package driver.screen;

import adapter.controller.ClientRegisterControl;
import adapter.controller.ControlContainer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;

public class RegisterUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;

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

    public RegisterUI(ControlContainer controlContainer, CardLayout cardLayout, JPanel screens) {
        this.controlContainer = controlContainer;
        this.cardLayout = cardLayout;
        this.screens = screens;
        UIDesign.setBackgroundFrame(this);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around each component

        // Create the title label with 30 pixels space on top
        JLabel title = new JLabel("Create your profile");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);
        int topMargin = 30;
        int leftMargin = 0;
        int bottomMargin = 0;
        int rightMargin = 0;
        title.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        add(title, gbc);

        // Add all the panels here...
        gbc.gridx = 0; // Set the gridx to 0 to place components under the title

        LabelTextPanel userNamePanel = new LabelTextPanel(new JLabel("User Name"), userName);
        add(userNamePanel, gbc);

        LabelTextPanel passwordPanel = new LabelTextPanel(new JLabel("Password"), password1);
        add(passwordPanel, gbc);

        LabelTextPanel repeatPasswordPanel = new LabelTextPanel(new JLabel("Confirm Password"), password2);
        add(repeatPasswordPanel, gbc);

        LabelTextPanel emailPanel = new LabelTextPanel(new JLabel("Email"), email);
        add(emailPanel, gbc);

        LabelTextPanel postalCodePanel = new LabelTextPanel(new JLabel("Zip Code"), postalCode);
        add(postalCodePanel, gbc);

        LabelTextPanel stateAbbPanel = new LabelTextPanel(new JLabel("State Abbreviation"), stateAbb);
        add(stateAbbPanel, gbc);

        LabelTextPanel agePanel = new LabelTextPanel(new JLabel("Age"), age);
        add(agePanel, gbc);

        LabelTextPanel numberOfHouseholdPanel = new LabelTextPanel(new JLabel("Number of Household"), numberOfHousehold);
        add(numberOfHouseholdPanel, gbc);

        LabelTextPanel annualIncomePanel = new LabelTextPanel(new JLabel("Annual Income"), annualIncome);
        add(annualIncomePanel, gbc);

        DropDownPanel ethnicityPanel = new DropDownPanel(new JLabel("Ethnicity"), ethnicity);
        add(ethnicityPanel, gbc);

        DropDownPanel genderPanel = new DropDownPanel(new JLabel("Gender"), gender);
        add(genderPanel, gbc);

        DropDownPanel maritalStatusPanel = new DropDownPanel(new JLabel("Marital Status"), maritalStatus);
        add(maritalStatusPanel, gbc);

        //Register button here
        JButton buttonToSubmit = new JButton("Register");
        UIDesign.setButton(buttonToSubmit);
        JButton homePage = new JButton("Welcome Page");
        UIDesign.setButton(homePage);
        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.lightGreenColor);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS)); // Use X_AXIS for horizontal alignment
        buttons.add(buttonToSubmit);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(homePage);
        buttons.setAlignmentX(LEFT_ALIGNMENT);
        buttonToSubmit.addActionListener(this);
        homePage.addActionListener(this);
        add(buttons, gbc);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("User clicks register");
        String actionCommand = e.getActionCommand();
        ClientRegisterControl clientRegisterControl = controlContainer.getClientRegisterControl();
        if ("Register".equals(actionCommand)) {
            try {
                clientRegisterControl.create(userName.getText(), email.getText(), String.valueOf(password1.getPassword()),
                        String.valueOf(password2.getPassword()), stateAbb.getText(), postalCode.getText(),
                        (String) ethnicity.getSelectedItem(), Integer.parseInt(age.getText()), (String) gender.getSelectedItem(),
                        (String) maritalStatus.getSelectedItem(), Integer.parseInt(numberOfHousehold.getText()),
                        Float.parseFloat(annualIncome.getText()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else if ("Welcome Page".equals(actionCommand)) {
            WelcomeUI welcomeUI = new WelcomeUI(controlContainer, cardLayout, screens);
            screens.add(welcomeUI, "Welcome");
            cardLayout.show(screens, "Welcome");
        }
    }
}