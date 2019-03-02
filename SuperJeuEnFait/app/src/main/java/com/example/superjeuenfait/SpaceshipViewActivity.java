package com.example.superjeuenfait;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SpaceshipViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceship_view);
    }

    @Override
    public void onBackPressed(){
        //disable back button
    }
}
