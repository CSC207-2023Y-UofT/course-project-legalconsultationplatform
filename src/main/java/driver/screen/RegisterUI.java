package driver.screen;

import adapter.controller.ClientRegisterControl;
import adapter.controller.ControlContainer;
import com.objectdb.o.BAC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static driver.screen.UIDrawer.*;
import static driver.screen.UIDesign.*;

public class RegisterUI extends BaseUI{
    UIManager UIManager;

    JTextField userName = new JTextField(15);
    JPasswordField password1 = new JPasswordField(15);
    JPasswordField password2 = new JPasswordField(15);
    JTextField email = new JTextField(15);
    JTextField postalCode = new JTextField(15);
    JTextField stateAbb = new JTextField(15);

    JTextField age = new JTextField(15);
    JTextField numberOfHousehold = new JTextField(15);
    JTextField annualIncome = new JTextField(15);

    String[] ethnicityList = {"Caucasian", "African American", "Asian", "Hispanic",
            "Native American", "Pacific Islander", "Middle Eastern", "Indigenous", "Multiracial", "Other"};
    String[] genderList = {"Male", "Female", "Transgender", "Non-binary", "Gender queer",
            "Gender fluid", "Agender", "Bigender", "Two-spirit", "Other"};
    String[] maritalStatusList = {"Single", "Married", "Separated", "Divorced or Widowed", "I'd rather not answer"};
    JComboBox<String> ethnicity = new JComboBox<>(ethnicityList);
    JComboBox<String> gender = new JComboBox<>(genderList);
    JComboBox<String> maritalStatus = new JComboBox<>(maritalStatusList);

    static final String USER_NAME_PROMPT = "User Name";
    static final String PASSWORD_PROMPT = "Password";
    static final String REPEAT_PASSWORD_PROMPT = "Repeat password";
    static final String EMAIL_PROMPT = "Email Address";
    static final String POSTAL_CODE_PROMPT = "Zip Code";
    static final String REGISTER_BUTTON_NAME = "Register";
    static final String BACK_BUTTON_NAME = "Back";
    static final String STATE_ABB_PROMPT = "State Abbreviation";
    static final String AGE_PROMPT = "Age";
    static final String HOUSEHOLD_PROMPT = "Number of household";
    static final String INCOME_PROMPT = "Annual Income";
    static final String ETHNICITY_PROMPT = "Ethnicity";
    static final String GENDER_PROMPT = "Gender";
    static final String MARITAL_STATUS_PROMPT = "Marital Status";

    public RegisterUI(UIManager UIManager) {
        super(UIManager);

        //Spacers
        JPanel spacer1 = addSpacer(20);
        JPanel spacer2 = addSpacer(20);

        // Create the title label with 30 pixels space on top
        JLabel title = new JLabel("Create your profile");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        setTitleFont(title);

        //Input panel
        JPanel inputPanel = new JPanel();
        setSizeInLayout(inputPanel, new Dimension(FRAME_WIDTH, 150));
        JPanel inputSpacer = addSpacer(5);
        JPanel userNamePanel = labelTextPanelDrawer(new JLabel(USER_NAME_PROMPT), userName);
        JPanel passwordPanel = labelTextPanelDrawer(new JLabel(PASSWORD_PROMPT), password1);
        JPanel repeatPasswordPanel = labelTextPanelDrawer(new JLabel(REPEAT_PASSWORD_PROMPT), password2);
        JPanel emailPanel = labelTextPanelDrawer(new JLabel(EMAIL_PROMPT), email);
        JPanel postalCodePanel = labelTextPanelDrawer(new JLabel(POSTAL_CODE_PROMPT), postalCode);
        JPanel stateAbbPanel = labelTextPanelDrawer(new JLabel(STATE_ABB_PROMPT), stateAbb);
        JPanel agePanel = labelTextPanelDrawer(new JLabel(AGE_PROMPT), age);
        JPanel numberOfHouseholdPanel = labelTextPanelDrawer(new JLabel(HOUSEHOLD_PROMPT), numberOfHousehold);
        JPanel annualIncomePanel = labelTextPanelDrawer(new JLabel(INCOME_PROMPT), annualIncome);

        JPanel ethnicityPanel = dropDownPanelDrawer(new JLabel(ETHNICITY_PROMPT), ethnicity);
        JPanel genderPanel = dropDownPanelDrawer(new JLabel(GENDER_PROMPT), gender);
        JPanel maritalStatusPanel = dropDownPanelDrawer(new JLabel(MARITAL_STATUS_PROMPT), maritalStatus);

        inputPanel.add(userNamePanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(passwordPanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(repeatPasswordPanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(emailPanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(postalCodePanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(stateAbbPanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(agePanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(numberOfHouseholdPanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(annualIncomePanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(ethnicityPanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(genderPanel);
        inputPanel.add(inputSpacer);
        inputPanel.add(maritalStatusPanel);
        inputPanel.add(inputSpacer);

        //Register button here
        List<String> buttonList = new ArrayList<>();
        buttonList.add(REGISTER_BUTTON_NAME);
        buttonList.add(BACK_BUTTON_NAME);
        JPanel buttons = setButtonPanel(buttonList, new Dimension(150, 50), 20, this);

        add(spacer1);
        add(title);
        add(spacer2);
        add(inputPanel);
        add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("User clicks register");
        String actionCommand = e.getActionCommand();
        ControlContainer controlContainer = UIManager.getControlContainer();
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        ClientRegisterControl clientRegisterControl = controlContainer.getClientRegisterControl();
        switch (actionCommand) {
            case REGISTER_BUTTON_NAME:
                try {
                    clientRegisterControl.create(userName.getText(), email.getText(), String.valueOf(password1.getPassword()),
                            String.valueOf(password2.getPassword()), stateAbb.getText(), postalCode.getText(),
                            (String) ethnicity.getSelectedItem(), Integer.parseInt(age.getText()), (String) gender.getSelectedItem(),
                            (String) maritalStatus.getSelectedItem(), Integer.parseInt(numberOfHousehold.getText()),
                            Float.parseFloat(annualIncome.getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                break;

            case BACK_BUTTON_NAME:
                cardLayout.show(screens, "Welcome");
                break;
        }
    }
}