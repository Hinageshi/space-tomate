package com.example.spacetomate;

public class Market {

    private PricesHandler pricesHandler;
    private SalesHistory salesHistory;

    public Market(){
        pricesHandler= new PricesHandler(Game.numberOfProducts);
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

    public void addProduct(){
        pricesHandler=new PricesHandler(Game.numberOfProducts);
        salesHistory=new SalesHistory();
    }

}
