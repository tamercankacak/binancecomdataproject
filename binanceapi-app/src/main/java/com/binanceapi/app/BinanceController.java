package com.binanceapi.app;

import com.binanceapi.library.DatabaseHelper;
import org.springframework.web.bind.annotation.*;

@RestController
public class BinanceController {

    DatabaseHelper _databaseHelper = new DatabaseHelper();

    @GetMapping("/")
    public String home(){
        return "<b>Get Crypto Money last 15 Minutes 5 by 5 = </b> localhost:8080/getPricesBySymbol?symbol={symbol}<br/>" +
                "Example : localhost:8080/getPricesBySymbol?symbol=ETHBTC<br/><br/>" +
                "<b>Get Crypto Money last 15 Minutes 5 by 5 = </b> localhost:8080/getPricesAvgBySymbol?symbol={symbol}<br/>" +
                "Example : localhost:8080/getPricesBySymbol?symbol=ETHBTC<br/><br/>";
    }


    @RequestMapping(value = "/getPricesBySymbol", method = RequestMethod.GET,produces = {"application/json"})
    @ResponseBody
    public String getPricesBySymbol(@RequestParam String symbol){
        String data = _databaseHelper.getDataBySymbol(symbol);
        if (data.isEmpty()){
            return "{\"INVALID\":\" TRY THIS : BTCUSD BNBBTC ETHBTC XRPBTC BCHBTC LTCBTC\"}";
        }
        return data;
    }

    @RequestMapping(value = "/getPricesAvgBySymbol", method = RequestMethod.GET,produces = {"application/json"})
    @ResponseBody
    public String getPriceAvgBySymbol(@RequestParam String symbol){
        String data = _databaseHelper.getDataAvgBySymbol(symbol);
        if (data.isEmpty()){
            return "{\"INVALID\":\" TRY THIS : BTCUSD BNBBTC ETHBTC XRPBTC BCHBTC LTCBTC\"}";
        }
        return data;
    }



}
