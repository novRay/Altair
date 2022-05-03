package com.ray.proj.controller;

import com.ray.proj.model.LED;
import com.ray.proj.view.AltairComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends AltairController {

    private static final int EASY = 0;
    private static final int MEDIUM = 1;
    private static final int HARD = 2;

    private GameListener gameListener1, gameListener2;

    public JButton gameButton1, gameButton2;
    private LED[] leds;

    private int speed;
    private int acceleration;
    private boolean started;
    private boolean enabled;

    private boolean hitLeft, hitRight;
    private Timer timer1, timer2;


    public GameController(LED[] LEDs, JButton gameButton1, JButton gameButton2, JButton[] functionBtns) {
        super(functionBtns);
        this.leds = LEDs;
        this.gameButton1 = gameButton1;
        this.gameButton2 = gameButton2;
        enabled = false;
        started = false;
    }

    public GameController(AltairComponents altairComponents) {
        super(altairComponents);
        this.leds = altairComponents.getGameLEDs();
        this.gameButton1 = altairComponents.getBtn8();
        this.gameButton2 = altairComponents.getBtn15();
        enabled = false;
        started = false;
    }

    public void startGame() {
        turnAllOff();
        enabled = true;
        speed = examineAt(GAME_SPEED_ADDRESS);
        acceleration = examineAt(GAME_ACC_ADDRESS);
        gameListener1 = new GameListener();
        gameListener2 = new GameListener();
        gameButton1.addKeyListener(gameListener1);
        gameButton2.addKeyListener(gameListener2);
        gameButton1.requestFocus();
        gameButton1.doClick();
        System.out.println("Start");
    }

    public void endGame() {
        turnAllOff();
        enabled = false;
        started = false;
        gameButton1.removeKeyListener(gameListener1);
        gameButton2.removeKeyListener(gameListener2);
        timer1.stop();
        timer2.stop();
        System.out.println("Stop");
    }

    private void moveRightToLeft() {
        moveLeftNext();
    }

    private void moveLeftToRight() {
        moveRightNext();
    }

    private void moveLeftNext() {
        if (!enabled) {
            return;
        }
        timer1 = new Timer(200, new ActionListener() {
            private int currId = 7;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enabled) {
                    return;
                }
                if (currId > 0) {
                    leds[currId].turnOff();
                    currId--;
                    leds[currId].turnOn();
                } else {
                    ((Timer)e.getSource()).stop();
                    if (hitLeft) {
                        hitLeft = false;
                        moveRightNext();
                    } else {
                        addOneAt(PLAYER1_ADDRESS);
                        System.out.println("Player 1 missed! Total miss: " + examineAt(PLAYER1_ADDRESS));
                        leds[0].turnOff();
                        moveLeftNext();
                    }
                }
            }
        });
        timer1.start();
    }

    private void moveRightNext() {
        if (!enabled) {
            return;
        }
        timer2 = new Timer(200, new ActionListener() {
            private int currId = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enabled) {
                    return;
                }
                if (currId < 7) {
                    leds[currId].turnOff();
                    currId++;
                    leds[currId].turnOn();
                } else {
                    ((Timer)e.getSource()).stop();
                    if (hitRight) {
                        hitRight = false;
                        moveLeftNext();
                    } else {
                        addOneAt(PLAYER2_ADDRESS);
                        System.out.println("Player 2 missed! Total miss: " + examineAt(PLAYER2_ADDRESS));
                        leds[7].turnOff();
                        moveRightNext();
                    }
                }
            }
        });
        timer2.start();
    }

    private void checkHit(int ledId) {
        if (ledId == 0) {
            if (!leds[ledId].isLighted()) {
                addOneAt(PLAYER1_ADDRESS);
                System.out.println("Player 1 too early! Total miss: " + examineAt(PLAYER1_ADDRESS));
            } else {
                hitLeft = true;
            }
        } else {
            if (!leds[ledId].isLighted()) {
                addOneAt(AltairController.PLAYER2_ADDRESS);
                System.out.println("Player 2 too early! Total miss: " + examineAt(PLAYER2_ADDRESS));
            } else {
                hitRight = true;
            }
        }
    }

    public class GameListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_1 -> {
                    //TODO: set switch
                    if (!started) {
                        System.out.println("Player 1 starts the game!");
                        started = true;
                        moveLeftToRight();
                    } else {
                        checkHit(0);
                    }
                }
                case KeyEvent.VK_0 -> {
                    //TODO: set switch
                    if (!started) {
                        System.out.println("Player 2 starts the game!");
                        started = true;
                        moveRightToLeft();
                    } else {
                        checkHit(7);
                    }
                }
            }
        }
    }

    public void turnAllOn() {
        for (LED led : leds) {
            led.turnOn();
        }
        System.out.println("All on");
    }

    public void turnAllOff() {
        for (LED led : leds) {
            led.turnOff();
        }
        System.out.println("All Off!");
    }

}
