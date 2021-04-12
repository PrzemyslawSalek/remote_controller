package com.app.remote_controller_app.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class SerializedControllers {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String serializedController;

    public SerializedControllers() {
    }

}
