package com.example.excaliburcreations.customerapp;

/**
 * Created by dell pc on 10-Sep-17.
 */

public class ClassStore {
    public String sAccountNature;
    public String sAccountTitle;
    public String sBank;
    public String sBankAccountNumber;
    public String sBankBranch;
    public String sBranchCode;
    public String sCNIC;
    public String sContact;
    public String sCurrency;
    public String sDescription;
    public String sEmail;
    public String sNTN;
    public String sName;
    public String sPassword;
    public String shopName;

    public ClassStore(){

    }

    public ClassStore(String sAccountNature, String sAccountTitle, String sBank, String sBankAccountNumber,String sBankBranch, String sBranchCode,
                      String sCNIC, String sContact, String sCurrency, String sDescription, String sEmail, String sNTN, String sName,
                      String sPassword, String shopName){

        this.sAccountNature = sAccountNature;
        this.sAccountTitle = sAccountTitle;
        this.sBank = sBank;
        this.sBankAccountNumber = sBankAccountNumber;
        this.sBankBranch = sBankBranch;
        this.sBranchCode = sBranchCode;
        this.sCNIC = sCNIC;
        this.sContact = sContact;
        this.sCurrency = sCurrency;
        this.sDescription = sDescription;
        this.sEmail = sEmail;
        this.sNTN = sNTN;
        this.sName = sName;
        this.sPassword = sPassword;
        this.shopName = shopName;
    }

    public String getsAccountNature() {
        return sAccountNature;
    }

    public String getsAccountTitle() {
        return sAccountTitle;
    }

    public String getsBank() {
        return sBank;
    }

    public String getsBankAccountNumber() {
        return sBankAccountNumber;
    }

    public String getsBankBranch() {
        return sBankBranch;
    }

    public String getsBranchCode() {
        return sBranchCode;
    }

    public String getsCNIC() {
        return sCNIC;
    }

    public String getsContact() {
        return sContact;
    }

    public String getsCurrency() {
        return sCurrency;
    }

    public String getsDescription() {
        return sDescription;
    }

    public String getsEmail() {
        return sEmail;
    }

    public String getsNTN() {
        return sNTN;
    }

    public String getsName() {
        return sName;
    }

    public String getsPassword() {
        return sPassword;
    }

    public String getShopName() {
        return shopName;
    }
}
