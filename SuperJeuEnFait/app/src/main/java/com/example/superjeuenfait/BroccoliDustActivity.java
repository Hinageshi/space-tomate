package com.example.superjeuenfait;

import android.os.Bundle;

public class BroccoliDustActivity extends DustActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setImageId(R.drawable.broccoli);
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
