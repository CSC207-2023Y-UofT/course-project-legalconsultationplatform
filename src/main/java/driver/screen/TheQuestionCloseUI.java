package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.RateControl;
import businessrule.usecase.util.PostDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import static driver.screen.UIDesign.*;
import static java.awt.Color.white;

public class TheQuestionCloseUI extends JPanel implements ActionListener {

    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;
    int questionId;
    String title;
    String type;
    LocalDate deadline;
    Map<Integer, PostDisplayFormatter> postMap;
    JComboBox<String> rate;

    public TheQuestionCloseUI(ControlContainer controlContainer, CardLayout cardLayout,
                              JPanel screens, int userId, String userName, int questionId, String title,
                              String type, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        this.controlContainer = controlContainer;
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.userId = userId;
        this.userName = userName;
        this.questionId = questionId;
        this.title = title;
        this.type = type;
        this.deadline = deadline;
        this.postMap = postMap;

        //Top half of the panel
        JPanel topPanel = new TheQuestionTopPanel(userId, userName, questionId, title, type, deadline, postMap);

        //The rate function

        JPanel rateThisQuestionPanel = new JPanel();
        setSizeInLayout(rateThisQuestionPanel, new Dimension(350, 30));
        JLabel label = new JLabel("Rate this question");
        UIDesign.setNameFont(label);
        rateThisQuestionPanel.add(label);
        rateThisQuestionPanel.setBackground(lightGreyColor);

        JPanel dropdownContainer = new JPanel();
        dropdownContainer.setBackground(white);
        setSizeInLayout(dropdownContainer, new Dimension(350, 50));

        String[] rateList = {"Satisfied", "Not satisfied"};
        JComboBox<String> rate = new JComboBox<>(rateList);
        setSizeInLayout(rate, new Dimension(250, 40));
        rate.setRenderer(new MyComboBoxRenderer());

        JButton rateQuestion = new JButton("Rate");
        rateQuestion.setForeground(darkGreenColor);
        setSizeInLayout(rateQuestion, new Dimension(80, 40));
        rateQuestion.addActionListener(this);

        dropdownContainer.setLayout(new FlowLayout());
        dropdownContainer.add(rate);
        dropdownContainer.add(rateQuestion);

        JPanel spacer = addSpacer(20);
        //Home Page Button
        JButton homePage = new JButton("Home Page");
        UIDesign.setGeneralButton(homePage);
        homePage.setAlignmentX(CENTER_ALIGNMENT);
        homePage.addActionListener(this);

        //Add everything together
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(lightGreenColor);
        add(topPanel);
        add(rateThisQuestionPanel);
        add(dropdownContainer);
        add(spacer);
        add(homePage);
    }

    private static class MyComboBoxRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Set background and foreground colors
            if (isSelected) {
                c.setBackground(darkGreenColor);
                c.setForeground(Color.WHITE);
            } else {
                c.setBackground(white);
                c.setForeground(darkGreenColor);
            }
            return c;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Rate")) {
            System.out.println("Client rates the question, return to client home page");
            RateControl rateControl = controlContainer.getRateControl();
            int updateRate;
            if (Objects.equals(rate.getSelectedItem(), "Satisfied")) {
                updateRate = 1;
            } else {
                updateRate = 0;
            }
            rateControl.rateAnswer(updateRate, questionId, userId);
        } else if (actionCommand.equals("Home Page")){
            ClientHomePageUI homePageUI = new ClientHomePageUI(controlContainer, cardLayout,
                    screens, userId, userName);
            screens.add(homePageUI, "Home Page");
            cardLayout.show(screens, "Home Page");
        }
    }
}
