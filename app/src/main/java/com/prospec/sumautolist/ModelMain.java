package com.prospec.sumautolist;

public class ModelMain {

    private String service;
    private int price;
    private boolean btnPlus=false;

    public ModelMain(String service, int price, boolean btnPlus){
        this.service = service;
        this.price = price;
        this.btnPlus = btnPlus;
    }

    public int getPrice() {
        return price;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public boolean isBtnPlus() {
        return btnPlus;
    }
    public void setBtnPlus(boolean btnPlus) {
        this.btnPlus = btnPlus;
    }
}