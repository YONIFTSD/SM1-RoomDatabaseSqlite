package com.example.smroomdatabasesqlite.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smroomdatabasesqlite.repositories.AnimalRepository;
import com.example.smroomdatabasesqlite.roomdatabase.Animal;

import java.util.List;

public class AnimalViewModel extends AndroidViewModel {

    private AnimalRepository animalRepository;
    private LiveData<List<Animal>> animals;

    public AnimalViewModel(@NonNull Application application) {
        super(application);
        animalRepository = new AnimalRepository(application);
        animals = animalRepository.getAllAnimals();
    }

    public LiveData<List<Animal>> getAllAnimals() {
        return animals;
    }

    public void insert(Animal animal) {
        animalRepository.insert(animal);
    }
}
