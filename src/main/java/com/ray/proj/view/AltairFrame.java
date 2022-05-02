package com.ray.proj.view;

import com.ray.proj.controller.AltairController;
import com.ray.proj.controller.FunctionButtonListener;
import com.ray.proj.controller.GameController;
import com.ray.proj.model.LED;

import javax.swing.*;
import java.awt.*;

import static com.ray.proj.controller.FunctionTypeField.RUN;
import static com.ray.proj.controller.FunctionTypeField.STOP;

public class AltairFrame extends JFrame {

    private JFrame frame;
    ImageIcon BACKGROUND = new ImageIcon(getClass().getResource("/img/panel.png"));

    private LED[] gameLEDs;
    private JLabel background;
    private JButton[] functionBtns;

    private JButton btn8, btn15;

    public AltairFrame() {
        // TODO: load background panel, LEDs, switches and other static resources
        frame = new JFrame("Altair8800 Computer");
        frame.setBounds(0, 0, 1500, 700);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        loadBackground();
        loadGameLEDs();
        loadSwitches();
        loadButtons();

        AltairController altairController = new GameController(gameLEDs, btn8, btn15, functionBtns);

        // TODO: for each button pair, add actionListeners,
        functionBtns[STOP].addActionListener(new FunctionButtonListener(altairController, STOP));
        functionBtns[RUN].addActionListener(new FunctionButtonListener(altairController, RUN));

        // TODO: add all components to frame
        for (LED led : gameLEDs) {
            frame.add(led.getLabel());
        }
        for (int i = 0; i < 10; i++) {
            frame.add(functionBtns[i]);
        }
        frame.add(btn8);
        frame.add(btn15);
        frame.add(background);
        frame.setVisible(true);
    }

    private void loadBackground() {
        Image image = BACKGROUND.getImage();
        Image temp = image.getScaledInstance(1477, 663, Image.SCALE_SMOOTH);
        ImageIcon background_img = new ImageIcon(temp);
        background = new JLabel("");
        background.setIcon(background_img);
        background.setBounds(0, 0, 1477, 663);
    }


    private void loadGameLEDs() {
        gameLEDs = new LED[8];
        for (int i = 0; i < 8; i++) {
            gameLEDs[i] = new LED(354 + 50 * i, 238, 22, 22);
        }
    }

    private void loadSwitches() {

    }

    private void loadButtons() {
        functionBtns = new JButton[10];

        for (int i = 0; i < 10;) {
            functionBtns[i] = new JButton();
            functionBtns[i].setBounds(295 + 50 * i, 435, 45, 20);
            //      stopBtn.setBorderPainted(false);
            functionBtns[i].setContentAreaFilled(false);
            functionBtns[i].setFocusPainted(false);
            functionBtns[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            functionBtns[i + 1] = new JButton();
            functionBtns[i + 1].setBounds(295 + 50 * i, 480, 45, 20);
            //      stopBtn.setBorderPainted(false);
            functionBtns[i + 1].setContentAreaFilled(false);
            functionBtns[i + 1].setFocusPainted(false);
            functionBtns[i + 1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            i += 2;
        }

        btn8 = new JButton();
        btn8.setBounds(780, 350, 45, 20);
        btn8.setContentAreaFilled(false);
        btn8.setFocusPainted(false);
        btn8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn15 = new JButton();
        btn15.setBounds(345, 350, 45, 20);
        btn15.setContentAreaFilled(false);
        btn15.setFocusPainted(false);
        btn15.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

}
