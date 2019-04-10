package com.example.superjeuenfait;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class TomatoActivity extends AppCompatActivity {

    private ImageButton imageButtonArrowLeft;
    private ImageButton imageButtonArrowRight;
    private Button buttonBuy;
    private Button buttonCollectAll;
    private Game game;
    private SharedPreferences sharedPreferences;
    private ImageButton tomatoBox1;
    private ImageButton tomatoBox2;
    private ImageButton tomatoBox3;
    private ImageButton tomatoBox4;
    private ImageButton tomatoBox5;
    private ImageButton tomatoBox6;
    private ImageButton tomatoBox7;
    private ImageButton tomatoBox8;
    private ImageButton tomatoBox9;
    private TextView tomatoBox1NbToCollect;
    private TextView tomatoBox2NbToCollect;
    private TextView tomatoBox3NbToCollect;
    private TextView tomatoBox4NbToCollect;
    private TextView tomatoBox5NbToCollect;
    private TextView tomatoBox6NbToCollect;
    private TextView tomatoBox7NbToCollect;
    private TextView tomatoBox8NbToCollect;
    private TextView tomatoBox9NbToCollect;
    private TextView tomatoBox1NbShoots;
    private TextView tomatoBox2NbShoots;
    private TextView tomatoBox3NbShoots;
    private TextView tomatoBox4NbShoots;
    private TextView tomatoBox5NbShoots;
    private TextView tomatoBox6NbShoots;
    private TextView tomatoBox7NbShoots;
    private TextView tomatoBox8NbShoots;
    private TextView tomatoBox9NbShoots;

    private Handler myHandler;
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            if(tomatoBox1.getVisibility() == View.VISIBLE) tomatoBox1NbToCollect.setText(String.valueOf(Integer.parseInt(tomatoBox1NbToCollect.getText().toString()) + (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(0) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(0))));
            if(tomatoBox2.getVisibility() == View.VISIBLE) tomatoBox2NbToCollect.setText(String.valueOf(Integer.parseInt(tomatoBox2NbToCollect.getText().toString()) + (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(1) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(1))));
            if(tomatoBox3.getVisibility() == View.VISIBLE) tomatoBox3NbToCollect.setText(String.valueOf(Integer.parseInt(tomatoBox3NbToCollect.getText().toString()) + (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(2) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(2))));
            if(tomatoBox4.getVisibility() == View.VISIBLE) tomatoBox4NbToCollect.setText(String.valueOf(Integer.parseInt(tomatoBox4NbToCollect.getText().toString()) + (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(3) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(3))));
            if(tomatoBox5.getVisibility() == View.VISIBLE) tomatoBox5NbToCollect.setText(String.valueOf(Integer.parseInt(tomatoBox5NbToCollect.getText().toString()) + (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(4) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(4))));
            if(tomatoBox6.getVisibility() == View.VISIBLE) tomatoBox6NbToCollect.setText(String.valueOf(Integer.parseInt(tomatoBox6NbToCollect.getText().toString()) + (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(5) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(5))));
            if(tomatoBox7.getVisibility() == View.VISIBLE) tomatoBox7NbToCollect.setText(String.valueOf(Integer.parseInt(tomatoBox7NbToCollect.getText().toString()) + (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(6) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(6))));
            if(tomatoBox8.getVisibility() == View.VISIBLE) tomatoBox8NbToCollect.setText(String.valueOf(Integer.parseInt(tomatoBox8NbToCollect.getText().toString()) + (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(7) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(7))));
            if(tomatoBox9.getVisibility() == View.VISIBLE) tomatoBox9NbToCollect.setText(String.valueOf(Integer.parseInt(tomatoBox9NbToCollect.getText().toString()) + (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(8) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(8))));
            myHandler.postDelayed(this, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomato);

        sharedPreferences=getBaseContext().getSharedPreferences("GAME",MODE_PRIVATE);
        Gson gson = new Gson();
        String json= sharedPreferences.getString("GAME_DATA",null);
        game=gson.fromJson(json,Game.class);

        resetTimer();

        imageButtonArrowLeft = findViewById(R.id.imageButtonArrowLeft);
        imageButtonArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(BroccoliActivity.class);
            }
        });

        imageButtonArrowRight = findViewById(R.id.imageButtonArrowRight);
        imageButtonArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(EggplantActivity.class);
            }
        });

        buttonBuy = findViewById(R.id.buttonBuy);
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(TomatoBuyActivity.class);
            }
        });

        buttonCollectAll = findViewById(R.id.buttonCollectAll);
        buttonCollectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tomatoBox1.getVisibility() == View.VISIBLE){
                    game.getPlayer().addProductStock(Products.TOMATO, Integer.parseInt(tomatoBox1NbToCollect.getText().toString()));
                    tomatoBox1NbToCollect.setText("0");
                }
                if(tomatoBox2.getVisibility() == View.VISIBLE){
                    game.getPlayer().addProductStock(Products.TOMATO, Integer.parseInt(tomatoBox2NbToCollect.getText().toString()));
                    tomatoBox2NbToCollect.setText("0");
                }
                if(tomatoBox3.getVisibility() == View.VISIBLE){
                    game.getPlayer().addProductStock(Products.TOMATO, Integer.parseInt(tomatoBox3NbToCollect.getText().toString()));
                    tomatoBox3NbToCollect.setText("0");
                }
                if(tomatoBox4.getVisibility() == View.VISIBLE){
                    game.getPlayer().addProductStock(Products.TOMATO, Integer.parseInt(tomatoBox4NbToCollect.getText().toString()));
                    tomatoBox4NbToCollect.setText("0");
                }
                if(tomatoBox5.getVisibility() == View.VISIBLE){
                    game.getPlayer().addProductStock(Products.TOMATO, Integer.parseInt(tomatoBox5NbToCollect.getText().toString()));
                    tomatoBox5NbToCollect.setText("0");
                }
                if(tomatoBox6.getVisibility() == View.VISIBLE){
                    game.getPlayer().addProductStock(Products.TOMATO, Integer.parseInt(tomatoBox6NbToCollect.getText().toString()));
                    tomatoBox6NbToCollect.setText("0");
                }
                if(tomatoBox7.getVisibility() == View.VISIBLE){
                    game.getPlayer().addProductStock(Products.TOMATO, Integer.parseInt(tomatoBox7NbToCollect.getText().toString()));
                    tomatoBox7NbToCollect.setText("0");
                }
                if(tomatoBox8.getVisibility() == View.VISIBLE){
                    game.getPlayer().addProductStock(Products.TOMATO, Integer.parseInt(tomatoBox8NbToCollect.getText().toString()));
                    tomatoBox8NbToCollect.setText("0");
                }
                if(tomatoBox9.getVisibility() == View.VISIBLE) {
                    game.getPlayer().addProductStock(Products.TOMATO, Integer.parseInt(tomatoBox9NbToCollect.getText().toString()));
                    tomatoBox9NbToCollect.setText("0");
                }
                resetTimer();
            }
        });

        tomatoBox1 = findViewById(R.id.tomatoBox1);
        tomatoBox2 = findViewById(R.id.tomatoBox2);
        tomatoBox3 = findViewById(R.id.tomatoBox3);
        tomatoBox4 = findViewById(R.id.tomatoBox4);
        tomatoBox5 = findViewById(R.id.tomatoBox5);
        tomatoBox6 = findViewById(R.id.tomatoBox6);
        tomatoBox7 = findViewById(R.id.tomatoBox7);
        tomatoBox8 = findViewById(R.id.tomatoBox8);
        tomatoBox9 = findViewById(R.id.tomatoBox9);

        tomatoBox1NbToCollect = findViewById(R.id.tomatoBox1NbToCollect);
        tomatoBox2NbToCollect = findViewById(R.id.tomatoBox2NbToCollect);
        tomatoBox3NbToCollect = findViewById(R.id.tomatoBox3NbToCollect);
        tomatoBox4NbToCollect = findViewById(R.id.tomatoBox4NbToCollect);
        tomatoBox5NbToCollect = findViewById(R.id.tomatoBox5NbToCollect);
        tomatoBox6NbToCollect = findViewById(R.id.tomatoBox6NbToCollect);
        tomatoBox7NbToCollect = findViewById(R.id.tomatoBox7NbToCollect);
        tomatoBox8NbToCollect = findViewById(R.id.tomatoBox8NbToCollect);
        tomatoBox9NbToCollect = findViewById(R.id.tomatoBox9NbToCollect);

        tomatoBox1NbShoots = findViewById(R.id.tomatoBox1NbShoots);
        tomatoBox2NbShoots = findViewById(R.id.tomatoBox2NbShoots);
        tomatoBox3NbShoots = findViewById(R.id.tomatoBox3NbShoots);
        tomatoBox4NbShoots = findViewById(R.id.tomatoBox4NbShoots);
        tomatoBox5NbShoots = findViewById(R.id.tomatoBox5NbShoots);
        tomatoBox6NbShoots = findViewById(R.id.tomatoBox6NbShoots);
        tomatoBox7NbShoots = findViewById(R.id.tomatoBox7NbShoots);
        tomatoBox8NbShoots = findViewById(R.id.tomatoBox8NbShoots);
        tomatoBox9NbShoots = findViewById(R.id.tomatoBox9NbShoots);

        if(game.getPlayer().getSpaceship().getTomatoGarden().getSlotUnlocked() > 0){
            switch(game.getPlayer().getSpaceship().getTomatoGarden().getSlotUnlocked()){
                case 9: tomatoBox9.setVisibility(View.VISIBLE); tomatoBox9NbToCollect.setText("0"); tomatoBox9NbShoots.setVisibility(View.VISIBLE); setSlotData(8, tomatoBox9, tomatoBox9NbShoots);
                case 8: tomatoBox8.setVisibility(View.VISIBLE); tomatoBox8NbToCollect.setText("0"); tomatoBox8NbShoots.setVisibility(View.VISIBLE); setSlotData(7, tomatoBox8, tomatoBox8NbShoots);
                case 7: tomatoBox7.setVisibility(View.VISIBLE); tomatoBox7NbToCollect.setText("0"); tomatoBox7NbShoots.setVisibility(View.VISIBLE); setSlotData(6, tomatoBox7, tomatoBox7NbShoots);
                case 6: tomatoBox6.setVisibility(View.VISIBLE); tomatoBox6NbToCollect.setText("0"); tomatoBox6NbShoots.setVisibility(View.VISIBLE); setSlotData(5, tomatoBox6, tomatoBox6NbShoots);
                case 5: tomatoBox5.setVisibility(View.VISIBLE); tomatoBox5NbToCollect.setText("0"); tomatoBox5NbShoots.setVisibility(View.VISIBLE); setSlotData(4, tomatoBox5, tomatoBox5NbShoots);
                case 4: tomatoBox4.setVisibility(View.VISIBLE); tomatoBox4NbToCollect.setText("0"); tomatoBox4NbShoots.setVisibility(View.VISIBLE); setSlotData(3, tomatoBox4, tomatoBox4NbShoots);
                case 3: tomatoBox3.setVisibility(View.VISIBLE); tomatoBox3NbToCollect.setText("0"); tomatoBox3NbShoots.setVisibility(View.VISIBLE); setSlotData(2, tomatoBox3, tomatoBox3NbShoots);
                case 2: tomatoBox2.setVisibility(View.VISIBLE); tomatoBox2NbToCollect.setText("0"); tomatoBox2NbShoots.setVisibility(View.VISIBLE); setSlotData(1, tomatoBox2, tomatoBox2NbShoots);
                case 1: tomatoBox1.setVisibility(View.VISIBLE); tomatoBox1NbToCollect.setText("0"); tomatoBox1NbShoots.setVisibility(View.VISIBLE); setSlotData(0, tomatoBox1, tomatoBox1NbShoots);
            }
        }
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

    public void setSlotData(int nb, ImageButton imageButton, TextView textViewShoots){
        switch (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(nb)){
            case 1: imageButton.setImageResource(R.drawable.harvest_level_1); break;
            case 2: imageButton.setImageResource(R.drawable.harvest_level_2); break;
            case 3: imageButton.setImageResource(R.drawable.harvest_level_3); break;
            case 4: imageButton.setImageResource(R.drawable.harvest_level_4); break;
            case 5: imageButton.setImageResource(R.drawable.harvest_level_5); break;
            case 6: imageButton.setImageResource(R.drawable.harvest_level_6); break;
            case 7: imageButton.setImageResource(R.drawable.harvest_level_7); break;
            case 8: imageButton.setImageResource(R.drawable.harvest_level_8); break;
            case 9: imageButton.setImageResource(R.drawable.harvest_level_9); break;
            case 10: imageButton.setImageResource(R.drawable.harvest_level_10); break;
        }
        textViewShoots.setText(String.valueOf(game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(nb)));
    }

    public void changeActivity(Class newActivity){
        Intent intent = new Intent(this, newActivity);
        saveData(game);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        if(myHandler != null) myHandler.removeCallbacks(myRunnable);
        saveData(game);
        super.onDestroy();
    }

    public void resetTimer(){
        if(myHandler != null) myHandler.removeCallbacks(myRunnable);
        myHandler = new Handler();
        myHandler.postDelayed(myRunnable, 500);
    }

    public void onPause() {
        super.onPause();
        if(myHandler != null) myHandler.removeCallbacks(myRunnable); // On arrete le callback
    }
}
