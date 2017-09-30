package com.example.excaliburcreations.customerapp;

/**
 * Created by dell pc on 30-Sep-17.
 */

public class ClassRider {
    String riderName;
    String riderContact;
    String riderCnic;
    String riderImageUrl;

    public ClassRider(){

    }
    public ClassRider(String riderName, String riderContact, String riderCnic, String riderImageUrl){
        this.riderName = riderName;
        this.riderContact = riderContact;
        this.riderCnic = riderCnic;
        this.riderImageUrl = riderImageUrl;
    }

    public String getRiderName() {
        return riderName;
    }

    public String getRiderContact() {
        return riderContact;
    }

    public String getRiderCnic() {
        return riderCnic;
    }

    public String getRiderImageUrl() {
        return riderImageUrl;
    }
}
