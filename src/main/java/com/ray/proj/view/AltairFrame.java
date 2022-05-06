package com.ray.proj.view;

import com.ray.proj.controller.AltairController;
import com.ray.proj.controller.FunctionButtonListener;
import com.ray.proj.controller.GameController;
import com.ray.proj.controller.ToggleSwitchListener;
import com.ray.proj.model.ClickableToggle;
import com.ray.proj.model.LED;

import javax.swing.*;
import java.awt.*;


public class AltairFrame extends JFrame {

    private JFrame frame;
    AltairController altairController;
    ImageIcon BACKGROUND = new ImageIcon(getClass().getResource("/img/panel.png"));

    private JLabel background;

    AltairComponents altairComponents;

    public AltairFrame() {
        // load background panel, LEDs, switches and other static resources
        frame = new JFrame("Altair8800 Computer");
        frame.setBounds(0, 0, 1500, 700);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        loadBackground();
        altairComponents = new AltairComponents();

        altairController = new GameController(altairComponents);


        // for each button pair, add actionListeners
        addListenersForBtns();


        // TODO: add all components to frame
        for (LED led : altairComponents.getGameLEDs()) {
            frame.add(led.getLabel());
        }
        for (LED led : altairComponents.getALEDs()) {
            frame.add(led.getLabel());
        }
        for (LED led : altairComponents.getDLEDs()) {
            frame.add(led.getLabel());
        }
        for (ClickableToggle toggle : altairComponents.getToggleSwitches()) {
            frame.add(toggle.getButton());
            frame.add(toggle.getLabel());
        }
        for (int i = 0; i < 10; i++) {
            frame.add(altairComponents.getFunctionBtns()[i]);
        }
        for (int i = 0; i < 9; i++) {
            frame.add(altairComponents.getFunctionToggles()[i].getLabel());
        }
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

    private void addListenersForBtns() {
        JButton[] functionBtns = altairController.getFunctionBtns();
        for (int i = 0; i < functionBtns.length; i++) {
            functionBtns[i].addActionListener(new FunctionButtonListener(altairController, i));
        }

        ClickableToggle[] rightToggles = altairComponents.getToggleSwitches();
        for (int i = 0; i < rightToggles.length; i++) {
            rightToggles[i].getButton().addActionListener(new ToggleSwitchListener(altairController, i));
        }
    }

}
