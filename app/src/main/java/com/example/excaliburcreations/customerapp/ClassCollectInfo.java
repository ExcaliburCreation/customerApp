package com.example.excaliburcreations.customerapp;

/**
 * Created by dell pc on 20-Apr-17.
 */

public  class ClassCollectInfo {
    public static String city;
    public static String area;
    public static String Oname;
    public static String Ocnic;
    public static String Oage;
    public static String Ocellno;
    public static String Oemail;
    public static String Opassword;
    public static String Ontnno;

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
