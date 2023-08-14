package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.RateControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static driver.screen.UIDesign.*;

public class RatePanel extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    JFrame rateFrame;
    String[] rateList = {"Satisfied", "Not satisfied"};
    JComboBox<String> rate = new JComboBox<>(rateList);
    int userId;
    int questionId;

    public RatePanel(ControlContainer controlContainer, JFrame rateFrame, int userId, int questionId) {
        this.controlContainer = controlContainer;
        this.rateFrame = rateFrame;
        this.userId = userId;
        this.questionId = questionId;

        JPanel dropdownContainer = new JPanel();
        dropdownContainer.setBackground(UIDesign.whiteColor);
        UIDesign.setSizeInLayout(dropdownContainer, new Dimension(350, 50));

        String[] rateList = {"Satisfied", "Not satisfied"};

        UIDesign.setSizeInLayout(rate, new Dimension(250, 40));
        rate.setRenderer(new MyComboBoxRenderer());

        JButton rateQuestion = new JButton("Rate");
        rateQuestion.setForeground(UIDesign.darkGreenColor);
        UIDesign.setSizeInLayout(rateQuestion, new Dimension(80, 40));
        rateQuestion.addActionListener(this);

        dropdownContainer.setLayout(new FlowLayout());
        dropdownContainer.setOpaque(false);
        dropdownContainer.add(rate);
        dropdownContainer.add(rateQuestion);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(lightGreenColor);
        add(dropdownContainer);

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
                c.setBackground(Color.white);
                c.setForeground(darkGreenColor);
            }
            return c;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RateControl rateControl = controlContainer.getRateControl();
        int updateRate;
        System.out.println("selected item is" + rate.getSelectedItem());
        if (Objects.equals(rate.getSelectedItem(), "Satisfied")) {
            updateRate = 1;
        } else {
            updateRate = 0;
        }
        System.out.println(updateRate);
        rateControl.rateAnswer(updateRate, questionId);
        javax.swing.SwingUtilities.getWindowAncestor(RatePanel.this).dispose();
    }
}
