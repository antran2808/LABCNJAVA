package org.example;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Component("plainTextWritter")
public class PlainTextWritter implements TextWritter{
    @Override
    public void writter(String name, String content) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(name));
        printWriter.println("Plain: " + content);
        System.out.println("Plain: " + content);
        printWriter.close();
    }
}
