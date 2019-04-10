package com.example.superjeuenfait;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class TomatoSlotActivity extends AppCompatActivity {

    private ImageView imageViewSlot;
    private TextView textViewSlotName;
    private TextView textViewSlotLevel;
    private TextView textViewSlotShoots;
    private TextView textViewLevelPrice;
    private TextView textViewShootPrice;
    private Button buttonBuyLevel;
    private Button buttonBuyShoot;
    private Game game;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomato_slot);

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

        Intent intent = this.getIntent();

        textViewSlotName = findViewById(R.id.textViewSlotName);
        textViewSlotName.setText("Slot " + intent.getIntExtra("SLOT_NUMBER", 0));
        textViewSlotLevel = findViewById(R.id.textViewSlotLevel);
        textViewSlotLevel.setText("Level " + intent.getIntExtra("SLOT_LEVEL", 0));
        textViewSlotShoots = findViewById(R.id.textViewSlotShoots);
        textViewSlotShoots.setText(intent.getIntExtra("SLOT_SHOOT", 0) + " shoots");
        textViewLevelPrice = findViewById(R.id.textViewLevelPrice);
        textViewLevelPrice.setText("Price : " + (intent.getIntExtra("SLOT_NUMBER", 0) * intent.getIntExtra("SLOT_LEVEL", 0) * 2500));
        textViewShootPrice = findViewById(R.id.textViewShootPrice);
        textViewShootPrice.setText("Price : " + (intent.getIntExtra("SLOT_NUMBER", 0) * intent.getIntExtra("SLOT_SHOOT", 0) * 1500));

        imageViewSlot = findViewById(R.id.imageViewSlot);
        buttonBuyLevel = findViewById(R.id.buttonBuyLevel);
        buttonBuyLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(textViewLevelPrice.getText().toString().substring(8)) <= game.getPlayer().getMoney()){
                    if(game.getPlayer().getSpaceship().getTomatoGarden().levelCanBeIncreased(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1)){
                        game.getPlayer().getSpaceship().getTomatoGarden().increaseLevelSlot(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1);
                        game.getPlayer().setMoney(game.getPlayer().getMoney() - Integer.parseInt(textViewLevelPrice.getText().toString().substring(8)));
                        actualizeLevelShoot();
                    } else showToast("Slot is already at maximum level !");
                } else showToast("You don't have enough money to buy this !");
            }
        });
        buttonBuyShoot = findViewById(R.id.buttonBuyShoot);
        buttonBuyShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(textViewShootPrice.getText().toString().substring(8)) <= game.getPlayer().getMoney()){
                    if(game.getPlayer().getSpaceship().getTomatoGarden().shootNumberCanBeIncreased(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1)){
                        game.getPlayer().getSpaceship().getTomatoGarden().increaseShootNumber(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1);
                        game.getPlayer().setMoney(game.getPlayer().getMoney() - Integer.parseInt(textViewShootPrice.getText().toString().substring(8)));
                        actualizeLevelShoot();
                    } else showToast("Slot is already full !");
                } else showToast("You don't have enough money to buy this !");
            }
        });
        actualizeLevelShoot();
    }

    @Override
    public void onBackPressed(){
        changeActivity(TomatoBuyActivity.class);
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

    public void showToast(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    public void actualizeLevelShoot(){
        textViewSlotLevel.setText("Level " + game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1));
        if(game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1) == 10){
            textViewLevelPrice.setVisibility(View.GONE);
            buttonBuyLevel.setEnabled(false);
            buttonBuyLevel.setText("MAX LEVEL");
        } else {
            textViewLevelPrice.setText("Price : " + (Integer.parseInt(textViewSlotName.getText().toString().substring(5)) * game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1) * 2500));
        }
        textViewSlotShoots.setText(game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1) + " shoots");
        if(game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1) == 10){
            textViewShootPrice.setVisibility(View.GONE);
            buttonBuyShoot.setEnabled(false);
            buttonBuyShoot.setText("MAX SHOOTS");
        } else {
            textViewShootPrice.setText("Price : " + (Integer.parseInt(textViewSlotName.getText().toString().substring(5)) * game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1) * 1500));
        }
        switch (game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(Integer.parseInt(textViewSlotName.getText().toString().substring(5)) - 1)){
            case 1: imageViewSlot.setImageResource(R.drawable.harvest_level_1); break;
            case 2: imageViewSlot.setImageResource(R.drawable.harvest_level_2); break;
            case 3: imageViewSlot.setImageResource(R.drawable.harvest_level_3); break;
            case 4: imageViewSlot.setImageResource(R.drawable.harvest_level_4); break;
            case 5: imageViewSlot.setImageResource(R.drawable.harvest_level_5); break;
            case 6: imageViewSlot.setImageResource(R.drawable.harvest_level_6); break;
            case 7: imageViewSlot.setImageResource(R.drawable.harvest_level_7); break;
            case 8: imageViewSlot.setImageResource(R.drawable.harvest_level_8); break;
            case 9: imageViewSlot.setImageResource(R.drawable.harvest_level_9); break;
            case 10: imageViewSlot.setImageResource(R.drawable.harvest_level_10); break;
        }
    }

    @Override
    protected void onDestroy() {
        saveData(game);
        super.onDestroy();
    }
}
