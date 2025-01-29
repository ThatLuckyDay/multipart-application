package com.pet.desktop.controller;

import com.pet.desktop.DesktopUIStarter;
import com.pet.desktop.DesktopUIStarter.StageReadyEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainWindowController {

    @Value("classpath:/views/pages/mainWindow.fxml")
    private Resource mainWindowFxmlResource;

    private final String applicationTitle;

    @Autowired
    public MainWindowController(@Value("${application.title}") String applicationTitle) {
        this.applicationTitle = applicationTitle;
    }

    @FXML
    public void initialize() {
    }

    public void loadView(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(mainWindowFxmlResource.getURL());
            fxmlLoader.setControllerFactory(DesktopUIStarter.getApplicationContext()::getBean);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);

            Stage stage = event.getStage();
            stage.setTitle(applicationTitle);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
