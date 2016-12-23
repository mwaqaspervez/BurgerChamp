package com.mwaqaspervez.chefsburger;


public class Item {


    private int price;
    private String name;
    private boolean isSpecial;
    private String detail;


    public Item(int price, String name, boolean isSpecial, String detail) {
        this.price = price;
        this.name = name;
        this.detail = detail;
        this.isSpecial = isSpecial;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
