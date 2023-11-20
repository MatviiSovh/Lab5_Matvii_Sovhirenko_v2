package com.example.lab5_matvii_sovhirenko_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
        statusTV.setText("Loading currency for USD");
        getDataByAsyncTask();
    }

    public void LoadSpecificData(View view) {
        String searchText = searchET.getText().toString().trim();
        if (!searchText.isEmpty()) {
            String currencyToSearch = searchText.toUpperCase();
            new DataLoader() {
                @Override
                protected void onPostExecute(String result) {
                    if (!result.isEmpty()) {
                        statusTV.setText("Found currency: " + currencyToSearch + "\n" + result);
                    } else {
                        statusTV.setText("Currency not found: " + currencyToSearch);
                    }
                }
            }.execute(ConstantLinks.FLOATRATES_API_URL, currencyToSearch);
        } else {
            statusTV.setText("Please enter a currency to search");
        }
    }


    public void getDataByAsyncTask(){
        new DataLoader() {
            @Override
            public void onPostExecute(String result) {
                statusTV.setText("Loaded data for USD currency: \n" + result);
            }
        }.execute(ConstantLinks.FLOATRATES_API_URL);
    }
}