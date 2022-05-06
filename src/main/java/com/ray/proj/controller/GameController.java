package com.ray.proj.controller;

import com.ray.proj.model.LED;
import com.ray.proj.view.AltairComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends AltairController {

    // game status constant
    private static final int PREPARING = 0;
    private static final int SERVING = 1;
    private static final int IN_PROGRESS = 2;

    private GameListener gameListener1, gameListener2;

    private final JButton gameButton1, gameButton2;    // game "paddles" for two players
    private final LED[] GameLEDs;   // LEDs for displaying ball's movement track

    private int interval;          // ball's moving interval, lower value is faster
    private int acceleration;   // ball's moving acceleration
    private int state;         // game state: preparing, serving or in-progress

    private boolean hitLeft, hitRight;  // player state
    private Timer timer1, timer2;   // two timers for controlling moving direction

    public GameController(AltairComponents altairComponents) {
        super(altairComponents);
        this.GameLEDs = altairComponents.getGameLEDs();
        this.gameButton1 = altairComponents.getBtn8();
        this.gameButton2 = altairComponents.getBtn15();
        state = PREPARING;
    }

    public void startGame() {
        turnAllGameLEDsOff();
        state = SERVING;
        interval = calculateInterval(examineAt(GAME_SPEED_ADDRESS));
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
        turnAllGameLEDsOff();
        state = PREPARING;
        gameButton1.removeKeyListener(gameListener1);
        gameButton2.removeKeyListener(gameListener2);
        if (timer1 != null) {
            timer1.stop();
        }
        if (timer2 != null) {
            timer2.stop();
        }
        System.out.println("Stop");
    }

    private void moveLeftNext() {
        if (state == PREPARING) {
            return;
        }
        timer1 = new Timer(interval, new ActionListener() {
            private int currId = 7;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (state == PREPARING) {
                    return;
                }
                if (currId > 0) {
                    GameLEDs[currId].turnOff();
                    currId--;
                    GameLEDs[currId].turnOn();
                } else {
                    // ball reaches left edge
                    ((Timer) e.getSource()).stop();
                    if (hitLeft) {
                        // player1 manages to hit, switch direction and reset player1's state
                        hitLeft = false;
                        moveRightNext();
                    } else {
                        // player1 fails to hit, penalize one score and keep the direction
                        addOneAt(PLAYER1_ADDRESS);
                        System.out.println("Player 1 missed! Total miss: " + examineAt(PLAYER1_ADDRESS));
                        GameLEDs[0].turnOff();
                        moveLeftNext();
                    }
                }
            }
        });
        timer1.start();
    }

    private void moveRightNext() {
        if (state == PREPARING) {
            return;
        }
        timer2 = new Timer(interval, new ActionListener() {
            private int currId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (state == PREPARING) {
                    return;
                }
                if (currId < 7) {
                    GameLEDs[currId].turnOff();
                    currId++;
                    GameLEDs[currId].turnOn();
                } else {
                    // ball reaches right edge
                    ((Timer) e.getSource()).stop();
                    if (hitRight) {
                        // player2 manages to hit, switch direction and reset player1's state
                        hitRight = false;
                        moveLeftNext();
                    } else {
                        // player1 fails to hit, penalize one score and keep the direction
                        addOneAt(PLAYER2_ADDRESS);
                        System.out.println("Player 2 missed! Total miss: " + examineAt(PLAYER2_ADDRESS));
                        GameLEDs[7].turnOff();
                        moveRightNext();
                    }
                }
            }
        });
        timer2.start();
    }

    /**
     * Checks whether the LED has been lighted when player strokes the key.
     * If not, increments corresponding player's missed-count
     *
     * @param ledId The index of LED to be checked
     */
    private void checkHit(int ledId) {
        if (ledId == 0) {
            if (!GameLEDs[ledId].isLighted()) {
                addOneAt(PLAYER1_ADDRESS);
                System.out.println("Player 1 too early! Total miss: " + examineAt(PLAYER1_ADDRESS));
            } else {
                hitLeft = true;
            }
        } else if (ledId == 7) {
            if (!GameLEDs[ledId].isLighted()) {
                addOneAt(AltairController.PLAYER2_ADDRESS);
                System.out.println("Player 2 too early! Total miss: " + examineAt(PLAYER2_ADDRESS));
            } else {
                hitRight = true;
            }
        }
    }

    /**
     * A keyListener for monitoring player's keystroke action.
     * If game state is serving, turns it into in-progress.
     * If game state is in-progress, checks if it is a hit.
     * Otherwise, gives no response.
     */
    private class GameListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_1 -> {
                    //TODO: set switch
                    if (state == SERVING) {
                        System.out.println("Player 1 starts the game!");
                        state = IN_PROGRESS;
                        moveRightNext();
                    } else {
                        checkHit(0);
                    }
                }
                case KeyEvent.VK_0 -> {
                    //TODO: set switch
                    if (state == SERVING) {
                        System.out.println("Player 2 starts the game!");
                        state = IN_PROGRESS;
                        moveLeftNext();
                    } else {
                        checkHit(7);
                    }
                }
            }
        }
    }

    private int calculateInterval(int v) {
        // interval = e^(-v/120 + 6) + 25, ranging from 73ms to 428ms
        return (int) (Math.exp(-v / 120 + 6) + 25);
    }

    public void turnAllGameLEDsOn() {
        for (LED led : GameLEDs) {
            led.turnOn();
        }
    }

    public void turnAllGameLEDsOff() {
        for (LED led : GameLEDs) {
            led.turnOff();
        }
    }

}
