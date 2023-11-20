package com.example.lab5_matvii_sovhirenko_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView currencyLV;
    private EditText searchET;
    private TextView statusTV;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.currencyLV = findViewById(R.id.currencyLV);
        this.searchET = findViewById(R.id.searchET);
        this.statusTV = findViewById(R.id.statusTV);


        this.adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        currencyLV.setAdapter(this.adapter);
    }


    public void LoadData(View view) {
        this.statusTV.setText("Loading data.. ");
    }

}