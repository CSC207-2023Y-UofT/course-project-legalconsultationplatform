package driver.screen;

import adapter.controller.ClientRegisterControl;
import adapter.controller.ControlContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUI extends JPanel implements ActionListener{
    ControlContainer controlContainer;

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

    public RegisterUI(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
        JLabel title = new JLabel("Create your profile");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Text field items here

        LabelTextPanel userNamePanel = new LabelTextPanel(new JLabel("User name"), userName);
        LabelTextPanel passwordPanel = new LabelTextPanel(new JLabel("Password"), password1);
        LabelTextPanel repeatPasswordPanel = new LabelTextPanel(new JLabel("Repeat Password"), password2);
        LabelTextPanel emailPanel = new LabelTextPanel(new JLabel("Email"), email);
        LabelTextPanel postalCodePanel = new LabelTextPanel(new JLabel("Zip Code"), postalCode);
        LabelTextPanel stateAbbPanel = new LabelTextPanel(new JLabel("State Abbreviation"), stateAbb);

        //Number items here
        LabelTextPanel agePanel = new LabelTextPanel(new JLabel("Age"), age);
        LabelTextPanel numberOfHouseholdPanel = new LabelTextPanel(new JLabel("Number of Household"), numberOfHousehold);
        LabelTextPanel annualIncomePanel = new LabelTextPanel(new JLabel("Annual Income"), annualIncome);


        //Dropbox items here
        DropDownPanel ethnicityPanel = new DropDownPanel(new JLabel("Ethnicity"), ethnicity);
        DropDownPanel genderPanel = new DropDownPanel(new JLabel("Gender"), gender);
        DropDownPanel maritalStatusPanel = new DropDownPanel(new JLabel("Marital Status"), maritalStatus);


        //Register button here
        JButton buttonToSubmit = new JButton("Register");
        JPanel buttons = new JPanel();
        buttons.add(buttonToSubmit);
        buttonToSubmit.addActionListener(this);

        //Add everything into the panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(userNamePanel);
        this.add(passwordPanel);
        this.add(repeatPasswordPanel);
        this.add(emailPanel);
        this.add(postalCodePanel);
        this.add(stateAbbPanel);
        this.add(agePanel);
        this.add(ethnicityPanel);
        this.add(genderPanel);
        this.add(maritalStatusPanel);
        this.add(numberOfHouseholdPanel);
        this.add(annualIncomePanel);

        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("User clicks register");
        ClientRegisterControl clientRegisterControl = controlContainer.getClientRegisterControl();

        try {
            clientRegisterControl.create(userName.getText(), email.getText(), String.valueOf(password1.getPassword()),
                    String.valueOf(password2.getPassword()), stateAbb.getText(), postalCode.getText(),
                    (String)ethnicity.getSelectedItem(), Integer.parseInt(age.getText()), (String)gender.getSelectedItem(),
                    (String)maritalStatus.getSelectedItem(), Integer.parseInt(numberOfHousehold.getText()),
                    Float.parseFloat(annualIncome.getText()));
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
