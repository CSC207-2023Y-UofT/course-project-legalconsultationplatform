package infrastructure.screens;

import adapters.controllers.ControlContainer;
import adapters.controllers.RegisterControl;
import entities.ApplicationException;
import infrastructure.screens.utils.UIManager;
import usecases.requests.AttorneyRegistrationData;
import usecases.requests.RegistrationData;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;

import static infrastructure.screens.utils.UIDrawer.*;


public class AttorneyRegisterUI extends RegisterUI {

    private static final String[] PROFESSIONAL_LIST = {
            "Family and Children", "Individual Rights",
            "Consumer Financial Questions", "Housing and Homelessness",
            "Income Maintenance", "Health and Disability",
            "Work, Employment and Unemployment", "Juvenile", "Education", "Other"
    };

    static JList<String> professionals = new JList<>(PROFESSIONAL_LIST);
    static JPanel professionalPanel;
    HashSet<String> selectedProfessionals;
    static final String PROFESSIONALS_PROMPT_LINE_1 = "Please select your area of professionals";
    static final String PROFESSIONALS_PROMPT_LINE_2 = "(Press command/ctrl for multiple selections)";

    public AttorneyRegisterUI(UIManager UIManager) {
        super(UIManager);
        professionals.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        professionals.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                List<String> selectedItems = professionals.getSelectedValuesList();
                selectedProfessionals = new HashSet<>(selectedItems);
            }
        });
        professionalPanel = promptPanelDrawer(new JLabel("<html>" + PROFESSIONALS_PROMPT_LINE_1 + "<br>" + PROFESSIONALS_PROMPT_LINE_2+ "</html>"), professionals);

        JPanel inputPanel = registrationPanelDrawer();

        JScrollPane scrollPane= registerScrollDrawer(title, inputPanel, registerButtons, 700);
        add(scrollPane);
    }

    protected JPanel registrationPanelDrawer() {
        JPanel result = super.registrationPanelDrawer();
        result.add(professionalPanel);
        result.add(inputSpacer);

        return result;
    }


    @Override
    protected void handleRegisterAction(ControlContainer controlContainer, JPanel screens, CardLayout cardLayout) {
        RegisterControl attorneyRegisterControl = controlContainer.getAttorneyRegisterControl();
        try {
            RegistrationData registrationData = new RegistrationData(userName.getText(), email.getText(), String.valueOf(password1.getPassword()), String.valueOf(password2.getPassword()), stateAbb.getText(), postalCode.getText());
            AttorneyRegistrationData attorneyRegistrationData = new AttorneyRegistrationData.Builder(registrationData)
                    .professionals(selectedProfessionals).build();
            attorneyRegisterControl.create(attorneyRegistrationData);
        } catch (ApplicationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}

