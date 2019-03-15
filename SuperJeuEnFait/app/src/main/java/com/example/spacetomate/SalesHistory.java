package com.example.spacetomate;

import java.util.ArrayList;
//Visible to player, use to crate a history of his last 50 (max) sales
public class SalesHistory {

    private ArrayList<Sale> salesHistory;

    public SalesHistory(){
        this.salesHistory=new ArrayList<Sale>(50);
    }


    public void addSale(Sale s){
        if(salesHistory.size()>=50){
            salesHistory.remove(49);
        }
        salesHistory.add(0,s);
    }

    public int numberOfProductSold(Products p){
        int count=0;
        for(Sale s: salesHistory){
           if(s.getProduct().equals(p)){
               count++;
           }
       }
       return count;
    }
}
