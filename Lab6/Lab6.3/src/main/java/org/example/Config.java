package org.example;

import org.springframework.context.annotation.Bean;

public class Config {
    @Bean
    public PlainTextWritter plainTextWritter(){return new PlainTextWritter();}

    @Bean
    public PlainTextWritter pdfTextWritter(){return new PlainTextWritter();}

    @Bean
    public TextEditor editText(){return new TextEditor();}
}
