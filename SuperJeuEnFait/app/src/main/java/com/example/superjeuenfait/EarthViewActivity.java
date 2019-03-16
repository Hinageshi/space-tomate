package com.example.superjeuenfait;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class EarthViewActivity extends AppCompatActivity {

    private Game game;
    private SharedPreferences sharedPreferences;
    private ImageButton imageButtonEarthSpaceship;
    private ImageButton imageButtonEarthScientist;
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
    private ConstraintLayout marketWindow;
    private boolean marketVisible;
    private ImageButton arrowLeftVegetable;
    private ImageButton arrowRightVegetable;
    private ImageButton arrowLeftQuantity;
    private ImageButton arrowRightQuantity;
    private ImageView chosenVegetable;
    private Products chosenProduct;
    private TextView quantity;
    private int quantityValue;
    private TextView price;
    private int priceCalculated;
    private ImageButton validate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);

        sharedPreferences=getBaseContext().getSharedPreferences("GAME",MODE_PRIVATE);
        Gson gson = new Gson();
        String json= sharedPreferences.getString("GAME_DATA",null);
        game=gson.fromJson(json,Game.class);

        imageButtonEarthSpaceship = findViewById(R.id.imageButtonEarthSpaceship);
        imageButtonEarthSpaceship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(MapActivity.class);;
            }
        });
        textViewTomato=findViewById(R.id.textViewEarthTomato);
        System.out.println(game.getMarket().getPriceTomato());
        textViewTomato.setText(game.getMarket().getPriceTomato()+"");

        textViewEggplant=findViewById(R.id.textViewEarthEggplant);
        textViewEggplant.setText(game.getMarket().getPriceEggplant()+"");
        textViewEggplant.setVisibility(TextView.INVISIBLE);
        imageViewEggplantPrice=findViewById(R.id.imageViewEggplantPrice);
        imageViewEggplantPrice.setVisibility(ImageView.INVISIBLE);
        imageViewEggplantCoin=findViewById(R.id.imageViewEggplantCoin);
        imageViewEggplantCoin.setVisibility(ImageView.INVISIBLE);

        textViewPumpkin = findViewById(R.id.textViewEarthPumpkin);
        textViewPumpkin.setText(game.getMarket().getPricePumpkin()+"");
        textViewPumpkin.setVisibility(TextView.INVISIBLE);
        imageViewPumpkinPrice=findViewById(R.id.imageViewPumpkinPrice);
        imageViewPumpkinPrice.setVisibility(ImageView.INVISIBLE);
        imageViewPumpkinCoin=findViewById(R.id.imageViewPumpkinCoin);
        imageViewPumpkinCoin.setVisibility(ImageView.INVISIBLE);


        textViewBroccoli = findViewById(R.id.textViewEarthBroccoli);
        textViewBroccoli.setText(game.getMarket().getPriceBroccoli()+"");
        textViewBroccoli.setVisibility(TextView.INVISIBLE);
        imageViewBroccoliPrice=findViewById(R.id.imageViewBroccoliPrice);
        imageViewBroccoliPrice.setVisibility(ImageView.INVISIBLE);
        imageViewBroccoliCoin=findViewById(R.id.imageViewBroccoliCoin);
        imageViewBroccoliCoin.setVisibility(ImageView.INVISIBLE);


        if(game.getUnlockedProducts()>=2) {
            textViewEggplant.setVisibility(TextView.VISIBLE);
            imageViewEggplantPrice.setVisibility(ImageView.VISIBLE);
            imageViewEggplantCoin.setVisibility(ImageView.VISIBLE);
            if(game.getUnlockedProducts()>=3) {
                textViewPumpkin.setVisibility(TextView.VISIBLE);
                imageViewPumpkinPrice.setVisibility(ImageView.VISIBLE);
                imageViewPumpkinCoin.setVisibility(ImageView.VISIBLE);
                if(game.getUnlockedProducts()>=4) {
                    textViewBroccoli.setVisibility(TextView.VISIBLE);
                    imageViewBroccoliPrice.setVisibility(ImageView.VISIBLE);
                    imageViewBroccoliCoin.setVisibility(ImageView.VISIBLE);
                }

            }
        }

        checkUpdate();

        marketWindow=findViewById(R.id.marketWindow);
        marketWindow.setVisibility(View.INVISIBLE);
        marketVisible=false;

        arrowLeftVegetable=findViewById(R.id.buttonEarthPreviousVegetable);
        arrowLeftVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (chosenProduct){
                    case EGGPLANT:
                        chosenProduct=Products.TOMATO;
                        break;
                    case PUMPKIN:
                        chosenProduct=Products.EGGPLANT;
                        break;
                    case BROCCOLI:
                        chosenProduct=Products.PUMPKIN;
                        break;
                    default:
                        chosenProduct=Products.TOMATO;
                        break;
                }
                updateVegetableView();
                setQuantityWhenChangeProduct();
                updateQuantityView();
                updatePrice();
                updateValidateButton();
            }
        });
        arrowRightVegetable=findViewById(R.id.buttonEarthNextVegetable);
        arrowRightVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (chosenProduct){
                    case EGGPLANT:
                        chosenProduct=Products.PUMPKIN;
                        break;
                    case PUMPKIN:
                        chosenProduct=Products.BROCCOLI;
                        break;
                    case TOMATO:
                        chosenProduct=Products.EGGPLANT;
                        break;
                    default:
                        chosenProduct=Products.TOMATO;
                        break;
                }
                updateVegetableView();
                setQuantityWhenChangeProduct();
                updateQuantityView();
                updatePrice();
                updateValidateButton();
            }
        });
        arrowLeftQuantity=findViewById(R.id.imageButtonQuantityLess);
        arrowLeftQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantityValue==1){
                    quantityValue=game.getPlayer().getStockProducts(chosenProduct);
                    if(quantityValue>Game.maxProductPerSale){
                        quantityValue=Game.maxProductPerSale;
                    }
                }
                else{
                    quantityValue--;
                }
                updateQuantityView();
                updatePrice();
                updateValidateButton();
            }
        });
        arrowRightQuantity=findViewById(R.id.imageButtonQuantityMore);
        arrowRightQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantityValue>=game.getPlayer().getStockProducts(chosenProduct) || quantityValue>=Game.maxProductPerSale){
                    quantityValue=1;
                }
                else{
                    quantityValue++;
                }
                updateQuantityView();
                updatePrice();
                updateValidateButton();
            }
        });
        chosenVegetable=findViewById(R.id.imageViewChosenVegetable);
        quantity=findViewById(R.id.textEarthQuantity);
        price=findViewById(R.id.textTotalPrice);
        validate=findViewById(R.id.imageButtonEarthValidate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    game.getPlayer().removeProductStock(chosenProduct, quantityValue);
                    game.addSale(chosenProduct,quantityValue);
                    game.getPlayer().addMoney(priceCalculated);
                }catch(Exception e){};
                setQuantityWhenChangeProduct();
                updatePrice();
                updateValidateButton();

            }
        });


        imageButtonEarthScientist=findViewById(R.id.imageButtonEarthScientist);
        imageButtonEarthScientist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(marketVisible){
                    marketWindow.setVisibility(View.GONE);
                    marketVisible=false;
                }
                else{
                    chosenProduct=Products.TOMATO;
                    setQuantityWhenChangeProduct();
                    updateVegetableView();
                    updateQuantityView();
                    updatePrice();
                    updateValidateButton();
                    marketWindow.setVisibility(View.VISIBLE);
                    marketVisible=true;

                }
            }
        });

    }


    @Override
    public void onBackPressed(){
        if(marketVisible){
            marketVisible=false;
            marketWindow.setVisibility(View.GONE);
        }
    }

    public void setArrowProductVisible(){
        switch(chosenProduct){
            case TOMATO:
                arrowLeftVegetable.setVisibility(View.INVISIBLE);
                if(game.getUnlockedProducts()<2){
                    arrowRightVegetable.setVisibility(View.INVISIBLE);
                }
                else{
                    arrowRightVegetable.setVisibility(View.VISIBLE);
                }
                break;
            case EGGPLANT:
                arrowLeftVegetable.setVisibility(View.VISIBLE);
                if(game.getUnlockedProducts()<3){
                    arrowRightVegetable.setVisibility(View.INVISIBLE);
                }
                else{
                    arrowRightVegetable.setVisibility(View.VISIBLE);
                }
                break;
            case PUMPKIN:
                arrowLeftVegetable.setVisibility(View.VISIBLE);
                if(game.getUnlockedProducts()<4){
                    arrowRightVegetable.setVisibility(View.INVISIBLE);
                }
                else{
                    arrowRightVegetable.setVisibility(View.VISIBLE);
                }
                break;
            case BROCCOLI:
                arrowLeftVegetable.setVisibility(View.VISIBLE);
                arrowRightVegetable.setVisibility(View.INVISIBLE);
                break;
            default:
                break;

        }
    }

    public void updateVegetableView(){
        switch(chosenProduct){
            case TOMATO:
                chosenVegetable.setImageResource(R.drawable.tomato);
                break;
            case EGGPLANT:
                chosenVegetable.setImageResource(R.drawable.eggplant);
                break;
            case PUMPKIN:
                chosenVegetable.setImageResource(R.drawable.pumpkin);
                break;
            case BROCCOLI:
                chosenVegetable.setImageResource(R.drawable.broccoli);
                break;
            default:
                break;
        }
        setArrowProductVisible();

    }

    public void setQuantityWhenChangeProduct(){
        arrowLeftQuantity.setVisibility(View.INVISIBLE);
        arrowRightQuantity.setVisibility(View.INVISIBLE);
        int q=game.getPlayer().getStockProducts(chosenProduct);
        if(q==0){
            quantity.setText("0");
            quantityValue=0;
        }
        else{
            quantity.setText("1");
            quantityValue=1;
            if(q>1){
                arrowRightQuantity.setVisibility(View.VISIBLE);
            }
        }

    }

    public void setArrowQuantityVisible(){
        if(quantityValue==0 || (quantityValue==1 && game.getPlayer().getStockProducts(chosenProduct)==1)){
            arrowLeftQuantity.setVisibility(View.INVISIBLE);
        }
        else{
            arrowLeftQuantity.setVisibility(View.VISIBLE);
        }
        if(quantityValue<game.getPlayer().getStockProducts(chosenProduct)){
            arrowRightQuantity.setVisibility(View.VISIBLE);
        }
        else{
            arrowRightQuantity.setVisibility(View.INVISIBLE);
        }
    }

    public void updateQuantityView(){
        setArrowQuantityVisible();
        quantity.setText(quantityValue+"");
    }

    public void updatePrice(){
        priceCalculated=(game.getMarket().getPriceProduct(chosenProduct))*quantityValue;
        price.setText("Total: "+priceCalculated);
    }

    public void updateValidateButton(){
        if(priceCalculated<=0 || quantityValue>(game.getPlayer().getStockProducts(chosenProduct))){
            validate.setVisibility(View.INVISIBLE);
        }
        else{
            validate.setVisibility(View.VISIBLE);
        }
    }

    public void saveData(Game game){
        Gson gson = new Gson();
        sharedPreferences.edit()
                .putString("GAME_DATA",gson.toJson(game))
                .apply();
    }

    private void updateMarketPrint(){
        textViewTomato.setText(game.getMarket().getPriceTomato()+"");
        textViewEggplant.setText(game.getMarket().getPriceEggplant()+"");
        textViewPumpkin.setText(game.getMarket().getPricePumpkin()+"");
        textViewBroccoli.setText(game.getMarket().getPriceBroccoli()+"");
    }

    private void checkUpdate(){
        if(game.timeToUpdate()){
            System.out.println("Update!");
            game.performGameUpdate();
            updateMarketPrint();
        }
    }

    public void changeActivity(Class newActivity){
        Intent intent = new Intent(this, newActivity);
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
