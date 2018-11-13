package com.me.dao.implement;

import static com.me.dao.DataSource.*;
import com.me.dao.Dao;
import com.me.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao implements Dao<Item> {

    //private DataSource dataSource = new DataSource();
    private static final String SQL_FIND_ALL = "select * from " + Item.TABLE_NAME;
    private static final String SQL_FIND_BY_ID = SQL_FIND_ALL + " where " + Item.ID_COLUMN + " = ?";
    private static final String SQL_INSERT = "insert into " + Item.TABLE_NAME + " (" + Item.NAME_COLUMN + ", " + Item.WAREHOUSE_ID_COLUMN + ") values (?, ?)";
    private static final String SQL_UPDATE = "update " + Item.TABLE_NAME + " set " + Item.NAME_COLUMN + " = ? where " + Item.ID_COLUMN + " = ?";
    private static final String SQL_DELETE = "delete from " + Item.TABLE_NAME + " where " + Item.ID_COLUMN + " = ?";

    private static volatile ItemDao itemDao = null;
    private static final Object lock = new Object();

    private ItemDao(){}

    public static ItemDao getInstance(){
        if (itemDao == null) {
            synchronized (lock) {
                if (itemDao == null) {
                    itemDao = new ItemDao();
                }
            }
        }
        return itemDao;
    }

    public synchronized List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
            //statement.setString(1, "items");
            //Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public synchronized Item findById(Long id) {
        Item item = null;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                item = new Item();
                item.setId(rs.getLong(Item.ID_COLUMN));
                item.setName(rs.getString(Item.NAME_COLUMN));
                item.setWarehouse_id(rs.getLong(Item.WAREHOUSE_ID_COLUMN));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return item;
    }

    public synchronized void insert(Item item) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setLong(2, item.getWarehouse_id());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public synchronized void update(Item item) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, item.getName());
            statement.setLong(2, item.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public synchronized void delete(Item item) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, item.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
