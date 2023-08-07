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
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

import static driver.screen.UIDesign.*;

public class TheQuestionUI extends JPanel implements ActionListener {
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

    public TheQuestionUI(ControlContainer controlContainer, CardLayout cardLayout,
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

        UIDesign.setBackgroundFrame(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //UserName and UserId
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);

        //Question title and type
        JPanel titlePanel = UIDesign.questionTitleDrawer(type, title);
        //Question deadline
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deadlineString = "Deadline: " + deadline.format(formatter);
        JLabel deadlineLine = new JLabel(deadlineString);
        UIDesign.setPromptFont(deadlineLine);


        JPanel postScrollPanel = new JPanel();
        postScrollPanel.setLayout(new BoxLayout(postScrollPanel, BoxLayout.Y_AXIS));
        for (PostDisplayFormatter post : postMap.values()) {
            //read all variables from displayFormatter
            String name = post.getName();
            String postText = post.getPostText();
            String userType;
            if (post.isClient()) {
                userType = "Client";
            } else {
                userType = "Attorney";
            }
            String postDate = post.getCreateAt().format(formatter);
            //lines
            String idLine = name + "(" + userType + ")";
            String dateLine = "Posted on: " + postDate;
            //Format them all into a textArea
            JPanel thisPost = UIDesign.singlePostDrawer(idLine, dateLine, postText, userType);
            //Add the postArea into the scrollpanel
            postScrollPanel.add(thisPost);
        }
        JScrollPane postScrollPane = new JScrollPane(postScrollPanel);
        postScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        postScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setSizeInLayout(postScrollPane, new Dimension(350, 200));
        postScrollPane.setOpaque(false);

        JPanel overallPosts = new JPanel();
        JLabel label = new JLabel("Previous discussions");
        UIDesign.setNameFont(label);
        label.setForeground(whiteColor);
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(darkGreenColor);
        labelPanel.add(label);
        overallPosts.setLayout(new BoxLayout(overallPosts, BoxLayout.Y_AXIS));
        overallPosts.setAlignmentX(LEFT_ALIGNMENT);
        overallPosts.add(labelPanel);
        overallPosts.add(postScrollPane);


        //The new post textBox
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

        JPanel topSpacer = addSpacer(30);

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
        JPanel spacer = addSpacer(30);

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
        setSizeInLayout(rateQuestion, postButtonSize);
        rateQuestion.addActionListener(this);
        rateOverall.add(ratePanel);
        rateOverall.add(rateQuestion);

        //The close function
        JButton closeQuestion = new JButton("Close question");
        closeQuestion.addActionListener(this);
        closeQuestion.setForeground(darkGreenColor);
        setSizeInLayout(closeQuestion, new Dimension(130, 50));

        //Add everything into JPanel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(lightGreenColor);
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPanel.add(helloMessage);
        contentPanel.add(titlePanel);
        contentPanel.add(deadlineLine);
        contentPanel.add(overallPosts);
        contentPanel.add(topSpacer);
        contentPanel.add(overallInputPost); // Use the JScrollPane for inputPostArea
        contentPanel.add(spacer);
        contentPanel.add(rateOverall);
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
        } else if ("Rate question".equals(actionCommand)){
            System.out.println("Client rates the question, return to client home page");
            RateControl rateControl = controlContainer.getRateControl();
            int updateRate;
            if (Objects.equals(rate.getSelectedItem(), "Satisfied")){
                updateRate = 1;
            } else{
                updateRate = 0;
            }
            rateControl.rateAnswer(updateRate, questionId, userId);
        }
    }
}
