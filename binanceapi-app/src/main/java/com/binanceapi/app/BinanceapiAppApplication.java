package com.binanceapi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BinanceapiAppApplication {

    public static void main(String[] args) {
        System.out.println("Server is Running !");
        SpringApplication.run(BinanceapiAppApplication.class, args);
    }

}
