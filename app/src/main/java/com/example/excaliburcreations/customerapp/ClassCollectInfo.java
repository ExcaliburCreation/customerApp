package com.example.excaliburcreations.customerapp;

/**
 * Created by dell pc on 20-Apr-17.
 */

public class ClassCollectInfo {
    public static String city;
    public static String area;

    public ClassCollectInfo(){

    }

    public ClassCollectInfo(String city, String area){
        this.city = city;
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }
}
