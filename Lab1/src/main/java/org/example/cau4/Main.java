package org.example.cau4;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please specify an URL to a file");
            return;
        }

        String url = args[0];
        UrlValidator urlValidator = new UrlValidator();

        if (!urlValidator.isValid(url)) {
            System.out.println("This is not a valid URL");
            return;
        }

        try {
            String fileName = new File(new URL(url).getPath()).getName();
            FileUtils.copyURLToFile(new URL(url), new File(fileName));
            System.out.println("File downloaded successfully");
        } catch (IOException e) {
            System.out.println("Error downloading the file: " + e.getMessage());
        }
    }
}