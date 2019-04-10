package com.example.superjeuenfait;

public class Player {

    private String name;
    private int money;
    private SalesHistory playerSalesHistory;
    private int stockProducts[];
    private int stockDust[];
    private Spaceship spaceship;
    private int unlockedProducts;

    public Player(String n){
        name=n;
        money=0;
        unlockedProducts=1;
        playerSalesHistory=new SalesHistory();
        stockProducts=new int[Game.numberOfProductsMax];
        stockDust=new int[Game.numberOfProductsMax];
        for(int i=0;i<Game.numberOfProductsMax;i++){
            stockDust[i]=0;
            stockProducts[i]=0;
        }
        spaceship=new Spaceship();

    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public SalesHistory getPlayerSalesHistory() {
        return playerSalesHistory;
    }

    public int getUnlockedProducts() {
        return unlockedProducts;
    }

    public int getStockDust(Products p) {
        switch (p){
            case TOMATO:
                return stockDust[0];
            case EGGPLANT:
                return stockDust[1];
            case PUMPKIN:
                return stockDust[2];
            case BROCCOLI:
                return stockDust[3];
            default:
                return 0;
        }
    }

    public int getStockProducts(Products p) {
        switch (p){
            case TOMATO:
                return stockProducts[0];
            case EGGPLANT:
                return stockProducts[1];
            case PUMPKIN:
                return stockProducts[2];
            case BROCCOLI:
                return stockProducts[3];
            default:
                return 0;
        }
    }

    public void addMoney(int quantity){
        money+=quantity;
    }

    public void removeMoney(int quantity) throws NotEnoughException{
        if(money>=quantity){
            money-=quantity;
        }
        else
            throw new NotEnoughException("Not enough products to remove such quantity");
    }

    public void addDustStock(Products p,int quantity){
        switch (p){
            case TOMATO:
                stockDust[0]+=quantity;
                break;
            case EGGPLANT:
                stockDust[1]+=quantity;
                break;
            case PUMPKIN:
                stockDust[2]+=quantity;
                break;
            case BROCCOLI:
                stockDust[3]+=quantity;
                break;
            default:
                break;
        }
    }

    public void removeDustStock(Products p,int quantity) throws NotEnoughException{
        switch (p){
            case TOMATO:
                if(stockDust[0]>=quantity) {
                    stockDust[0] -= quantity;
                }
                else{
                    throw new NotEnoughException("Not enough dust to remove such quantity");
                }
                break;
            case EGGPLANT:
                if(stockDust[1]>=quantity) {
                    stockDust[1] -= quantity;
                }
                else{
                    throw new NotEnoughException("Not enough dust to remove such quantity");
                }
                break;
            case PUMPKIN:
                if(stockDust[2]>=quantity) {
                    stockDust[2] -= quantity;
                }
                else{
                    throw new NotEnoughException("Not enough dust to remove such quantity");
                }
                break;
            case BROCCOLI:
                if(stockDust[3]>=quantity) {
                    stockDust[3] -= quantity;
                }
                else{
                    throw new NotEnoughException("Not enough dust to remove such quantity");
                }
                break;
            default:
                break;
        }
    }

    public void addProductStock(Products p,int quantity){
        switch (p){
            case TOMATO:
                stockProducts[0]+=quantity;
                break;
            case EGGPLANT:
                stockProducts[1]+=quantity;
                break;
            case PUMPKIN:
                stockProducts[2]+=quantity;
                break;
            case BROCCOLI:
                stockProducts[3]+=quantity;
                break;
            default:
                break;
        }
    }

    public void removeProductStock(Products p,int quantity) throws NotEnoughException{
        switch (p){
            case TOMATO:
                if(stockProducts[0]>=quantity) {
                    stockProducts[0] -= quantity;
                }
                else{
                    throw new NotEnoughException("Not enough product to remove such quantity");
                }
                break;
            case EGGPLANT:
                if(stockProducts[1]>=quantity) {
                    stockProducts[1] -= quantity;
                }
                else{
                    throw new NotEnoughException("Not enough product to remove such quantity");
                }
                break;
            case PUMPKIN:
                if(stockProducts[2]>=quantity) {
                    stockProducts[2] -= quantity;
                }
                else{
                    throw new NotEnoughException("Not enough product to remove such quantity");
                }
                break;
            case BROCCOLI:
                if(stockProducts[3]>=quantity) {
                    stockProducts[3] -= quantity;
                }
                else{
                    throw new NotEnoughException("Not enough product to remove such quantity");
                }
                break;
            default:
                break;
        }
    }

    public void unlockNewProduct(){
        if(unlockedProducts<Game.numberOfProductsMax){
            spaceship.unlockNextGarden();
            unlockedProducts++;
        }
    }

    public void addSale(Products p, int price){
        playerSalesHistory.addSale(new Sale(p,price));
    }
}
