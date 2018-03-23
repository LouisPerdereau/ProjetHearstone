package com.example.e164726a.projethearthstone;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.parser.AsyncParser;
import com.koushikdutta.ion.Ion;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            HttpResponse<JsonNode> response = Unirest.get("https://omgvamp-hearthstone-v1.p.mashape.com/cards")
                    .header("X-Mashape-Key", "zlietzvXopmshQaw5kLpHhPRJ9ggp1s1rfPjsn3PhcjtWFHUf7")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

   /*     Ion.with(MainActivity.this)
                .load("https://omgvamp-hearthstone-v1.p.mashape.com/cards")
                .addHeader("X-Mashape-Key","zlietzvXopmshQaw5kLpHhPRJ9ggp1s1rfPjsn3PhcjtWFHUf7")
                .asJsonObject()

                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("-error-", e.getMessage());
                        Log.d("-Json HearthStone-", result.toString());

                    }

                });*/
    }
}
