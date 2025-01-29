package com.pet.desktop;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Desktop {
    public static void main(String[] args) {
        Application.launch(DesktopUIStarter.class, args);
    }
}