package com.example.superjeuenfait;

import java.util.concurrent.ThreadLocalRandom;

public class PricesHandler {

    private SalesCount salesCount;
    private int prices[];
    private DemandHandler demandHandler;
    //TO ADD: value for time, to update every hour
    private static final int maxPrice=100;
    private static final int minPrice=10;
    private static final double demandImpact=0.1;
    private int numberOfProducts;

    public PricesHandler(int numberOfProducts){
        salesCount=new SalesCount();
        this.numberOfProducts=numberOfProducts;
        prices=new int[this.numberOfProducts];
        for(int i=0;i<numberOfProducts;i++) {
            prices[i] = (maxPrice-minPrice)/2;
        }
        demandHandler=new DemandHandler(numberOfProducts);
    }

    //Also generate sales that are not from the player
    protected void updateSalesCount(SalesCount playerSalesCount){
        salesCount.clean();
        if(numberOfProducts>=2) {
            int salesToSimulate = (demandHandler.getDemandsPerDay()) - playerSalesCount.getTotalSales() - (5 * (4-numberOfProducts));
            if (salesToSimulate > 0) {
                int probaTomato = demandHandler.getProbaTomato();
                int probaEggplant = demandHandler.getProbaEggplant();
                int probaPumpkin = demandHandler.getProbaPumpkin();
                for (int i = 0; i < salesToSimulate; i++) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, demandHandler.sumProba());
                    if (randomNum <= probaTomato) {
                        salesCount.addSale(Products.TOMATO);
                    } else {
                        if (randomNum <= (probaTomato + probaEggplant)) {
                            salesCount.addSale(Products.EGGPLANT);
                        } else {
                            if (randomNum <= probaTomato + probaEggplant + probaPumpkin) {
                                salesCount.addSale(Products.PUMPKIN);
                            } else {
                                salesCount.addSale(Products.BROCCOLI);
                            }
                        }
                    }
                }
                salesCount.addMultipleSale(Products.EGGPLANT, playerSalesCount.getProductSales(Products.EGGPLANT));
                salesCount.addMultipleSale(Products.PUMPKIN, playerSalesCount.getProductSales(Products.PUMPKIN));
                salesCount.addMultipleSale(Products.BROCCOLI, playerSalesCount.getProductSales(Products.BROCCOLI));
            }
        }
        salesCount.addMultipleSale(Products.TOMATO,playerSalesCount.getProductSales(Products.TOMATO));

    }

    protected void updatePrices(){
        demandHandler.updateDemands(salesCount);
        if(numberOfProducts>=2) {
            float demandsPerDay = (float) demandHandler.getDemandsPerDay() / 2;
            float newPrice;
            newPrice = (float) prices[0] * ((float) (demandHandler.getDemandsTomate() + 5) / demandsPerDay);
            prices[0] = Math.round(newPrice);
            newPrice = (float) prices[1] * ((float) (demandHandler.getDemandsEggplant() + 5) / demandsPerDay);
            prices[1] = Math.round(newPrice);
            if(numberOfProducts>=3) {
                newPrice = (float) prices[2] * ((float) (demandHandler.getDemandsPumpkin() + 5) / demandsPerDay);
                prices[2] = Math.round(newPrice);
                if(numberOfProducts>=4) {
                    newPrice = (float) prices[3] * ((float) (demandHandler.getDemandsBroccoli() + 5) / demandsPerDay);
                    prices[3] = Math.round(newPrice);
                }
            }
            for (int i = 0; i < numberOfProducts; i++) {
                if (prices[i] > maxPrice) {
                    prices[i] = maxPrice;
                } else {
                    if (prices[i] < minPrice)
                        prices[i] = minPrice;
                }
            }
        }
        else{
            int tomatoSold=salesCount.getProductSales(Products.TOMATO);
            if(tomatoSold>0){
                prices[0]-=(tomatoSold*2);
                if(prices[0]<minPrice) {
                    prices[0]=minPrice;
                }
            }
            else{
                prices[0]+=5;
                if(prices[0]>maxPrice) {
                    prices[0]=maxPrice;
                }
            }
        }

    }

    protected void update(SalesCount playerSalesCount){
        updateSalesCount(playerSalesCount);
        updatePrices();
    }

    public int getPriceTomato(){
        return prices[0];
    }
    public int getPriceEggplant(){
        if(numberOfProducts>=2)
            return prices[1];
        else
            return 0;
    }
    public int getPricePumpkin(){
        if(numberOfProducts>=3)
            return prices[2];
        else
            return 0;
    }
    public int getPriceBroccoli(){
        if(numberOfProducts>=4)
            return prices[3];
        else
            return 0;
    }
}
