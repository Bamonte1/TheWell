package com.example.thewell;

import android.graphics.drawable.Drawable;

/**
 Jacob Bamonte
 CIT382
 Assignment 3 - Droid Cafe
 */

public class Item {

    //Constructor, getters, and setters for images, descriptions, and prices

    private Drawable image;
    private String desc;
    private String price;

    public Item(Drawable image, String desc, String price) {
        this.image = image;
        this.desc = desc;
        this.price = price;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
