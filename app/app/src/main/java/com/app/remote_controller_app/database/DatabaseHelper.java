package com.app.remote_controller_app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app.remote_controller_app.Controller;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "remote_controller.db";
    private static final int DATABASE_VERSION = 8;

    private Dao serializedControllerDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, SerializedControllers.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, SerializedControllers.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao getSerializedControllerDao() throws SQLException {
        if (serializedControllerDao == null) {
            serializedControllerDao = getDao(SerializedControllers.class);
        }
        return serializedControllerDao;
    }
}
