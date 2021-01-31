package com.binanceapi.app;

import com.binanceapi.library.CryptoMoneyModel;
import com.binanceapi.library.DatabaseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BinanceController {

    DatabaseHelper _databaseHelper = new DatabaseHelper();

    @GetMapping("/")
    public String home(){
        return "<b>Get Crypto Money last 15 Minutes 5 by 5 = </b> localhost:8080/getPricesBySymbol?symbol={symbol}<br/>" +
                "Example : localhost:8080/getPricesBySymbol?symbol=ETHBTC<br/><br/>" +
                "<b>Get Crypto Money Avg Last 60 Minute = </b> localhost:8080/getPricesAvgBySymbol?symbol={symbol}<br/>" +
                "Example : localhost:8080/getPricesAvgBySymbol?symbol=ETHBTC<br/><br/>";
    }


    @RequestMapping(value = "/getPricesBySymbol", method = RequestMethod.GET,produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<ArrayList<CryptoMoneyModel>> getPricesBySymbol(@RequestParam String symbol){
        ArrayList<CryptoMoneyModel> data = _databaseHelper.getDataBySymbol(symbol);
        if (data == null)
        return new ResponseEntity("{\"INVALID\":\" TRY THIS : BTCBUSD BNBBTC ETHBTC XRPBTC BCHBTC LTCBTC\"}",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @RequestMapping(value = "/getPricesAvgBySymbol", method = RequestMethod.GET,produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<CryptoMoneyModel> getPriceAvgBySymbol(@RequestParam String symbol){
        CryptoMoneyModel data = _databaseHelper.getDataAvgBySymbol(symbol);
        if (data == null)
            return new ResponseEntity("{\"INVALID\":\" TRY THIS : BTCBUSD BNBBTC ETHBTC XRPBTC BCHBTC LTCBTC\"}",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }



}
