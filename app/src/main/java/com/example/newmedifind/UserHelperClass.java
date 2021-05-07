package com.example.newmedifind;

public class UserHelperClass {

    String username,phonenumber,email,password,confirmpassword;

    String medicinename,medicineprice,contents,storename,id,quantity;

    String storeusername,storephonenumber,storename1,storepassword,location,id1;




    public UserHelperClass() {

    }



    public UserHelperClass(String medicinename, String medicineprice, String contents, String storename, String id, String quantity) {
        this.medicinename = medicinename;
        this.medicineprice = medicineprice;
        this.contents = contents;
        this.storename = storename;
        this.id = id;
        this.quantity = quantity;
    }

    public UserHelperClass(String username, String phonenumber, String email, String password, String confirmpassword) {
        this.username = username;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public String getMedicineprice() {
        return medicineprice;
    }

    public void setMedicineprice(String medicineprice) {
        this.medicineprice = medicineprice;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
        
    }
}
