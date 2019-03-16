package com.example.superjeuenfait;

import android.os.Bundle;

public class EggplantDustActivity extends DustActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setImageId(R.drawable.eggplant);
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
}
