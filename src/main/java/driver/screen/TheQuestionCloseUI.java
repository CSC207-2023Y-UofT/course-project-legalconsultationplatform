package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.RateControl;
import businessrule.usecase.PostDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import static driver.screen.UIDesign.*;

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


    JTextArea inputPostArea = new JTextArea(3, 30);
    String[] rateList = {"Satisfied", "Not satisfied"};
    JComboBox<String> rate = new JComboBox<>(rateList);

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

        //The top spacer
        JPanel topSpacer = addSpacer(30);

        //The rate function
        JLabel rateText = new JLabel("Rate this question");
        JPanel ratePanel = new JPanel();
        UIDesign.setNameFont(rateText);
        rateText.setForeground(Color.white);
        ratePanel.setBackground(darkGreenColor);
        setSizeInLayout(rate, new Dimension(150, 50));
        ratePanel.setLayout(new BoxLayout(ratePanel, BoxLayout.Y_AXIS));
        ratePanel.add(rateText);
        ratePanel.add(rate);
        JPanel rateOverall = new JPanel();
        rateOverall.setOpaque(false);
        JButton rateQuestion = new JButton("Rate");
        rateQuestion.setForeground(darkGreenColor);
        setSizeInLayout(rateQuestion, new Dimension(80, 50));
        rateQuestion.addActionListener(this);
        rateOverall.add(ratePanel);
        rateOverall.add(rateQuestion);

        //Add everything together
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(lightGreenColor);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(topPanel);
        contentPanel.add(topSpacer);
        contentPanel.add(rateOverall);
        this.add(contentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Client rates the question, return to client home page");
        RateControl rateControl = controlContainer.getRateControl();
        int updateRate;
        if (Objects.equals(rate.getSelectedItem(), "Satisfied")) {
            updateRate = 1;
        } else {
            updateRate = 0;
        }
        rateControl.rateAnswer(updateRate, questionId, userId);
    }
}
