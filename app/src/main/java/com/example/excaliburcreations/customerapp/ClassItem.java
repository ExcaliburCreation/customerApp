package com.example.excaliburcreations.customerapp;

/**
 * Created by dell pc on 28-Dec-17.
 */

public class ClassItem {
    String name;
    String quantity;

    public ClassItem(){

    }
    public ClassItem(String name, String quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }
}


