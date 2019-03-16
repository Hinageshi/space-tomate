package com.example.superjeuenfait;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;

public class DemandHandler {

    private SalesCount salesCount;
    private int proba[];
    private int demands[];
    private static final int demandsPerDay=20;
    private int numberOfProducts;

    public DemandHandler(int numberOfProducts){
        this.numberOfProducts=numberOfProducts;
        salesCount=new SalesCount();
        proba=new int[this.numberOfProducts];
        for(int i=0;i<numberOfProducts;i++){
            proba[i]=Math.round(100/(float)this.numberOfProducts);
        }
        demands=new int[this.numberOfProducts];
        for(int i=0;i<this.numberOfProducts;i++){
            demands[i]=Math.round((float)demandsPerDay/(float)numberOfProducts);
        }
    }

    public int updateProductProba(float marketShare, float demandShare, int previousProductProba){
        float variable= marketShare/demandShare;
        float updatedProba;
        if(variable<1) {
            updatedProba=(float)previousProductProba*(2-variable);
        }
        else{
            updatedProba=(float)previousProductProba*(1.5f-(0.5f*variable));
        }
        int res=Math.round(updatedProba);
        if(res<5){
            res=5;
        }
        if(res>(100-(5*(numberOfProducts-1)))){
            res=100-(5*(numberOfProducts-1));
        }
        return res;
    }
    public void updateProba(SalesCount s){
        salesCount=s;
        proba[0]=updateProductProba(s.getMarketShare(Products.TOMATO),((float)demands[0]/(float)demandsPerDay)*100f, proba[0]);
        if(numberOfProducts>=2) {
            proba[1] = updateProductProba(s.getMarketShare(Products.EGGPLANT), (float) demands[1] * 100 / demandsPerDay, proba[1]);
            if(numberOfProducts>=3) {
                proba[2] = updateProductProba(s.getMarketShare(Products.PUMPKIN), (float) demands[2] * 100 / demandsPerDay, proba[2]);
                if(numberOfProducts>=4) {
                    proba[3] = updateProductProba(s.getMarketShare(Products.BROCCOLI), (float) demands[3] * 100 / demandsPerDay, proba[3]);
                }
            }
        }
    }

    public int sumProba(){
        int res=proba[0];
        for(int i=1;i<numberOfProducts;i++)
            res+=proba[i];
        return res;
    }

    public void updateDemands(SalesCount s){
        if(numberOfProducts>=2) {
            this.updateProba(s);
            this.cleanDemands();
            for (int i = 0; i < demandsPerDay - numberOfProducts; i++) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, sumProba()+1);
                if (randomNum <= proba[0]) {
                    demands[0]++;
                } else {
                    if (randomNum <= (proba[0]+proba[1])) {
                        demands[1]++;
                    } else {
                        if (numberOfProducts>=3 && randomNum <= proba[0]+proba[1]+proba[2]) {
                            demands[2]++;
                        } else {
                            if(numberOfProducts>=4)
                                demands[3]++;
                        }
                    }
                }
            }
            for(int i=0;i<numberOfProducts;i++)
                demands[i]++;
        }
        else{
            this.cleanDemands();
            demands[0]=demandsPerDay;
        }



    }

    public int getDemandsPerDay(){
        return demandsPerDay;
    }

    public void cleanDemands(){
        for(int i=0;i<numberOfProducts;i++){
            demands[i]=0;
        }
    }
    public int getProbaTomato() {
        return proba[0];
    }

    public int getProbaEggplant() {
        if(numberOfProducts>=2)
            return proba[1];
        else
            return 0;
    }

    public int getProbaPumpkin() {
        if(numberOfProducts>=3)
            return proba[2];
        else
            return 0;
    }

    public int getProbaBroccoli() {
        if(numberOfProducts>=4)
            return proba[3];
        else
            return 0;
    }

    public int getDemandsTomate() {
        return demands[0];
    }
    public int getDemandsEggplant() {
        if(numberOfProducts>=2)
            return demands[1];
        else
            return 0;
    }
    public int getDemandsPumpkin() {
        if(numberOfProducts>=3)
            return demands[2];
        else
            return 0;
    }
    public int getDemandsBroccoli() {
        if(numberOfProducts>=4)
            return demands[3];
        else
            return 0;
    }
}
