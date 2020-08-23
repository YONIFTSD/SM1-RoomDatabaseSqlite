package com.example.smroomdatabasesqlite.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContinentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Continent continent);

    @Query("SELECT * FROM continents ORDER BY id ASC")
    LiveData<List<Continent>> getAllContinents();

    @Query("DELETE FROM continents")
    void deleteAll();

}
