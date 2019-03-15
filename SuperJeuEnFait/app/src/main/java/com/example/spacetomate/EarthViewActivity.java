package com.example.spacetomate;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EarthViewActivity extends AppCompatActivity {

    private ImageButton imageButtonEarthSpaceship;
    private ImageButton imageButtonEarthScientist;
    private Market market;
    private SalesCount playerSalesCount;
    private TextView textViewTomato;
    private TextView textViewEggplant;
    private TextView textViewPumpkin;
    private TextView textViewBroccoli;
    private ImageView imageViewEggplantPrice;
    private ImageView imageViewPumpkinPrice;
    private ImageView imageViewBroccoliPrice;
    private ImageView imageViewEggplantCoin;
    private ImageView imageViewPumpkinCoin;
    private ImageView imageViewBroccoliCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);
        imageButtonEarthSpaceship = findViewById(R.id.imageButtonEarthSpaceship);
        imageButtonEarthSpaceship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(MapActivity.class);;
            }
        });
        this.market=new Market();
        playerSalesCount=new SalesCount();
        textViewTomato=findViewById(R.id.textViewEarthTomato);
        textViewTomato.setText(market.getPriceTomato()+"");

        textViewEggplant=findViewById(R.id.textViewEarthEggplant);
        textViewEggplant.setText(market.getPriceEggplant()+"");
        textViewEggplant.setVisibility(TextView.INVISIBLE);
        imageViewEggplantPrice=findViewById(R.id.imageViewEggplantPrice);
        imageViewEggplantPrice.setVisibility(ImageView.INVISIBLE);
        imageViewEggplantCoin=findViewById(R.id.imageViewEggplantCoin);
        imageViewEggplantCoin.setVisibility(ImageView.INVISIBLE);

        textViewPumpkin = findViewById(R.id.textViewEarthPumpkin);
        textViewPumpkin.setText(market.getPricePumpkin()+"");
        textViewPumpkin.setVisibility(TextView.INVISIBLE);
        imageViewPumpkinPrice=findViewById(R.id.imageViewPumpkinPrice);
        imageViewPumpkinPrice.setVisibility(ImageView.INVISIBLE);
        imageViewPumpkinCoin=findViewById(R.id.imageViewPumpkinCoin);
        imageViewPumpkinCoin.setVisibility(ImageView.INVISIBLE);


        textViewBroccoli = findViewById(R.id.textViewEarthBroccoli);
        textViewBroccoli.setText(market.getPriceBroccoli()+"");
        textViewBroccoli.setVisibility(TextView.INVISIBLE);
        imageViewBroccoliPrice=findViewById(R.id.imageViewBroccoliPrice);
        imageViewBroccoliPrice.setVisibility(ImageView.INVISIBLE);
        imageViewBroccoliCoin=findViewById(R.id.imageViewBroccoliCoin);
        imageViewBroccoliCoin.setVisibility(ImageView.INVISIBLE);


        if(Game.numberOfProducts>=2) {
            textViewEggplant.setVisibility(TextView.VISIBLE);
            imageViewEggplantPrice.setVisibility(ImageView.VISIBLE);
            imageViewEggplantCoin.setVisibility(ImageView.VISIBLE);
            if(Game.numberOfProducts>=3) {
                textViewPumpkin.setVisibility(TextView.VISIBLE);
                imageViewPumpkinPrice.setVisibility(ImageView.VISIBLE);
                imageViewPumpkinCoin.setVisibility(ImageView.VISIBLE);
                if(Game.numberOfProducts>=4) {
                    textViewBroccoli.setVisibility(TextView.VISIBLE);
                    imageViewBroccoliPrice.setVisibility(ImageView.VISIBLE);
                    imageViewBroccoliCoin.setVisibility(ImageView.VISIBLE);
                }

            }
        }
        imageButtonEarthScientist=findViewById(R.id.imageButtonEarthScientist);
        imageButtonEarthScientist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /*imageButtonEarthTest = findViewById(R.id.imageButtonEarthTest);
        imageButtonEarthTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMarket();
                playerSalesCount.clean();
            }
        });*/

    }

    @Override
    public void onBackPressed(){
        //disable back button
    }

    private void updateMarket(){
        market.update(playerSalesCount);
        textViewTomato.setText("Tomato: "+market.getPriceTomato()+"G");
        textViewEggplant.setText("Eggplant: "+market.getPriceEggplant()+"G");
        textViewPumpkin.setText("Pumpkin: "+market.getPricePumpkin()+"G");
        textViewBroccoli.setText("Broccoli: "+market.getPriceBroccoli()+"G");
    }

    public void changeActivity(Class newActivity){
        Intent intent = new Intent(this, newActivity);
        startActivity(intent);
        finish();
    }
}
