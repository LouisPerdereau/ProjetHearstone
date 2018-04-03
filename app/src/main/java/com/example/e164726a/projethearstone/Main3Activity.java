package com.example.e164726a.projethearstone;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

public class Main3Activity extends Activity {

    private TextView nom;
    private TextView effet;
    private TextView stats;
    private TextView type;
    private TextView classe;

    private String json;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        json = this.getIntent().getStringExtra("result");

        JsonParser parser = new JsonParser();
        final JsonObject obj = parser.parse(json).getAsJsonObject();

        setContentView(R.layout.activity_main3);

        nom = findViewById(R.id.nom);

        effet = findViewById(R.id.effet);

        stats = findViewById(R.id.stats);

        type = findViewById(R.id.typeCarte);

        classe = findViewById(R.id.classeCarte);

        nom.setText(obj.getAsJsonObject().get("name").toString().replace("\"",""));

        if(obj.getAsJsonObject().get("text") != null){
            effet.setText(obj.getAsJsonObject().get("text").toString().replace("\"",""));
        }else{

        }

        if(obj.getAsJsonObject().get("health") != null){
            stats.setText(obj.getAsJsonObject().get("health").toString().replace("\"",""));
        }else{

        }

        type.setText(obj.getAsJsonObject().get("type").toString().replace("\"",""));

        classe.setText(obj.getAsJsonObject().get("playerClass").toString().replace("\"",""));


        //Initialize ImageView
        final ImageView imageView = (ImageView) findViewById(R.id.avatar);

        //Loading image from below url into imageView
        String url = obj.getAsJsonObject().get("img").toString().replace("\"","");
        Log.d("url",url);

        Picasso.with(this)
                .load(url)
                .error(R.drawable.cardback)
                .into(imageView);



    }
}
