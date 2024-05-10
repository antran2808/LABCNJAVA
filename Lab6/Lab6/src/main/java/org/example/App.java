package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Product product1 = applicationContext.getBean("bean1", Product.class);
        Product product2 = applicationContext.getBean("bean2", Product.class);
        Product product3 = applicationContext.getBean("bean3", Product.class);

        System.out.println(product1.getName());
        System.out.println(product2.getName());
        System.out.println(product3.getName());
    }
}
