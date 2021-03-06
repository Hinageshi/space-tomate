package com.example.superjeuenfait;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class SpaceshipViewActivity extends AppCompatActivity {

    private ImageButton imageButtonTomato;
    private ImageButton imageButtonEggplant;
    private ImageButton imageButtonPumpkin;
    private ImageButton imageButtonBroccoli;
    private ImageButton imageButtonChest;
    private ImageButton testReset;
    private ImageButton testUnlock;
    private ImageButton testT;
    private ImageButton testE;
    private ImageButton testP;
    private ImageButton testB;
    private Game game;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceship_view);

        sharedPreferences=getBaseContext().getSharedPreferences("GAME",MODE_PRIVATE);
        Gson gson = new Gson();
        String json= sharedPreferences.getString("GAME_DATA",null);
        game=gson.fromJson(json,Game.class);

        testUnlock= findViewById(R.id.testAddProduct);
        testUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.unlockNewProduct();
                showToast("testUnlock applied");
            }
        });
        testReset= findViewById(R.id.testReset);
        testReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game=new Game("Joueur");
                saveData(game);
                showToast("testReset applied");
            }
        });
        testT= findViewById(R.id.testAddTomato);
        testT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getPlayer().addProductStock(Products.TOMATO,50);
                showToast("testT applied");
            }
        });
        testE= findViewById(R.id.testAddEggplant);
        testE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getPlayer().addProductStock(Products.EGGPLANT,50);
                showToast("testE applied");
            }
        });
        testP= findViewById(R.id.testAddPumpkin);
        testP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getPlayer().addProductStock(Products.PUMPKIN,50);
                showToast("testP applied");
            }
        });
        testB= findViewById(R.id.testAddBroccoli);
        testB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.getPlayer().addProductStock(Products.BROCCOLI,50);
                showToast("testB applied");
            }
        });

        imageButtonTomato = findViewById(R.id.imageButtonTomato);
        imageButtonTomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(TomatoActivity.class);
            }
        });
        imageButtonEggplant = findViewById(R.id.imageButtonEggplant);
        imageButtonEggplant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(EggplantActivity.class);
            }
        });
        imageButtonPumpkin = findViewById(R.id.imageButtonPumpkin);
        imageButtonPumpkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(PumpkinActivity.class);
            }
        });
        imageButtonBroccoli = findViewById(R.id.imageButtonBroccoli);
        imageButtonBroccoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(BroccoliActivity.class);
            }
        });

        imageButtonChest = findViewById(R.id.imageButtonChest);
        imageButtonChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(InventoryActivity.class);
            }
        });
    }

    @Override
    public void onBackPressed(){
        changeActivity(MapActivity.class);
    }

    public void saveData(Game game){
        Gson gson = new Gson();
        sharedPreferences.edit()
                .putString("GAME_DATA",gson.toJson(game))
                .apply();
    }

    public void changeActivity(Class newActivity){
        Intent intent = new Intent(this, newActivity);
        saveData(game);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        saveData(game);
        super.onDestroy();
    }

    public void showToast(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
