package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

public class TextEditor{

    private String content;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

    @Qualifier("pdfTextWritter")
    @Autowired
    private TextWritter textWritter;

    void input(String input){
        this.content = input;
    }
    void save(String fileName) throws IOException {
        textWritter.writter(fileName, this.content);
    }
}
