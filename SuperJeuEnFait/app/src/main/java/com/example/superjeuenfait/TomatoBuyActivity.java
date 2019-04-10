package com.example.superjeuenfait;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TomatoBuyActivity extends AppCompatActivity {

    private List<Article> articles = new ArrayList<Article>();
    private ListView listViewTomatoSlots;
    ArticleAdapter articleAdapter;
    private Game game;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomato_buy);

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
        for(int i = 1; i < 10; i++){
            if(this.game.getPlayer().getSpaceship().getTomatoGarden().slotAvailable(i-1)){
                articles.add(new Article("Slot " + i, 0));
            } else {
                articles.add(new Article("Slot " + i, 2500*i));
            }
        }

        articleAdapter = new ArticleAdapter(this, this.articles);
        this.listViewTomatoSlots = (ListView)findViewById(R.id.listViewTomatoSlots);
        this.listViewTomatoSlots.setAdapter(articleAdapter);
        listViewTomatoSlots.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(((Article)adapterView.getAdapter().getItem(i)).getPrice() == 0){
                    changeActivity(TomatoSlotActivity.class, i+1, game.getPlayer().getSpaceship().getTomatoGarden().getLevelSlot(i), game.getPlayer().getSpaceship().getTomatoGarden().getShootPerSlot(i));
                } else {
                    if(i == game.getPlayer().getSpaceship().getTomatoGarden().getNextSlotToUnlock()){
                        if(game.getPlayer().getMoney() >= ((Article)adapterView.getAdapter().getItem(i)).getPrice()){
                            game.getPlayer().getSpaceship().getTomatoGarden().unlockNextSlot();
                            game.getPlayer().setMoney(game.getPlayer().getMoney() - ((Article)adapterView.getAdapter().getItem(i)).getPrice());
                            articles.get(i).setPrice(0);
                            articleAdapter.notifyDataSetChanged();
                            listViewTomatoSlots.setAdapter(articleAdapter);
                        } else showToast("You don't have enough money to buy this !");
                    } else showToast("You can't unlock this slot unless you unlocked previous slots before.");
                }
            }
        });


    }

    @Override
    public void onBackPressed(){
        changeActivity(TomatoActivity.class);
    }

    public void showToast(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
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

    public void changeActivity(Class newActivity, int nb, int level, int shoot){
        Intent intent = new Intent(this, newActivity);
        intent.putExtra("SLOT_NUMBER", nb);
        intent.putExtra("SLOT_LEVEL", level);
        intent.putExtra("SLOT_SHOOT", shoot);
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
