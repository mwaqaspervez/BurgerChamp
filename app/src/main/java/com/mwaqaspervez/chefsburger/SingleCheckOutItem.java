package com.mwaqaspervez.chefsburger;


class SingleCheckOutItem {


    private String name;
    private int quantity, price;

    SingleCheckOutItem(String name, int quantity, int price) {

        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getQuantity() {
        return quantity;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    int getPrice() {
        return price;
    }

    void setPrice(int price) {
        this.price = price;
    }
}
