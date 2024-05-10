package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

/**
 * Hello world!
 *
 */
@ComponentScan(basePackages = "org.example")
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        TextEditor textEditor = (TextEditor) applicationContext.getBean(TextEditor.class);
        textEditor.input("hello palin");
        PdfTextWritter pdf = new PdfTextWritter();
        try{
            textEditor.save("plain.txt");
            pdf.writter("pdf.txt", "hello pdf");
        }catch (IOException e){
            System.out.println(e);
        }

    }
}
