package org.example;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintWriter;
@Component("pdfTextWritter")
public class PdfTextWritter implements TextWritter{
    @Override
    public void writter(String name, String content) throws IOException {
        String tmp = "PDF".concat(content);;
        PrintWriter printWriterpdf = new PrintWriter(new FileWriter(name));
        printWriterpdf.print("PDF: "+content);
        //System.out.println("PDF" + content);
        System.out.println(tmp);

        printWriterpdf.close();
    }
}
