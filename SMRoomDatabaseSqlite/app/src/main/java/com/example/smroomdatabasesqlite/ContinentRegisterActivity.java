package com.example.smroomdatabasesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContinentRegisterActivity extends AppCompatActivity {

    public static final String RESPONSE_NAME = "name";
    private EditText text_field_continent_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_continente);

        text_field_continent_name = findViewById(R.id.text_field_continent_name);
        Button button_continent_save = findViewById(R.id.button_continent_save);

        button_continent_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(text_field_continent_name.getText().toString())){
                    Toast.makeText(ContinentRegisterActivity.this, "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    String name = text_field_continent_name.getText().toString();
                    intent.putExtra(RESPONSE_NAME, name);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}
