package com.example.lab5_matvii_sovhirenko_v2;

import static com.example.lab5_matvii_sovhirenko_v2.ConstantLinks.FLOATRATES_API_URL;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiDataReader {
    public static String getValuesFromApi(String apiCode) throws IOException {
        InputStream apiContentStream = null;
        String result = "";
        try {
            switch (apiCode) {
                case FLOATRATES_API_URL:
                    apiContentStream = downloadUrlContent(FLOATRATES_API_URL);
                    result = Parser.getCurrencyRatesBaseUsd(apiContentStream);
                    break;
                default:
            }
        }
        finally {
            if (apiContentStream != null) {
                apiContentStream.close();
            }
        }
        return result;
    }

    public static String getValuesFromApi(String apiCode, String currencyToSearch) throws IOException {
        InputStream apiContentStream = null;
        String result = "";
        try {
            switch (apiCode) {
                case FLOATRATES_API_URL:
                    apiContentStream = downloadUrlContent(FLOATRATES_API_URL);
                    result = Parser.getCurrencyRatesByCurrency(apiContentStream, currencyToSearch);
                    break;
                default:
            }
        } finally {
            if (apiContentStream != null) {
                apiContentStream.close();
            }
        }
        return result;
    }

    private static InputStream downloadUrlContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}
