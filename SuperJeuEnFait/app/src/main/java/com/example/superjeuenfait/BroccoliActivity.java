package com.example.superjeuenfait;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class BroccoliActivity extends AppCompatActivity {

    private ImageButton imageButtonArrowLeft;
    private ImageButton imageButtonArrowRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broccoli);
        imageButtonArrowLeft = findViewById(R.id.imageButtonArrowLeft);
        imageButtonArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(PumpkinActivity.class);
            }
        });

        imageButtonArrowRight = findViewById(R.id.imageButtonArrowRight);
        imageButtonArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(TomatoActivity.class);
            }
        });

    }

    @Override
    public void onBackPressed(){
        changeActivity(SpaceshipViewActivity.class);
    }

    public void changeActivity(Class newActivity){
        Intent intent = new Intent(this, newActivity);
        startActivity(intent);
        finish();
    }
}
