package driver.screen;

import adapter.controller.CloseQuestionControl;
import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
import adapter.controller.RateControl;
import businessrule.usecase.PostDisplayFormatter;
import entity.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

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


    JTextArea inputPostArea = new JTextArea();
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


        //UserName and UserId
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);
        //Question title and type
        String titleLineString = "(" + type + ")" + title;
        JLabel titleLine = new JLabel(titleLineString);
        titleLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Question deadline
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deadlineString = "Deadline: " + deadline.format(formatter);
        JLabel deadlineLine = new JLabel(deadlineString);


        //The scrollable posts
        int numberOfPosts = postMap.size();
        JScrollPane postScrollPane = new JScrollPane();
        JPanel postScrollPanel = new JPanel();
        postScrollPanel.setLayout(new GridLayout(numberOfPosts, 1));
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
            JTextArea postArea = new JTextArea();
            postArea.setEditable(false);
            postArea.setLineWrap(true);
            String overallText = idLine + "\n" + dateLine + "\n" + postText;
            postArea.setText(overallText);
            postArea.setPreferredSize(new Dimension(300, 30));
            //Add the postArea into the scrollpanel
            postScrollPanel.add(postArea);
        }
        postScrollPane.add(postScrollPanel);


        //The new post textBox
        inputPostArea.setLineWrap(true);
        inputPostArea.setWrapStyleWord(true);
        inputPostArea.setPreferredSize(new Dimension(400, 100));

        //The rate function
        DropDownPanel ratePanel = new DropDownPanel(new JLabel("Rate this question"), rate);

        //The three buttons
        JPanel buttons = new JPanel();
        JButton postReply = new JButton("Post reply");
        JButton closeQuestion = new JButton("Close question");
        JButton rateQuestion = new JButton("Rate question");

        buttons.add(postReply);
        buttons.add(closeQuestion);
        buttons.add(rateQuestion);
        postReply.addActionListener(this);
        closeQuestion.addActionListener(this);
        rateQuestion.addActionListener(this);

        //Add everything into JPanel
        this.add(helloMessage);
        this.add(titleLine);
        this.add(deadlineLine);
        this.add(postScrollPane);
        this.add(inputPostArea);
        this.add(ratePanel);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Post Reply".equals(actionCommand)){
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
