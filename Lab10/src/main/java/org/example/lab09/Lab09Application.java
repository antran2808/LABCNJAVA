package org.example.lab09;

import org.example.lab09.service.OrderService;
import org.example.lab09.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab09Application {


    public static void main(String[] args) {
        SpringApplication.run(Lab09Application.class, args);
    }

}
