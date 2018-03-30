package com.example.e164726a.projethearstone;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.example.e164726a.projethearthstone.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.gson.GsonArrayParser;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinClass;
    private Spinner spinType;
    private Spinner spinFaction;
    private Spinner spinRace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinClass = (Spinner) findViewById(R.id.spinClass);
        spinFaction = (Spinner) findViewById(R.id.spinFaction);
        spinRace = (Spinner) findViewById(R.id.spinRace);
        spinType = (Spinner) findViewById(R.id.spinType);

        final Intent verActivity2 = new Intent(MainActivity.this, Main2Activity.class);

        Ion.with(MainActivity.this)
                .load("https://omgvamp-hearthstone-v1.p.mashape.com/info/")
                .addHeader("X-Mashape-Key","ffCXysEgCMmshgWPOSVnNtrl3kllp1YejDMjsnP8cQy6RhhHBl")
                .addHeader("X-Mashape-Host", "omgvamp-hearthstone-v1.p.mashape.com")
                .asJsonObject()

                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                            Log.d("-Json HearthStone-", result.toString());

                            /* --------------------- Classes ------------------------- */

                            List<String> listClass = new LinkedList<>();
                            listClass.add("Choose your classes");
                            JsonArray classes = result.getAsJsonArray("classes");

                            for (JsonElement classe : classes) {
                                listClass.add(classe.toString().replace("\"",""));
                            }

                            ArrayAdapter<String> spinClassArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, listClass);

                            spinClassArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinClass.setAdapter(spinClassArrayAdapter);

                            /* --------------------- type ------------------------- */
                            List<String> listType = new LinkedList<>();
                            listType.add("Choose your type");
                            JsonArray types = result.getAsJsonArray("types");

                            for (JsonElement type : types) {
                                listType.add(type.toString().replace("\"",""));
                            }

                            ArrayAdapter<String> spinTypeArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, listType);

                            spinTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinType.setAdapter(spinTypeArrayAdapter);

                           /* --------------------- Faction ------------------------- */
                            List<String> listFaction = new LinkedList<>();
                            listFaction.add("Choose your faction");
                            JsonArray factions = result.getAsJsonArray("factions");

                            for (JsonElement faction : factions) {
                                listFaction.add(faction.toString().replace("\"",""));
                            }

                            ArrayAdapter<String> spinFactionArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, listFaction);

                            spinFactionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinFaction.setAdapter(spinFactionArrayAdapter);

                           /* --------------------- Faction ------------------------- */
                            List<String> listRace = new LinkedList<>();
                            listRace.add("Choose your race");
                            JsonArray races = result.getAsJsonArray("races");

                            for (JsonElement race : races) {
                                listRace.add(race.toString().replace("\"",""));
                            }

                            ArrayAdapter<String> spinRaceArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, listRace);

                            spinRaceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinRace.setAdapter(spinRaceArrayAdapter);

                    }

                });





        spinClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                final String classes = spinClass.getSelectedItem().toString();
                if(classes.compareTo("Choose your classes") != 0) {

                    String request = classes.replace(" ","%20");

                    Log.d("-class-", "https://omgvamp-hearthstone-v1.p.mashape.com/cards/classes/" +request);

                    Ion.with(MainActivity.this)
                               .load("https://omgvamp-hearthstone-v1.p.mashape.com/cards/classes/"+classes)
                               .addHeader("X-Mashape-Key", "ffCXysEgCMmshgWPOSVnNtrl3kllp1YejDMjsnP8cQy6RhhHBl")
                               .addHeader("X-Mashape-Host", "omgvamp-hearthstone-v1.p.mashape.com")
                               .asJsonArray()

                                .setCallback(new FutureCallback<JsonArray>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonArray result) {
                                        if (result != null) {
                                            Log.d("-Json HearthStone-", result.toString());
                                            verActivity2.putExtra("result", result.toString());
                                            verActivity2.putExtra("selected", classes);
                                            startActivity(verActivity2);
                                        }else{
                                            Log.d("-error-",e.getMessage());
                                        }
                                    }

                                });
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        spinFaction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        spinRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
