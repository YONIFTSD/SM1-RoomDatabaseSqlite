package com.example.smroomdatabasesqlite.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Animal.TABLE_NAME)
public class Animal {
    static final String TABLE_NAME = "animals";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "continent")
    private String continent;

    public Animal(@NonNull String name, @NonNull String description, @NonNull String continent){
        this.name = name;
        this.description = description;
        this.continent = continent;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName(){
        return name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getContinent() {
        return continent;
    }
}
