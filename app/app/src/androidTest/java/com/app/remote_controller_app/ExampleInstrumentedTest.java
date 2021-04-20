package com.app.remote_controller_app;

import android.content.Context;
import android.database.SQLException;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.app.remote_controller_app.database.DatabaseHelper;
import com.app.remote_controller_app.database.SerializedControllers;
import com.j256.ormlite.dao.Dao;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void databaseAddController() throws SQLException, java.sql.SQLException {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DatabaseHelper db = new DatabaseHelper(appContext);
            try{

                Dao d = db.getSerializedControllerDao();
                SerializedControllers sc=new SerializedControllers(new Controller("name123", ""));
                d.create(sc);
                sc.getObject().setId(sc.getId());
                d.update(sc);
                List<SerializedControllers> listS=d.queryForAll();
                assertEquals(sc.getSerializedController(),listS.get(listS.size() - 1).getSerializedController());
                d.deleteById(sc.getId());

            } catch (java.sql.SQLException e) {
                throw new RuntimeException(e);
            }
    }


    @Test
    public void databaseRemoveController() throws SQLException, java.sql.SQLException {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DatabaseHelper db = new DatabaseHelper(appContext);
        try{
            Dao d = db.getSerializedControllerDao();
            SerializedControllers sc=new SerializedControllers(new Controller("name123", ""));
            d.create(sc);
            sc.getObject().setId(sc.getId());
            d.update(sc);
            d.deleteById(sc.getId());
            List<SerializedControllers> listS=d.queryForAll();
            assertEquals(false, listS.contains(sc));
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
