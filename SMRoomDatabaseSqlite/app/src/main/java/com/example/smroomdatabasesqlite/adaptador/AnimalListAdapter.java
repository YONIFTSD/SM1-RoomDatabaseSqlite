package com.example.smroomdatabasesqlite.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smroomdatabasesqlite.R;
import com.example.smroomdatabasesqlite.roomdatabase.Animal;

import java.util.List;

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Animal> animals;

    public AnimalListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimalListAdapter.AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalListAdapter.AnimalViewHolder holder, int position) {
        holder.setData(animals.get(position));
    }

    @Override
    public int getItemCount() {
        if(animals != null){
            return animals.size();
        }else{
            return 0;
        }
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {

        private final TextView animal_name;
        private final TextView animal_description;
        private final TextView animal_continent;

        AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            animal_name = itemView.findViewById(R.id.animal_name);
            animal_description = itemView.findViewById(R.id.animal_description);
            animal_continent = itemView.findViewById(R.id.animal_continent);
        }

        private void setData(Animal animal) {
            String continent = "Continente: " + animal.getContinent();
            animal_name.setText(animal.getName());
            animal_description.setText(animal.getDescription());
            animal_continent.setText(continent);
        }
    }
}
