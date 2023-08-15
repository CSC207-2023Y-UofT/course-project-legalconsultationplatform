package infrastructure.screens.utils;

import adapters.controllers.ControlContainer;

import javax.swing.*;
import java.awt.*;

public class UIManager {
    ControlContainer controlContainer;
    JPanel screens;
    CardLayout cardLayout;

    public UIManager(JPanel screens, CardLayout cardLayout) {
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

    public void setControlContainer(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
    }
}
