package com.example.fooddelivery;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String name;
    private int price;
    private int imageResId;
    private int quantity;  // Add quantity field
    private String databaseKey;  // Add databaseKey field

    public CartItem() {
        // Required empty constructor for Firebase
    }

    public CartItem(String name, int price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = 1;  // Default quantity is 1
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDatabaseKey() {
        return databaseKey;
    }

    public void setDatabaseKey(String databaseKey) {
        this.databaseKey = databaseKey;
    }
}
