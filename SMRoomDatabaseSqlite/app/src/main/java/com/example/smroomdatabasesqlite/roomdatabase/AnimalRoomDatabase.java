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
            animals.add(new Animal("El Elefante Africano", "Es el animal terrestre más grande del planeta. Los machos miden cerca de 3 metros de altura y pesan de 5 a 6 toneladas. Las hembras son un poco menores y alcanzan aproximadamente la mitad de peso. Tienen unos largos colmillos de marfil.", "Africa"));
            animals.add(new Animal("Anaconda", "Americano de la misma familia de las boas y de costumbres acuáticas, que pertenece a las especies estranguladoras, mide entre 4,5 y 10 m de longitud, es de color pardo grisáceo con manchas negras redondeadas sobre el dorso y tiene cabeza de color oscuro con una banda anaranjada detrás de los ojos.", "América"));
            animals.add(new Animal("Bos Mutus", "Es un bóvido de tamaño mediano y pelaje lanoso, nativo de las montañas de Asia Cental y el Himalaya, vive en las altiplanicies esteparias y fríos desiertos del Tíbet, Pamir y Karakórum, entre los 4000 y 6000 metros de altitud, donde se encuentra tanto en estado salvaje como doméstico.", "Asia"));
            animals.add(new Animal("Bison bisonasus", "Es una especie de mamíferoartiodáctilo de la familia Bovidae. Es el mamífero de mayor tamaño deEuropa y uno de los más amenazados, por lo que es objeto de varios programas de reproducción en cautividad llevados a cabo en parques zoológicos.", "Europa"));
            animals.add(new Animal("Diablo de Tasmania", "Es una especie de marsupial dasiuromorfo de la familia Dasyuridae. En la actualidad sólo se encuentra en estado silvestre en la isla de Tasmania, al sur de Australia. Es el marsupial carnívoro de mayor tamaño existente en la actualidad, tras la extinción del lobo marsupial.", "Oceanía"));
            return animals;
        }

        private List<Continent> getContinents() {
            ArrayList<Continent> continents = new ArrayList<>();
            continents.add(new Continent("Africa"));
            continents.add(new Continent("América"));
            continents.add(new Continent("Asia"));
            continents.add(new Continent("Europa"));
            continents.add(new Continent("Oceanía"));
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
