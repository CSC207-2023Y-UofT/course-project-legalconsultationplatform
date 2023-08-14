package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.UIFactory;
import businessrule.outputboundary.BaseOutputBoundary;
import businessrule.responsemodel.BaseResponseModel;
import driver.screen.UIManager;
import entity.ApplicationException;
//import driver.screen.WelcomeUI;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class RegisterResponseFormatter implements BaseOutputBoundary {
    UIManager UIManager;

    public RegisterResponseFormatter(UIManager UIManager) {
        this.UIManager = UIManager;
    }

    @Override
    public BaseResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }
    @Override
    public BaseResponseModel prepareSuccess(String msg) {
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        JPanel welcomeUI = UIFactory.getUI(UIFactory.UIType.WELCOME_UI, UIManager, new BaseResponseModel());
        screens.add(welcomeUI, "Welcome");
        cardLayout.show(screens, "Welcome");
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(msg+"");
        clip.setContents(tText, null);
        String message = "Important: Your unique User ID: " + msg + " has been generated and copied to your clipboard. \nPlease save it securely, as it will not be shown again.";
        JOptionPane.showMessageDialog(null, message,"Notice",JOptionPane.YES_OPTION);
        return new BaseResponseModel();
    }
}
