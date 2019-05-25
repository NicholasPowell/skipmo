package com.nilo.skipmo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(String[] args) {
        System.out.println("EUREKA!!!!");
        SpringApplication.run(EurekaApplication.class, args);
    }

}
