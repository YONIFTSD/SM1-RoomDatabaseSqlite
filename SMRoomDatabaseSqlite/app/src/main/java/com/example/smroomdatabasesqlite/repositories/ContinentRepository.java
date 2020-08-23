package com.example.smroomdatabasesqlite.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.smroomdatabasesqlite.roomdatabase.AnimalRoomDatabase;
import com.example.smroomdatabasesqlite.roomdatabase.Continent;
import com.example.smroomdatabasesqlite.roomdatabase.ContinentDAO;

import java.util.List;

public class ContinentRepository {

    private ContinentDAO continentDAO;
    private LiveData<List<Continent>> continents;

    public ContinentRepository(Application application) {
        AnimalRoomDatabase animalRoomDatabase = AnimalRoomDatabase.getDatabase(application);
        continentDAO = animalRoomDatabase.continentDAO();
        continents = continentDAO.getAllContinents();
    }

    public LiveData<List<Continent>> getAllContinents() {
        return continents;
    }

    public void insert(Continent continent) {
        new insertAsyncTask(continentDAO).execute(continent);
    }

    private static class insertAsyncTask extends AsyncTask<Continent, Void, Void> {

        private ContinentDAO continentDAO;

        private insertAsyncTask(ContinentDAO continentDAO) {
            this.continentDAO = continentDAO;
        }

        @Override
        protected Void doInBackground(Continent... continents) {
            continentDAO.insert(continents[0]);
            return null;
        }
    }
}
