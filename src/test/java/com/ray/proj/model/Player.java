package com.ray.proj.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Player implements Runnable{

    private int id;
    JButton button;
    private final String ACTION_KEY = "action_key";

    public Player(int id, JButton button) {
        this.id = id;
        this.button = button;
    }


    @Override
    public void run() {
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Player "+ id +" pressed.");
            }
        };
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0);
        InputMap inputMap = button.getInputMap();
        inputMap.put(key, ACTION_KEY);
        ActionMap actionMap = button.getActionMap();
        actionMap.put(ACTION_KEY, action);
        button.setActionMap(actionMap);
    }
}
