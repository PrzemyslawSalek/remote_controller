package com.app.remote_controller_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app.remote_controller_app.Fragments.Controller;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class DatabaseHelper  extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "remote_controller.db";
    private static final int DATABASE_VERSION = 0;

    private Dao controllerDao = null;
    private Dao componentDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
//        try {
//            TableUtils.createTable(connectionSource, Controller.class);
//            TableUtils.createTable(connectionSource, Component.class);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//        try {
//            TableUtils.dropTable(connectionSource, Controller.class, true);
//            TableUtils.dropTable(connectionSource, Component.class, true);
//            onCreate(database, connectionSource);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void addController(Controller c){
        try {
            controllerDao.create(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNameController(Controller c){
        try {
            controllerDao.update(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeController(Controller c){
        try {
            controllerDao.delete(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Controller> listController(){
        try {
            return controllerDao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
