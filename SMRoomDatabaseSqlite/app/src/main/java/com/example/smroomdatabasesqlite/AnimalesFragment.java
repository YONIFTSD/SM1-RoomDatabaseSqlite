package com.example.smroomdatabasesqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smroomdatabasesqlite.helper.AnimalListAdapter;
import com.example.smroomdatabasesqlite.roomdatabase.Animal;
import com.example.smroomdatabasesqlite.vistamodelo.AnimalViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AnimalesFragment extends Fragment {
    public static final int NEW_ANIMAL_RESULT = 1;
    private AnimalViewModel animalViewModel;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animales, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AnimalesFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final AnimalListAdapter adapter = new AnimalListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        animalViewModel = ViewModelProviders.of(this).get(AnimalViewModel.class);

        animalViewModel.getAllAnimals().observe(getViewLifecycleOwner(), new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animals) {
                adapter.setAnimals(animals);
            }
        });

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AnimalRegisterActivity.class);
                startActivityForResult(intent, NEW_ANIMAL_RESULT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ANIMAL_RESULT && resultCode == Activity.RESULT_OK) {
            assert data != null;
            String name = data.getStringExtra(AnimalRegisterActivity.RESPONSE_NAME);
            String description = data.getStringExtra(AnimalRegisterActivity.RESPONSE_DESCRIPTION);
            String continent = data.getStringExtra(AnimalRegisterActivity.RESPONSE_CONTINENT);
            assert name != null;
            assert description != null;
            assert continent != null;
            Animal animal = new Animal(name, description, continent);
            animalViewModel.insert(animal);
        }else {
            Toast.makeText(getContext(), "THERE IS NO DATA", Toast.LENGTH_SHORT).show();
        }
    }
}