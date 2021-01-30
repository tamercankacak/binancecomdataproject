package com.binanceapi.library;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {

    private Connection connection;

    public DatabaseHelper() {
        try {
            Gson gson = new Gson();
            JsonReader jsonFile = new JsonReader(new FileReader("Settings.json"));
            SettingsModel settings = gson.fromJson(jsonFile, SettingsModel.class);
            connection = DriverManager.getConnection(settings.ConnectionUrl, settings.UserName, settings.Password);


        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }


    public String getDataBySymbol(String symbol) {
        try {
            Gson gson = new Gson();
            ArrayList<CryptoMoneyModel> cryptoList = new ArrayList<>();
            String _sqlText = "select symbol,price,insertdate from cryptomoneys where symbol = '" + symbol + "' \n" +
                    "ORDER BY insertdate desc limit 3";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(_sqlText);
            if (result.isBeforeFirst()) {
                while (result.next()) {
                    CryptoMoneyModel tempModel = new CryptoMoneyModel();
                    tempModel.price = result.getDouble("price");
                    tempModel.symbol = result.getString("symbol");
                    tempModel.insertdate = result.getTimestamp("insertdate");
                    cryptoList.add(tempModel);
                }
                if (cryptoList.size() == 3)
                    return gson.toJson(cryptoList);
                    //insufficient data
                else
                    return "";
            } else return "";


        } catch (Exception exception) {
            exception.printStackTrace();
            return "";
        }
    }

    public String getDataAvgBySymbol(String symbol) {
        try {
            Gson gson = new Gson();
            ArrayList<CryptoMoneyModel> cryptoList = new ArrayList<>();
            String _sqlText = "select symbol,price,insertdate from cryptomoneys where symbol = '" + symbol + "' \n" +
                    "ORDER BY insertdate desc limit 12";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(_sqlText);
            if (result.isBeforeFirst()) {
                while (result.next()) {
                    CryptoMoneyModel tempModel = new CryptoMoneyModel();
                    tempModel.price = result.getDouble("price");
                    tempModel.symbol = result.getString("symbol");
                    tempModel.insertdate = result.getTimestamp("insertdate");
                    cryptoList.add(tempModel);
                }
                double priceSum = 0;
                if (cryptoList.size() == 12) {
                    for (CryptoMoneyModel tempModel :
                            cryptoList) {
                    priceSum += tempModel.price;
                    }
                    CryptoMoneyModel model = new CryptoMoneyModel();
                    model.symbol = symbol;
                    model.price = priceSum / 12;
                    return gson.toJson(model);

                }
                //insufficient data
                else
                    return "";
            } else return "";


        } catch (Exception exception) {
            exception.printStackTrace();
            return "";
        }
    }


}
