package com.example.superjeuenfait;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;

public class MapActivity extends AppCompatActivity {

    private ImageButton exitButton;
    private ImageButton imageButtonSpaceship;
    private ImageButton imageButtonEarth;
    private ImageButton imageButtonTomatoPlanet;
    private ImageButton imageButtonEggplantPlanet;
    private ImageButton imageButtonPumpkinPlanet;
    private ImageButton imageButtonBroccoliPlanet;
    private Game game;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        sharedPreferences=getBaseContext().getSharedPreferences("GAME",MODE_PRIVATE);
        if(sharedPreferences.contains("GAME_DATA")){
            Gson gson = new Gson();
            String json= sharedPreferences.getString("GAME_DATA",null);
            game=gson.fromJson(json,Game.class);
        }
        else{
            game=new Game("Joueur");
            saveData(game);
        }

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertExit();
            }
        });
        imageButtonSpaceship = findViewById(R.id.imageButtonSpaceship);
        imageButtonSpaceship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(SpaceshipViewActivity.class);
            }
        });
        imageButtonEarth = findViewById(R.id.imageButtonEarth);
        imageButtonEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(EarthViewActivity.class);
            }
        });
        imageButtonTomatoPlanet = findViewById(R.id.imageButtonTomatoPlanet);
        imageButtonTomatoPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(TomatoDustActivity.class);
            }
        });
        imageButtonEggplantPlanet = findViewById(R.id.imageButtonEggplantPlanet);
        imageButtonEggplantPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(EggplantDustActivity.class);
            }
        });
        imageButtonPumpkinPlanet = findViewById(R.id.imageButtonPumpkinPlanet);
        imageButtonPumpkinPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(PumpkinDustActivity.class);
            }
        });
        imageButtonBroccoliPlanet = findViewById(R.id.imageButtonBroccoliPlanet);
        imageButtonBroccoliPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(BroccoliDustActivity.class);
            }
        });
    }

    @Override
    public void onBackPressed(){
        //disable back button
    }

    public void saveData(Game game){
        Gson gson = new Gson();
        sharedPreferences.edit()
                .putString("GAME_DATA",gson.toJson(game))
                .apply();
    }

    public void showAlertExit(){
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Quitter le jeu");
        alertDialog.setMessage("Êtes vous sûr de vouloir quitter cette SUPERBE application ?");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }

    public void changeActivity(Class newActivity){
        Intent intent = new Intent(this, newActivity);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        saveData(game);
        super.onDestroy();
    }
}
