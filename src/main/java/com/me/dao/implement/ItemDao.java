package com.me.dao.implement;

import static com.me.DataSource.*;
import com.me.dao.Dao;
import com.me.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao implements Dao<Item> {
    //private DataSource dataSource = new DataSource();

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
            //connection = DataSource.getConnection();
            //PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from items");
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
        return null;
    }

    public synchronized void insert(Item item) {

    }

    public synchronized void update(Item item) {

    }

    public synchronized void delete(Item item) {

    }
}
