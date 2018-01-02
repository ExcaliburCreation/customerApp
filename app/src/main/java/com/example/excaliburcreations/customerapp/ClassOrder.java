package com.example.excaliburcreations.customerapp;

import java.util.ArrayList;

/**
 * Created by dell pc on 29-Jul-17.
 */

public class ClassOrder {
    String city;
    String area;
    String time;
//    String address;
//    String contactno;
    String status;
    String item ;
//    String consigneeName;
//    String payMethod;
    String amount;
//    String comments;

    //othere variables
    String datetime;
    String total;
    String basket;

    //items
    String category;
  //  String count;
    String description;
    String imageUrl;
//    String name;
    //String price;
//    String quantity;
    String unit;
    //private int id;
   // private String isAdded;
    String itemKey;
    String consigneeName;
    String payMethod;
    String address;
    String contactno;
    String comments;
    String name;
    String quantity;
    private  ArrayList<String> yourlist;



    public ClassOrder(){

    }
    //items Contructor
//    public ClassOrder(String category, String count, String description, String imageUrl, String name, String price, String quantity, String unit){
//        this.category = category;
//        this.count = count;
//        this.description = description;
//        this.imageUrl = imageUrl;
//        this.name = name;
//        this.price = price;
//        this.quantity = quantity;
//        this.unit = unit;
//    }
    public ClassOrder(String category, String description, String imageUrl, String name, String quantity, String unit){
        this.category = category;

        this.description = description;
        this.imageUrl = imageUrl;
        this.name = name;

        this.quantity = quantity;
        this.unit = unit;
    }
    public ClassOrder(String name, String quantity){
        this.name = name;
        this.quantity = quantity;
    }
    public ClassOrder(String consigneeName, String contactno, String payMethod, String datetime){
        this.consigneeName = consigneeName;
        this.contactno = contactno;
        this.payMethod = payMethod;
        this.datetime = datetime;
    }


    public ClassOrder(String city, String area, String time, String address, String contactno, String status,
                      String item, String consigneeName, String payMethod, String amount, String comments ){
        this.city = city;
        this.area = area;
        this.time = time;
        this.address = address;
        this.contactno = contactno;
        this.status = status;
        this.item = item;
        this.consigneeName = consigneeName;
        this.payMethod = payMethod;
        this.amount = amount;
        this.comments = comments;
    }


    //other constructor
    public ClassOrder(String datetime, String total, String basket){
        this.datetime = datetime;
        this.total = total;
        this.basket = basket;

    }
//    public ClassOrder(String datetime, String total, String basket, String itemKey){
//        this.datetime = datetime;
//        this.total = total;
//        this.basket = basket;
//        this.itemKey = itemKey;
//
//    }


    public ClassOrder(String total){
        this.total = total;
    }

    public String getContactno() {
        return contactno;
    }

    public String getAddress() {
        return address;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getItem() {
        return item;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public String getAmount() {
        return amount;
    }

    public String getComments() {
        return comments;
    }

//other getters

    public String getBasket() {
        return basket;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getTotal() {
        return total;
    }

    public String getItemKey() {
        return itemKey;
    }

    //items getter
    public String getCategory() {
        return category;
    }

    //public String getCount() {
//        return count;
//    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

//    public String getPrice() {
//        return price;
//    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public ArrayList<String> getYourlist() {
        return yourlist;
    }


}
