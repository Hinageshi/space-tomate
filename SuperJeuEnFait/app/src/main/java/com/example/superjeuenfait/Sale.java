package com.example.superjeuenfait;

public class Sale {

    private Products product;
    private int price;

    public Sale(Products product, int price){
        this.product=product;
        this.price=price;
    }


    public int getPrice() {
        return price;
    }

    public Products getProduct() {
        return product;
    }
}
