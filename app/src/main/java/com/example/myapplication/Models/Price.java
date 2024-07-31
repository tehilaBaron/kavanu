package com.example.myapplication.Models;


public class Price {
    private String itemName;
    private String itemPrice;

    public Price(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }
}
