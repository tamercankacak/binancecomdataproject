package com.binanceapi.library;

import java.util.Date;

public class CryptoMoneyModel {
    String symbol;
    Double price;
    Date insertdate;



    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public Date getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(Date insertdate) {
        this.insertdate = insertdate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
