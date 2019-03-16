package com.example.superjeuenfait;

import android.content.Intent;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.superjeuenfait.MapActivity;
import com.example.superjeuenfait.R;

import java.util.ArrayList;
import java.util.Random;

public abstract class DustActivity extends AppCompatActivity {

    private ImageView imageViewSpaceship;
    private ImageView imageViewVegetable;
    private TextView textViewDustNumber;
    private TextView textViewTimeRemaining;
    private LinearLayout linearLayout;
    private RelativeLayout mainView;
    static int minX, maxX, minY, maxY;
    private int imageId;
    ArrayList<ImageView> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dust);
        linearLayout = findViewById(R.id.linearLayout);
        mainView = findViewById(R.id.mainView);
        imageViewVegetable = findViewById(R.id.imageViewVegetable);
        imageViewVegetable.setImageResource(imageId);
        imageViewSpaceship = findViewById(R.id.imageViewSpaceship);
        imageViewSpaceship.setOnTouchListener(new View.OnTouchListener() {
            float x = 0, y = 0, dx = 0, dy = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN :{
                        dx = event.getRawX();
                        dy = event.getRawY();
                    } break;

                    case MotionEvent.ACTION_MOVE : {
                        x = event.getRawX();
                        if(x >= minX && x <= maxX){
                            imageViewSpaceship.setX(x - (imageViewSpaceship.getWidth()/2) );
                        }

                    } break;
                }
                return true;
            }
        });
        minX = linearLayout.getPaddingLeft() + (imageViewSpaceship.getWidth()/4);
        maxX = getWindowManager().getDefaultDisplay().getWidth() - linearLayout.getPaddingRight() - (imageViewSpaceship.getWidth()/4);
        textViewDustNumber = findViewById(R.id.textViewDustNumber);
        textViewTimeRemaining = findViewById(R.id.textViewTimeRemaining);
        CountDownTimer countDownTimer = new CountDownTimer(10000, 500){
            public void onTick(long millisUntilFinished){
                String minutes = String.valueOf((millisUntilFinished / 1000) / 60);
                String secondes = "";
                if((millisUntilFinished / 1000) % 60 < 10){
                    secondes = "0" + String.valueOf((millisUntilFinished / 1000) % 60);
                } else {
                    secondes = String.valueOf((millisUntilFinished / 1000) % 60);
                }
                textViewTimeRemaining.setText(minutes + ":" + secondes);
                addTomatoes(1);
            }

            public void onFinish(){
                changeActivity(MapActivity.class);
                finish();
            }
        }.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        minX = linearLayout.getPaddingLeft() + (imageViewSpaceship.getWidth()/4);
        maxX = getWindowManager().getDefaultDisplay().getWidth() - linearLayout.getPaddingRight() - (imageViewSpaceship.getWidth()/4);
        maxY = (int)imageViewSpaceship.getY();
    }

    public void addTomatoes(int nb){
        Random r = new Random();
        for(int i = 0; i < nb; ++i){
            final ImageView imageview = new ImageView(this);
            imageview.setImageResource(this.imageId);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100,100);
            imageview.setLayoutParams(params);
            imageview.setX(r.nextInt(maxX - minX) + minX);
            imageview.setY(200);
            mainView.addView(imageview);
            images.add(imageview);
            TranslateAnimation animation = new TranslateAnimation((int) imageview.getScaleX(), (int) imageview.getScaleX(), (int) imageview.getScaleY(), maxY);
            animation.setDuration(1000);
            imageview.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if(checkCollision(imageview, imageViewSpaceship)) addOnePoint();
                    mainView.removeView(imageview);
                    images.remove(imageview);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

    }

    public boolean checkCollision(View v1, View v2){
        Rect R1 = new Rect((int)v1.getX(),(int)(v1.getY() + 1229),(int)(v1.getX() + v1.getWidth()),(int)(v1.getY() + 1229 + v1.getHeight()));
        Rect R2 = new Rect((int)v2.getX(),(int)v2.getY(),(int)(v1.getX() + v2.getWidth()),(int)(v2.getY() + v2.getHeight()));
        return R1.intersect(R2);
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

    public void setImageId(int imageId){
        this.imageId = imageId;
    }

    public void addOnePoint(){
        int nbPoints = Integer.parseInt(textViewDustNumber.getText().toString());
        nbPoints++;
        textViewDustNumber.setText(String.valueOf(nbPoints));
    }
}
