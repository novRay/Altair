package com.ray.proj.controller;

import com.ray.proj.model.LED;
import com.ray.proj.model.Toggle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.ray.proj.controller.FunctionTypeField.*;

public class FunctionButtonListener implements ActionListener {

    private final int functionType;

    GameController altairController;

    public FunctionButtonListener(AltairController altairController, int functionType) {
        this.altairController = (GameController) altairController;
        this.functionType = functionType;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (functionType) {
            case OFF -> {
                altairController.getFunctionToggles()[0].pushUp();
                altairController.clear();
                altairController.endGame(); // end running game
                setAllBtnsEnabled(false);
                altairController.getFunctionBtns()[ON].setEnabled(true);
                turnAllLEDsOff();
                //TODO: turn status lights off
            }
            case ON -> {
                altairController.getFunctionToggles()[0].pullDown();
                setAllBtnsEnabled(true);
                flink();
                //TODO: turn status lights on
            }
            case STOP -> {
                pushUpToggle(altairController.getFunctionToggles()[1]);
                JButton source = (JButton) (e.getSource());
                source.setEnabled(false);
                altairController.getFunctionBtns()[RUN].setEnabled(true);
                altairController.endGame();
            }
            case RUN -> {
                pullDownToggle(altairController.getFunctionToggles()[1]);
                JButton source = (JButton) (e.getSource());
                source.setEnabled(false);
                altairController.getFunctionBtns()[STOP].setEnabled(true);
                altairController.startGame();
            }
            case EXAMINE ->  {
                pushUpToggle(altairController.getFunctionToggles()[2]);
                int value = altairController.examine();
                System.out.println("Examined value is :" + value);
                showDLEDs(value);
                showALEDs();
            }
            case EXAMINE_NEXT -> {
                pullDownToggle(altairController.getFunctionToggles()[2]);
                int value = altairController.examineNext();
                showDLEDs(value);
                showALEDs();
            }
            case DEPOSIT -> {
                pushUpToggle(altairController.getFunctionToggles()[3]);
                int value = altairController.deposit();
                showDLEDs(value);
            }
            case DEPOSIT_NEXT -> {
                pullDownToggle(altairController.getFunctionToggles()[3]);
                int value = altairController.depositNext();
                showDLEDs(value);
                showALEDs();
            }
            case RESET -> {
                pushUpToggle(altairController.getFunctionToggles()[4]);
                altairController.reset();
                showALEDs();
                showDLEDs(0);
                flink();
            }
            case CLR -> {
                pullDownToggle(altairController.getFunctionToggles()[4]);
                altairController.clear();
                showALEDs();
                showDLEDs(0);
                flink();
            }
        }
    }

    public int getFunctionType() {
        return functionType;
    }

    public AltairController getAltairController() {
        return altairController;
    }

    // show A-LED(s) according to LED map
    private void showALEDs() {
        byte ledMap = altairController.getLedMap();
        for (int i = 0; i < 8; i++) {
            LED led = altairController.getALEDs()[i];
            if ((ledMap & 1) == 1) {
                led.turnOn();
            } else {
                led.turnOff();
            }
            ledMap >>= 1;
        }
    }

    //show D-LED(s) according to memory value
    private void showDLEDs(int value) {
        for (int i = 0; i < 8; i++) {
            LED led = altairController.getDLEDs()[i];
            if ((value & 1) == 1) {
                led.turnOn();
            } else {
                led.turnOff();
            }
            value >>= 1;
        }
    }

    private void setAllBtnsEnabled(boolean b) {
        for (JButton btn : altairController.getFunctionBtns()) {
            btn.setEnabled(b);
        }
    }

    private void flink() {
        turnAllLEDsOn();
        Timer timer = new Timer(650, e -> {
            turnAllLEDsOff();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void turnAllLEDsOn() {
        altairController.turnAllOn();
        altairController.turnAllGameLEDsOn();
    }

    private void turnAllLEDsOff() {
        altairController.turnAllOff();
        altairController.turnAllGameLEDsOff();
    }

    private void pullDownToggle(Toggle t) {
        t.pullDown();
        Timer timer = new Timer(400, e -> {
            t.reset();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void pushUpToggle(Toggle t) {
        t.pushUp();
        Timer timer = new Timer(400, e -> {
            t.reset();
        });
        timer.setRepeats(false);
        timer.start();
    }
}
