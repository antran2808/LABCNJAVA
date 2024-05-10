package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ProductConfig {
    @Bean
    @Scope("prototype")
    public Product product1(){
        Product product = new Product(1, "Iphone", 12, "My Phone");
        return product;
    }
    @Bean
    @Scope("prototype")
    public Product product2(){
        Product product = new Product(product1());
        return product;
    }
    @Bean
    @Scope("singleton")
    public Product product3(){
        Product product = new Product(3, "Xbox", 12, "My Phone");
        return product;
    }
}
