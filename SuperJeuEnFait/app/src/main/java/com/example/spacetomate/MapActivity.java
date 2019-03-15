package com.example.spacetomate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MapActivity extends AppCompatActivity {

    private ImageButton exitButton;
    private ImageButton imageButtonSpaceship;
    private ImageButton imageButtonEarth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
    }

    @Override
    public void onBackPressed(){
        //disable back button
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
}
