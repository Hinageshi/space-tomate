package com.example.superjeuenfait;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class InventoryActivity extends AppCompatActivity {

    private TextView textViewNumberTomato;
    private TextView textViewNumberEggplant;
    private TextView textViewNumberPumpkin;
    private TextView textViewNumberBroccoli;
    private TextView textViewNumberTomatoDust;
    private TextView textViewNumberEggplantDust;
    private TextView textViewNumberPumpkinDust;
    private TextView textViewNumberBroccoliDust;
    private TextView textViewNumberMoney;
    private Game game;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        sharedPreferences=getBaseContext().getSharedPreferences("GAME",MODE_PRIVATE);
        Gson gson = new Gson();
        String json= sharedPreferences.getString("GAME_DATA",null);
        game=gson.fromJson(json,Game.class);

        textViewNumberTomato = findViewById(R.id.textViewNumberTomato);
        textViewNumberTomato.setText(String.valueOf(game.getPlayer().getStockProducts(Products.TOMATO)));
        textViewNumberTomatoDust = findViewById(R.id.textViewNumberTomatoDust);
        textViewNumberTomatoDust.setText(String.valueOf(game.getPlayer().getStockDust(Products.TOMATO)));
        textViewNumberEggplant = findViewById(R.id.textViewNumberEggplant);
        textViewNumberEggplant.setText(String.valueOf(game.getPlayer().getStockProducts(Products.EGGPLANT)));
        textViewNumberEggplantDust = findViewById(R.id.textViewNumberEggplantDust);
        textViewNumberEggplantDust.setText(String.valueOf(game.getPlayer().getStockDust(Products.EGGPLANT)));
        textViewNumberPumpkin = findViewById(R.id.textViewNumberPumpkin);
        textViewNumberPumpkin.setText(String.valueOf(game.getPlayer().getStockProducts(Products.PUMPKIN)));
        textViewNumberPumpkinDust = findViewById(R.id.textViewNumberPumpkinDust);
        textViewNumberPumpkinDust.setText(String.valueOf(game.getPlayer().getStockDust(Products.PUMPKIN)));
        textViewNumberBroccoli = findViewById(R.id.textViewNumberBroccoli);
        textViewNumberBroccoli.setText(String.valueOf(game.getPlayer().getStockProducts(Products.BROCCOLI)));
        textViewNumberBroccoliDust = findViewById(R.id.textViewNumberBroccoliDust);
        textViewNumberBroccoliDust.setText(String.valueOf(game.getPlayer().getStockDust(Products.BROCCOLI)));
        textViewNumberMoney = findViewById(R.id.textViewNumberMoney);
        textViewNumberMoney.setText(String.valueOf(game.getPlayer().getMoney()));
    }

    @Override
    public void onBackPressed(){
        changeActivity(SpaceshipViewActivity.class);
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
}
