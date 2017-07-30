package com.example.excaliburcreations.customerapp;

/**
 * Created by dell pc on 29-Jul-17.
 */

public class ClassOrder {
    String city;
    String area;
    String time;
    String address;
    String contactno;
    String status;
    String item ;


    public ClassOrder(){

    }

    public ClassOrder(String city, String area, String time, String address, String contactno, String status, String item ){
        this.city = city;
        this.area = area;
        this.time = time;
        this.address = address;
        this.contactno = contactno;
        this.status = status;
        this.item = item;
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
}
