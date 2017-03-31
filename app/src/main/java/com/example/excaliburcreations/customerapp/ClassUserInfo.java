package com.example.excaliburcreations.customerapp;

/**
 * Created by dell pc on 29-Mar-17.
 */

public class ClassUserInfo {
    String firstName;
    String lastName;
    String email;
    String password;
    String address;
    String area;
    String city;

    public ClassUserInfo(String firstName, String lastName, String email,String address, String area, String city){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.area = area;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getArea() {
        return area;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
