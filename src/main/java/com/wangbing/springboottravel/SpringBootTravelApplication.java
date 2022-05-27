package com.wangbing.springboottravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.wangbing.springboottravel.web")
public class SpringBootTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTravelApplication.class, args);
    }

}
