package com.pet.desktop;

import com.pet.desktop.controller.MainWindowController;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DesktopStageListener implements ApplicationListener<DesktopUIStarter.StageReadyEvent> {

    private final MainWindowController mainWindowController;

    @Autowired
    public DesktopStageListener(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    @Override
    public void onApplicationEvent(@NotNull DesktopUIStarter.StageReadyEvent event) {
        mainWindowController.loadView(event);
    }
}
