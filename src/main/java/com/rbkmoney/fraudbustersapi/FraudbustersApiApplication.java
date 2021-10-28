package com.rbkmoney.fraudbustersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class FraudbustersApiApplication extends SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudbustersApiApplication.class, args);
    }

}
