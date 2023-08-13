package driver.screen;

import adapter.controller.ControlContainer;

import javax.swing.*;
import java.awt.*;

public class UIManager {
    ControlContainer controlContainer;
    JPanel screens;
    CardLayout cardLayout;

    public UIManager(ControlContainer controlContainer, JPanel screens, CardLayout cardLayout) {
        this.controlContainer = controlContainer;
        this.screens = screens;
        this.cardLayout = cardLayout;
    }

    public ControlContainer getControlContainer() {
        return controlContainer;
    }

    public JPanel getScreens() {
        return screens;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
