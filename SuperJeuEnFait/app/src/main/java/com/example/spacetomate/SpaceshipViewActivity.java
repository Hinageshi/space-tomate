package com.example.spacetomate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SpaceshipViewActivity extends AppCompatActivity {

    private ImageButton imageButtonTomato;
    private ImageButton imageButtonEggplant;
    private ImageButton imageButtonPumpkin;
    private ImageButton imageButtonBroccoli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceship_view);
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
    }

    @Override
    public void onBackPressed(){
        changeActivity(MapActivity.class);
    }

    public void changeActivity(Class newActivity){
        Intent intent = new Intent(this, newActivity);
        startActivity(intent);
        finish();
    }
}
