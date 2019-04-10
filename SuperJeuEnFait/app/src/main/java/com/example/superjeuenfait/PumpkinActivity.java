package com.example.superjeuenfait;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;

public class PumpkinActivity extends AppCompatActivity {

    private ImageButton imageButtonArrowLeft;
    private ImageButton imageButtonArrowRight;
    private Game game;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumpkin);

        sharedPreferences=getBaseContext().getSharedPreferences("GAME",MODE_PRIVATE);
        Gson gson = new Gson();
        String json= sharedPreferences.getString("GAME_DATA",null);
        game=gson.fromJson(json,Game.class);

        imageButtonArrowLeft = findViewById(R.id.imageButtonArrowLeft);
        imageButtonArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(EggplantActivity.class);
            }
        });

        imageButtonArrowRight = findViewById(R.id.imageButtonArrowRight);
        imageButtonArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(BroccoliActivity.class);
            }
        });

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
