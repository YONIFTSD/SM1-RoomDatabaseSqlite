package com.example.smroomdatabasesqlite.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

@Database(entities = { Animal.class, Continent.class }, version = 2, exportSchema = false)
public abstract class AnimalRoomDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "database_room_data";
    public abstract AnimalDAO animalDAO();
    public abstract ContinentDAO continentDAO();
    private static AnimalRoomDatabase instance;

    public static AnimalRoomDatabase getDatabase(final Context context){
        if (instance == null) {
            synchronized (AnimalRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AnimalRoomDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(roomDatabaseCallback).build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new fillDatabaseAsync(instance).execute();
        }
    };

    private static class fillDatabaseAsync extends AsyncTask<Void, Void, Void> {

        private final AnimalDAO animalDAO;
        private final ContinentDAO continentDAO;

        private List<Animal> getAnimals() {
            ArrayList<Animal> animals = new ArrayList<>();
            animals.add(new Animal("Ajolote", "En el axolotl, los aztecas vieron una manifestación del dios Xolotl, quien llevó a las almas al inframundo junto con el sol poniente. Los aztecas veneraban la carne del ajolote (como se le puede nombrar) y atrapaban a las criaturas de la enorme red de canales y lagos que sostenían sus comunidades en el centro de México. Hoy, solo queda una fracción de este sistema acuático, y está siendo contaminado por los fertilizantes, pesticidas, heces y basura de la Ciudad de México.", "América"));
            animals.add(new Animal("Mandril", "El estilo lo es todo cuando vives en un grupo tan grande como el de un mandril. Con una tropa de 1.300 ejemplares una vez registrada en los bosques de Gabón, se cree que los mandriles forman los grupos sociales más grandes de primates no humanos. Con sus llamativas caras y ojos, han evolucionado para exhibir la coloración más espectacular de cualquier especie de mamífero, cuya intensidad indica su estado social y sexual.", "América"));
            animals.add(new Animal("Osos polares", "Los osos polares dependen del hielo marino para atrapar su presa. Se abalanzan sobre las focas cuando emergen a través de sus respiraderos y las acechan mientras toman el sol al aire libre, pero el hielo se está derritiendo a medida que nuestro clima se calienta.", "Asia"));
            animals.add(new Animal("Lémures", "Solo queda el 10% de los bosques históricos de Madagascar, los cuales sustentan a estos lémures en peligro crítico. Con una gran pasión por el néctar, se cree que los lémures son los polinizadores más grandes del mundo.", "Europa"));
            return animals;
        }

        private List<Continent> getContinents() {
            ArrayList<Continent> continents = new ArrayList<>();
            continents.add(new Continent("Oceanía"));
            continents.add(new Continent("América"));
            continents.add(new Continent("Africa"));
            continents.add(new Continent("Europa"));
            continents.add(new Continent("Asia"));


            return continents;
        }

        fillDatabaseAsync(AnimalRoomDatabase animalRoomDatabase){
            animalDAO = animalRoomDatabase.animalDAO();
            continentDAO = animalRoomDatabase.continentDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            animalDAO.deleteAll();
            continentDAO.deleteAll();
            for(Animal item : getAnimals()){
                Animal animal = new Animal(item.getName(), item.getDescription(), item.getContinent());
                animalDAO.insert(animal);
            }

            for(Continent item : getContinents()){
                Continent continent = new Continent(item.getName());
                continentDAO.insert(continent);
            }

            return null;
        }
    }
}
