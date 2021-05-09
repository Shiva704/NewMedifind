package com.example.newmedifind;

public class OrderAdder {

    String med_name,med_quantity,med_price,store_name;
    String url;

    public OrderAdder() {
    }

    public OrderAdder(String url) {
        this.url = url;
    }

    public OrderAdder(String med_name, String med_quantity, String med_price, String store_name) {
        this.med_name=med_name;
        this.med_quantity=med_quantity;
        this.med_price=med_price;
        this.store_name=store_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getMed_quantity() {
        return med_quantity;
    }

    public void setMed_quantity(String med_quantity) {
        this.med_quantity = med_quantity;
    }

    public String getMed_price() {
        return med_price;
    }

    public void setMed_price(String med_price) {
        this.med_price = med_price;
    }
}
