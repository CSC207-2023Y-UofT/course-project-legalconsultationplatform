package driver.screen;

import adapter.controller.ControlContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class HomePageUI extends UserUI {
    public HomePageUI(String userName, int userId, ControlContainer controlContainer) {
        super(userName, userId, controlContainer);
    }
}
