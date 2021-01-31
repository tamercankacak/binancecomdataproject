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


    public ArrayList<CryptoMoneyModel> getDataBySymbol(String symbol) {
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
                    return cryptoList;
                else
                    return null;
            } else return null;


        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public CryptoMoneyModel getDataAvgBySymbol(String symbol) {
        try {
            Gson gson = new Gson();
            CryptoMoneyModel tempModel = new CryptoMoneyModel();
            String _sqlText = "select AVG(tablex.price) as price  from (select price from cryptomoneys where symbol = '" + symbol + "' \n" +
                    "\t\t   order by insertdate desc limit 12)tablex";


            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(_sqlText);
            if (result.isBeforeFirst()) {
                while (result.next()) {
                    tempModel.price = result.getDouble("price");
                }
                tempModel.symbol = symbol;
                return tempModel;

            } else return null;


        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }


}
