package com.ray.proj.controller;

import com.ray.proj.model.Player;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestGameController {

    AltairController altairController;

    private boolean started;

    private JButton btn1, btn2;
    private GameListener lis1, lis2;

    Player p0, p1;


    public TestGameController(JButton b0, JButton b1) {
        p0 = new Player(0, b0);
        p1 = new Player(1, b1);
    }

    public TestGameController() {

    }

    public void setGameButton(JButton btn1, JButton btn2) {
        this.btn1 = btn1;
        this.btn2 = btn2;
    }

    public void start() {
        lis1 = new GameListener();
        lis2 = new GameListener();
        btn1.addKeyListener(lis1);
        btn2.addKeyListener(lis2);
    }

    public class GameListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_0 -> {
                    if (!started) {
                        System.out.println("Player 0 starts the game!");
                        started = true;
                    } else {
                        System.out.println("Pressed 0");
                    }
                }
                case KeyEvent.VK_1 -> {
                    if (!started) {
                        System.out.println("Player 1 starts the game!");
                        started = true;
                    } else {
                        System.out.println("Pressed 1");
                    }
                }
            }
        }
    }

}
