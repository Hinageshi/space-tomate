package com.example.superjeuenfait;

import android.os.Bundle;

public class PumpkinDustActivity extends DustActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setImageId(R.drawable.pumpkin);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
