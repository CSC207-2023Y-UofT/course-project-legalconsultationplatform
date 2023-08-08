package driver.screen;

import businessrule.usecase.PostDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static driver.screen.UIDesign.*;

public class TheQuestionTopPanel extends JPanel{
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

    public TheQuestionTopPanel(int userId, String userName, int questionId, String title,
                               String type, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
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

        //Add everything into JPanel
        UIDesign.setBackgroundFrame(this);
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(lightGreenColor);
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPanel.add(helloMessage);
        contentPanel.add(titlePanel);
        contentPanel.add(deadlineLine);
        contentPanel.add(overallPosts);

        this.add(contentPanel);
    }
}
