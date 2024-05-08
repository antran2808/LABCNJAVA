package org.example.lab07_1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab071Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Lab071Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to Java Tech World!");
    }
}
