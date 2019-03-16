package com.example.superjeuenfait;

public class Market {

    private PricesHandler pricesHandler;
    private SalesHistory salesHistory;
    private int numberOfProducts;

    public Market(int numberOfProducts){
        this.numberOfProducts=numberOfProducts;
        pricesHandler= new PricesHandler(this.numberOfProducts);
        salesHistory=new SalesHistory();
    }

    public void update(SalesCount playerSalesCount){
        pricesHandler.update(playerSalesCount);
    }

    public int getPriceTomato(){
        return pricesHandler.getPriceTomato();
    }

    public int getPriceEggplant(){
        return pricesHandler.getPriceEggplant();
    }

    public int getPricePumpkin(){
        return pricesHandler.getPricePumpkin();
    }

    public int getPriceBroccoli(){
        return pricesHandler.getPriceBroccoli();
    }

    public int getPriceProduct(Products p){
        switch(p){
            case TOMATO:
                return getPriceTomato();
            case PUMPKIN:
                return getPricePumpkin();
            case EGGPLANT:
                return getPriceEggplant();
            case BROCCOLI:
                return getPriceBroccoli();
            default:
                return 0;
        }
    }

    public void addProduct(){
        pricesHandler=new PricesHandler(numberOfProducts);
        salesHistory=new SalesHistory();
    }

}
