package com.example.e164726a.projethearthstone;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.Spinner;



import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import com.koushikdutta.ion.Ion;

public class MainActivity extends AppCompatActivity {

    private Spinner spinClass;
    private Spinner spinType;
    private Spinner spinFaction;
    private Spinner spinRace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ion.with(MainActivity.this)
                .load("https://omgvamp-hearthstone-v1.p.mashape.com/cards")
                .addHeader("X-Mashape-Key","ffCXysEgCMmshgWPOSVnNtrl3kllp1YejDMjsnP8cQy6RhhHBl")
                .addHeader("X-Mashape-Host", "omgvamp-hearthstone-v1.p.mashape.com")
                .asJsonObject()

                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("-error-", e.getMessage());
                        Log.d("-Json HearthStone-", result.toString());

                    }

                });
    }
}
