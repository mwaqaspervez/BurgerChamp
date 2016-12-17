package com.mwaqaspervez.burgerchamp;


public class Item {


    private int price;
    private String name;
    private boolean isSpecial;


    public Item(int price, String name, boolean isSpecial) {
        this.price = price;
        this.name = name;
        this.isSpecial = isSpecial;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }
}
