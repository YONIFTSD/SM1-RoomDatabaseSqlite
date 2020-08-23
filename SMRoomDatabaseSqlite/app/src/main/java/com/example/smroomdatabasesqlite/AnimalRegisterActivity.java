package com.example.smroomdatabasesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smroomdatabasesqlite.roomdatabase.Continent;
import com.example.smroomdatabasesqlite.viewmodel.ContinentViewModel;

import java.util.ArrayList;
import java.util.List;

public class AnimalRegisterActivity extends AppCompatActivity {

    public static final String RESPONSE_NAME = "name";
    public static final String RESPONSE_DESCRIPTION = "description";
    public static final String RESPONSE_CONTINENT = "continent";
    private EditText text_field_animal_name;
    private EditText text_field_animal_description;
    AutoCompleteTextView editTextFilledExposedDropdown;

    ArrayList<String> CONTINENTS_ARRAY;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_animal);

        getContinents();

        text_field_animal_name = findViewById(R.id.text_field_animal_name);
        text_field_animal_description = findViewById(R.id.text_field_animal_description);

        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.compomente_continente_row, CONTINENTS_ARRAY);
        editTextFilledExposedDropdown = findViewById(R.id.text_field_animal_continent);
        editTextFilledExposedDropdown.setAdapter(adapter);

        final Button button_animal_save = findViewById(R.id.button_animal_save);

        button_animal_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(text_field_animal_name.getText())) {
                    Toast.makeText(AnimalRegisterActivity.this, "Ingrese Nombre", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(text_field_animal_description.getText())) {
                    Toast.makeText(AnimalRegisterActivity.this, "Ingrese Descripción", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(editTextFilledExposedDropdown.getText().toString())){
                    Toast.makeText(AnimalRegisterActivity.this, "Ingrese Descripción", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent();
                    String name = text_field_animal_name.getText().toString();
                    String description = text_field_animal_description.getText().toString();
                    String continent = editTextFilledExposedDropdown.getText().toString();
                    intent.putExtra(RESPONSE_NAME, name);
                    intent.putExtra(RESPONSE_DESCRIPTION, description);
                    intent.putExtra(RESPONSE_CONTINENT, continent);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        ContinentViewModel continentViewModel = ViewModelProviders.of(this).get(ContinentViewModel.class);
        continentViewModel.getAllContinent().observe(this, new Observer<List<Continent>>() {
            @Override
            public void onChanged(List<Continent> continents) {
                ArrayList<String> listArray = new ArrayList<>();
                for(Continent continent : continents){
                    listArray.add(continent.getName());
                    Log.d("CONTINENT", continent.getName());
                }
                CONTINENTS_ARRAY.addAll(listArray);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void getContinents() {
        CONTINENTS_ARRAY = new ArrayList<>();
    }
}
