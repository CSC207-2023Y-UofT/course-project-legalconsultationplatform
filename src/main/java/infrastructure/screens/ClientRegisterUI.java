package infrastructure.screens;

import adapters.controllers.ControlContainer;
import adapters.controllers.RegisterControl;
import infrastructure.screens.utils.UIManager;
import usecases.requests.ClientRegistrationData;
import usecases.requests.RegistrationData;

import javax.swing.*;
import java.awt.*;

import static infrastructure.screens.utils.UIDesign.*;
import static infrastructure.screens.utils.UIDrawer.*;

public class ClientRegisterUI extends RegisterUI{
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

    static JPanel agePanel;
    static JPanel numberOfHouseholdPanel;
    static JPanel annualIncomePanel;
    static JPanel ethnicityPanel;
    static JPanel genderPanel;
    static JPanel maritalStatusPanel;
    static final String AGE_PROMPT = "Age";
    static final String HOUSEHOLD_PROMPT = "Number of household";
    static final String INCOME_PROMPT = "Annual Income";
    static final String ETHNICITY_PROMPT = "Ethnicity";
    static final String GENDER_PROMPT = "Gender";
    static final String MARITAL_STATUS_PROMPT = "Marital Status";
    public ClientRegisterUI(UIManager UIManager) {
        super(UIManager);

        agePanel = labelTextPanelDrawer(new JLabel(AGE_PROMPT), age);
        numberOfHouseholdPanel = labelTextPanelDrawer(new JLabel(HOUSEHOLD_PROMPT), numberOfHousehold);
        annualIncomePanel = labelTextPanelDrawer(new JLabel(INCOME_PROMPT), annualIncome);
        ethnicityPanel = promptPanelDrawer(new JLabel(ETHNICITY_PROMPT), ethnicity);
        genderPanel = promptPanelDrawer(new JLabel(GENDER_PROMPT), gender);
        maritalStatusPanel = promptPanelDrawer(new JLabel(MARITAL_STATUS_PROMPT), maritalStatus);

        JPanel inputPanel = registrationPanelDrawer();

        JScrollPane scrollPane= registerScrollDrawer(title, inputPanel, registerButtons, 20, 780);
        add(scrollPane);

    }
    @Override
    protected JPanel registrationPanelDrawer() {
        JPanel result = super.registrationPanelDrawer();
        result.add(agePanel);
        result.add(inputSpacer);
        result.add(numberOfHouseholdPanel);
        result.add(inputSpacer);
        result.add(annualIncomePanel);
        result.add(inputSpacer);
        result.add(ethnicityPanel);
        result.add(inputSpacer);
        result.add(genderPanel);
        result.add(inputSpacer);
        result.add(maritalStatusPanel);
        result.add(inputSpacer);

        return result;
    }

    @Override
    protected void handleRegisterAction(ControlContainer controlContainer, JPanel screens, CardLayout cardLayout) {
        RegisterControl clientRegisterControl = controlContainer.getClientRegisterControl();
        try {
            RegistrationData registrationData = new RegistrationData(userName.getText(), email.getText(), String.valueOf(password1.getPassword()), String.valueOf(password2.getPassword()), stateAbb.getText(), postalCode.getText());
            ClientRegistrationData clientRegistrationData = new ClientRegistrationData.Builder(registrationData)
                    .ethnicity((String) ethnicity.getSelectedItem())
                    .age(Integer.parseInt(age.getText()))
                    .gender((String) gender.getSelectedItem())
                    .maritalStatus((String) maritalStatus.getSelectedItem())
                    .numberOfHousehold(Integer.parseInt(numberOfHousehold.getText()))
                    .annualIncome(Float.parseFloat(annualIncome.getText()))
                    .build();
            clientRegisterControl.create(clientRegistrationData);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
