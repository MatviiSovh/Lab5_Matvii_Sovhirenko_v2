package com.example.lab5_matvii_sovhirenko_v2;

import android.os.AsyncTask;

import java.io.IOException;

public class DataLoader extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... params) {
        try {
            if (params.length > 1) {
                return ApiDataReader.getValuesFromApi(params[0], params[1]);
            } else {
                return ApiDataReader.getValuesFromApi(params[0]);
            }
        } catch (IOException ex) {
            return String.format("Some error occurred => %s", ex.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
