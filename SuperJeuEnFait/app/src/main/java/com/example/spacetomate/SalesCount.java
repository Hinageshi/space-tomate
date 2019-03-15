package com.example.spacetomate;

//Invisible to player, use to set prices
public class SalesCount {

    private int productsSold[];

    public SalesCount(){
        productsSold=new int[4];
        for(int i=0; i<4; i++)
            productsSold[i]=0;
    }


    private int getTomato() {
        return productsSold[0];
    }

    private int getEggplant() {
        return productsSold[1];
    }

    private int getPumpkin() {
        return productsSold[2];
    }

    private int getBroccoli() {
        return productsSold[3];
    }

    public void setBroccoli(int broccoli) {
        this.productsSold[3] = broccoli;
    }

    public void setEggplant(int eggplant) {
        this.productsSold[1] = eggplant;
    }

    public void setPumpkin(int pumpkin) {
        this.productsSold[2] = pumpkin;
    }

    public void setTomato(int tomato) {
        this.productsSold[0] = tomato;
    }

    public void addSale(Products p){
        switch (p) {
            case TOMATO:
                productsSold[0]++;
                break;
            case EGGPLANT:
                productsSold[1]++;
                break;
            case PUMPKIN:
                productsSold[2]++;
                break;
            case BROCCOLI:
                productsSold[3]++;
                break;
            default:
                break;
        }
    }

    public void addMultipleSale(Products p, int number){
        switch (p) {
            case TOMATO:
                productsSold[0]+=number;
                break;
            case EGGPLANT:
                productsSold[1]+=number;
                break;
            case PUMPKIN:
                productsSold[2]+=number;
                break;
            case BROCCOLI:
                productsSold[3]+=number;
                break;
            default:
                break;
        }
    }

    public int getProductSales(Products p){
        switch (p){
            case TOMATO:
                return getTomato();
            case EGGPLANT:
                return getEggplant();
            case PUMPKIN:
                return getPumpkin();
            case BROCCOLI:
                return getBroccoli();
            default:
                return 0;

        }
    }
    public int getTotalSales(){
        return productsSold[0]+productsSold[1]+productsSold[2]+productsSold[3];
    }

    public float getMarketShare(Products p){
        float saleProduct=(float)getProductSales(p);
        float totalSale=(float)getTotalSales();
        if(totalSale==0)
            return 0;
        else
            return((saleProduct/totalSale)*100);
    }

    public void clean(){
        for(int i=0; i<4; i++)
            productsSold[i]=0;
    }




}
