package com.example.smroomdatabasesqlite.helper;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.smroomdatabasesqlite.roomdatabase.Animal;
import com.example.smroomdatabasesqlite.roomdatabase.AnimalDAO;
import com.example.smroomdatabasesqlite.roomdatabase.AnimalRoomDatabase;

import java.util.List;

public class AnimalRepository {
    private AnimalDAO animalDAO;
    private LiveData<List<Animal>> animals;

    public AnimalRepository(Application application) {
        AnimalRoomDatabase animalRoomDatabase = AnimalRoomDatabase.getDatabase(application);
        animalDAO = animalRoomDatabase.animalDAO();
        animals = animalDAO.getAllAnimals();
    }

    public LiveData<List<Animal>> getAllAnimals() {
        return animals;
    }

    public void insert(Animal animal) {
        new insertAsyncTask(animalDAO).execute(animal);
    }

    private static class insertAsyncTask extends AsyncTask<Animal, Void, Void> {

        private AnimalDAO animalDAO;

        private insertAsyncTask(AnimalDAO animalDAO) {
            this.animalDAO = animalDAO;
        }

        @Override
        protected Void doInBackground(Animal... animals) {
            animalDAO.insert(animals[0]);
            return null;
        }
    }
}
