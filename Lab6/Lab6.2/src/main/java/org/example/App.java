package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ProductConfig.class);
        Product product1 = (Product) annotationConfigApplicationContext.getBean("product1");
        Product product2 = (Product) annotationConfigApplicationContext.getBean("product2");
        Product product3 = (Product) annotationConfigApplicationContext.getBean("product3");
       // System.out.println( "Hello World!" );

        System.out.println(product1.getName());
        System.out.println(product2.getName());
        System.out.println(product3.getName());
    }
}
