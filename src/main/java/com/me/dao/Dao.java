package com.me.dao;

import java.util.List;

public interface Dao<T> {

    //public final String SQL_FIND_ALL = "select * from " + T.TABLE_NAME;
    //public static final String SQL_FIND_BY_ID = SQL_FIND_ALL + " where " + T.ID_COLUMN + " = ?";
    //public static final String SQL_INSERT = "insert into " + T.TABLE_NAME + " (" + T.NAME_COLUMN + ", " + T.WAREHOUSE_ID_COLUMN + ") values (?, ?)";
    //public static final String SQL_UPDATE = "update " + T.TABLE_NAME + " set " + T.NAME_COLUMN + " where " + T.ID_COLUMN + " = ?";
    //public static final String SQL_DELETE = "delete from " + T.TABLE_NAME + " where " + T.ID_COLUMN + " = ?";

    List<T> findAll();
    T findById(Long id);
    void insert(T item);
    void update(T item);
    void delete(T item);
}
