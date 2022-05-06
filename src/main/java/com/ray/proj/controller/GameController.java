package com.ray.proj.controller;

import com.ray.proj.model.ClickableToggle;
import com.ray.proj.model.LED;
import com.ray.proj.model.Toggle;
import com.ray.proj.view.AltairComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends AltairController {

    // game state constant
    private static final int PREPARING = 0;     // game program not running
    private static final int SERVING = 1;       // waiting for either player to serve
    private static final int IN_PROGRESS = 2;   // game in progress

    private int state;              // game state: preparing, serving or in-progress
    private int interval;           // moving interval, ranging from 98ms to 453ms
    private double acceleration;    // moving acceleration rate, ranging from 0% to 5.1%

    private final ClickableToggle gameToggle1, gameToggle2; // game "paddles" for two players
    private GameListener gameListener1, gameListener2;      // keystroke listener
    private final LED[] GameLEDs;                           // LEDs for displaying ball's movement track

    private Timer timer1, timer2;       // two timers for moving direction control
    private boolean hitLeft, hitRight;  // players' state

    public GameController(AltairComponents altairComponents) {
        super(altairComponents);
        this.GameLEDs = altairComponents.getGameLEDs();
        this.gameToggle2 = altairComponents.getToggleSwitches()[8];
        this.gameToggle1 = altairComponents.getToggleSwitches()[15];
        state = PREPARING;
    }

    public void startGame() {
        turnAllGameLEDsOff();
        state = SERVING;
        interval = calculateInterval(examineAt(GAME_SPEED_ADDRESS));
        acceleration = calculateAcc(examineAt(GAME_ACC_ADDRESS));
        gameListener1 = new GameListener();
        gameListener2 = new GameListener();
        gameToggle1.getButton().addKeyListener(gameListener1);
        gameToggle2.getButton().addKeyListener(gameListener2);
        gameToggle1.getButton().requestFocus();
//        gameToggle1.getButton().doClick();
//        gameToggle1.getButton().doClick();
        System.out.println("Game started.");
    }

    public void endGame() {
        turnAllGameLEDsOff();
        state = PREPARING;
        gameToggle1.getButton().removeKeyListener(gameListener1);
        gameToggle2.getButton().removeKeyListener(gameListener2);
        if (timer1 != null) {
            timer1.stop();
        }
        if (timer2 != null) {
            timer2.stop();
        }
        System.out.println("Game stopped");
    }

    private void moveLeftNext(int lastInterval) {
        if (state == PREPARING) {
            return;
        }
        timer1 = new Timer(Math.min(lastInterval, interval), new ActionListener() {
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
                    timer1.setDelay((int) (timer1.getDelay() * (1 - acceleration)));
                } else {
                    // ball reaches left edge
                    ((Timer) e.getSource()).stop();
                    if (hitLeft) {
                        // player1 manages to hit, switch direction and reset player1's state
                        hitLeft = false;
                        moveRightNext(timer1.getDelay());
                    } else {
                        // player1 fails to hit, penalize one score and keep the direction
                        addOneAt(PLAYER1_ADDRESS);
                        System.out.println("Player 1 missed! Total miss: " + examineAt(PLAYER1_ADDRESS));
                        GameLEDs[0].turnOff();
                        moveLeftNext(interval);
                    }
                }
            }
        });
        timer1.start();
    }

    private void moveRightNext(int lastInterval) {
        if (state == PREPARING) {
            return;
        }
        timer2 = new Timer(Math.min(lastInterval, interval), new ActionListener() {
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
                    timer2.setDelay((int) (timer2.getDelay() * (1 - acceleration)));
                } else {
                    // ball reaches right edge
                    ((Timer) e.getSource()).stop();
                    if (hitRight) {
                        // player2 manages to hit, switch direction and reset player1's state
                        hitRight = false;
                        moveLeftNext(timer2.getDelay());
                    } else {
                        // player1 fails to hit, penalize one score and keep the direction
                        addOneAt(PLAYER2_ADDRESS);
                        System.out.println("Player 2 missed! Total miss: " + examineAt(PLAYER2_ADDRESS));
                        GameLEDs[7].turnOff();
                        moveRightNext(interval);
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
                    toggle(gameToggle1);
                    if (state == SERVING) {
                        System.out.println("Player 1 served the ball!");
                        state = IN_PROGRESS;
                        moveRightNext(interval);
                    } else {
                        checkHit(0);
                    }
                }
                case KeyEvent.VK_0 -> {
                    toggle(gameToggle2);
                    if (state == SERVING) {
                        System.out.println("Player 2 served the ball!");
                        state = IN_PROGRESS;
                        moveLeftNext(interval);
                    } else {
                        checkHit(7);
                    }
                }
            }
        }
    }

    private void toggle(Toggle t) {
        t.pushUp();
        Timer timer = new Timer(100, e -> {
            t.pullDown();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private int calculateInterval(int v) {
        // interval = e^(-v/120 + 6) + 25, ranging from 98ms to 453ms
        return (int) (Math.exp(-(double) v / 120 + 6) + 50);
    }

    private double calculateAcc(int a) {
        return (double) a / 5000;   // range from 0% to 5.1%
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
