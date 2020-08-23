package com.example.smroomdatabasesqlite.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnimalDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Animal animal);

    @Query("SELECT * FROM animals ORDER BY id DESC")
    LiveData<List<Animal>> getAllAnimals();

    @Query("DELETE FROM animals")
    void deleteAll();
}
