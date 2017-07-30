package com.example.excaliburcreations.customerapp;

/**
 * Created by dell pc on 29-Mar-17.
 */

public class ClassUserInfo {
    String companyName;
    String personName;
    String personDes;
    String businessDesp;
    String cellNo;
    String city;
    String time;
    String comments;
    String country;
    String address;



        public ClassUserInfo( String companyName,String personName,String personDes,String businessDesp,String cellNo,
            String city,String time,String comments,String country,String address){
        this.companyName = companyName;
        this.personName = personName;
        this.personDes  = personDes;
        this.businessDesp = businessDesp;
        this.cellNo = cellNo;
        this.city = city;
        this.time = time;
        this.comments = comments;
        this.country = country;
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public String getBusinessDesp() {
        return businessDesp;
    }

    public String getCellNo() {
        return cellNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getComments() {
        return comments;
    }

    public String getCountry() {
        return country;
    }

    public String getPersonDes() {
        return personDes;
    }

    public String getPersonName() {
        return personName;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }
}
