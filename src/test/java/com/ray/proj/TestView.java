package com.ray.proj;


import com.ray.proj.controller.TestGameController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TestView extends JFrame {

    public static ImageIcon SWITCH_MID = new ImageIcon("src/main/resources/img/switch-mid.png");
    public static ImageIcon SWITCH_UP = new ImageIcon("src/main/resources/img/switch-up.png");
    public static ImageIcon SWITCH_DOWN = new ImageIcon("src/main/resources/img/switch-down.png");

    JFrame jf;
    JButton b1, b2;
    TestController testController;
    List<JButton> buttonList = new ArrayList<>();

    public TestView() {
        jf = new JFrame();
        jf.setBounds(0, 0, 500, 500);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);


        b1 = new JButton("OFF");
        b1.setBounds(100, 100, 100, 50);

        b2 = new JButton("ON");
        b2.setBounds(200, 200, 100, 50);

        buttonList.add(b1);
        buttonList.add(b2);

        JLabel label = new JLabel("SWITCH-MID");
        label.setIcon(SWITCH_MID);
        label.setBounds(150, 150, 100, 50);
        jf.add(label);

        int[] values = {0, 1, 2, 3, 4, 5};

        TestController b1Listener = new TestController(0, values);
        b1Listener.setLabel(label);
        b1.addActionListener(b1Listener);


        TestController b2Listener = new TestController(1, values);
        b2Listener.setLabel(label);
        b2.addActionListener(b2Listener);

        for (JButton button : buttonList) {
            jf.add(button);
        }
        jf.setVisible(true);

//        TestGameController.setGameButton(b1);
//        TestGameController.start();
        TestGameController testGameController = new TestGameController();
        testGameController.setGameButton(b1, b2);
        testGameController.start();
    }
}
