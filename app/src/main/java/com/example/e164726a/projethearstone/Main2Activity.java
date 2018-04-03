package com.example.e164726a.projethearstone;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Activity {

    private TextView tv;
    private String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.tvCard);
        final Intent versActivity3 = new Intent(Main2Activity.this,Main3Activity.class);
        String header = this.getIntent().getStringExtra("selected");
        tv.setText(header);
        json = this.getIntent().getStringExtra("result");



        try {
            List<String> items= new ArrayList<>();

            JsonParser parser = new JsonParser();
            final JsonArray obj = parser.parse(json).getAsJsonArray();

            for (JsonElement card : obj) {

                Log.d("name",card.getAsJsonObject().get("name").toString());
                items.add(card.getAsJsonObject().get("name").toString().replace("\"",""));

            }

            ListView listView = findViewById(R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    versActivity3.putExtra("result",obj.get(i).toString());
                    Log.d("click",obj.get(i).toString());
                    startActivity(versActivity3);

                }
            });




        }catch(Exception e)
        {
            e.printStackTrace();
        }



    }
}
