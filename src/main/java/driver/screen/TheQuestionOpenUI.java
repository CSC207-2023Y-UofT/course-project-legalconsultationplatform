package driver.screen;

import adapter.controller.CloseQuestionControl;
import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
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

public class TheQuestionOpenUI extends JPanel implements ActionListener {
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

    public TheQuestionOpenUI(ControlContainer controlContainer, CardLayout cardLayout,
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

        //The input post
        inputPostArea.setLineWrap(true);
        inputPostArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(inputPostArea);
        inputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setSizeInLayout(inputScrollPane, new Dimension(250, 50));

        JLabel input = new JLabel("Type your content here");
        JPanel inputPanel = new JPanel();
        UIDesign.setNameFont(input);
        input.setForeground(Color.white);
        inputPanel.add(input);
        inputPanel.setBackground(darkGreenColor);
        JPanel overallInput = new JPanel();
        overallInput.setOpaque(false);
        overallInput.setLayout(new BoxLayout(overallInput, BoxLayout.Y_AXIS));
        overallInput.setAlignmentX(LEFT_ALIGNMENT);
        overallInput.add(inputPanel);
        overallInput.add(inputScrollPane);

        //add the textbox and the reply button together
        JButton postReply = new JButton("Post");
        postReply.setForeground(darkGreenColor);
        Dimension postButtonSize = new Dimension(80, 50);
        setSizeInLayout(postReply, postButtonSize);
        postReply.addActionListener(this);
        JPanel overallInputPost = new JPanel();
        overallInputPost.setLayout(new FlowLayout());
        overallInputPost.add(overallInput);
        overallInputPost.add(postReply);
        overallInputPost.setBackground(lightGreenColor);

        //The spacer
        JPanel middleSpacer = addSpacer(30);

        //The close function
        JButton closeQuestion = new JButton("Close question");
        closeQuestion.addActionListener(this);
        closeQuestion.setForeground(darkGreenColor);
        setSizeInLayout(closeQuestion, new Dimension(130, 50));

        ///Add everything together
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(lightGreenColor);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(topPanel);
        contentPanel.add(topSpacer);
        contentPanel.add(overallInputPost);
        contentPanel.add(middleSpacer);
        contentPanel.add(closeQuestion);

        this.add(contentPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Post".equals(actionCommand)){
            System.out.println("The user posts a reply.");
            PostControl postControl = controlContainer.getPostControl();
            postControl.createPost(questionId, userId, inputPostArea.getText());
        } else if ("Close question".equals(actionCommand)){
            System.out.println("Client closes the question, return to client home page");
            CloseQuestionControl closeQuestionControl = controlContainer.getCloseQuestionControl();
            closeQuestionControl.closeQuestion(questionId, userId);
        }
    }
}
