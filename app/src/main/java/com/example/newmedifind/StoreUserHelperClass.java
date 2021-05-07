package com.example.newmedifind;

public class StoreUserHelperClass {

    String storeusername,storephonenumber,storename1,storepassword,location,id1;

    public StoreUserHelperClass() {

    }

    public StoreUserHelperClass(String storeusername, String storephonenumber, String storename1, String storepassword, String location, String id1) {
        this.storeusername = storeusername;
        this.storephonenumber = storephonenumber;
        this.storename1 = storename1;
        this.storepassword = storepassword;
        this.location = location;
        this.id1 = id1;
    }

    public String getStoreusername() {
        return storeusername;
    }

    public void setStoreusername(String storeusername) {
        this.storeusername = storeusername;
    }

    public String getStorephonenumber() {
        return storephonenumber;
    }

    public void setStorephonenumber(String storephonenumber) {
        this.storephonenumber = storephonenumber;
    }

    public String getStorename1() {
        return storename1;
    }

    public void setStorename1(String storename1) {
        this.storename1 = storename1;
    }

    public String getStorepassword() {
        return storepassword;
    }

    public void setStorepassword(String storepassword) {
        this.storepassword = storepassword;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }
}
