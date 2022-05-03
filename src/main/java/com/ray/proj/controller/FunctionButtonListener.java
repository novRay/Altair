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
                // TODO: turn all lights off, disable all function buttons except 'ON'
                altairController.clear();
            }
            case ON -> {
                //TODO: turn all lights including three status lights on, enable all function buttons
            }
            case STOP -> {
                JButton source = (JButton) (e.getSource());
                source.setEnabled(false);
                altairController.getFunctionBtns()[RUN].setEnabled(true);
                altairController.endGame();
            }
            case RUN -> {
                JButton source = (JButton) (e.getSource());
                source.setEnabled(false);
                altairController.getFunctionBtns()[STOP].setEnabled(true);
                altairController.startGame();
            }
            case EXAMINE ->  {
                int value = altairController.examine();
                updateToggles();
                showDLEDs(value);
                showALEDs();
            }
            case EXAMINE_NEXT -> {
                int value = altairController.examineNext();
                showALEDs();
                showDLEDs(value);
            }
            case DEPOSIT -> {
                int value = altairController.deposit();
                showDLEDs(value);
            }
            case DEPOSIT_NEXT -> {
                int value = altairController.depositNext();
                showALEDs();
                showDLEDs(value);
            }
            case RESET -> {
                //TODO: turn all LEDs on and then turn all off
                altairController.reset();   //trivial method
                showALEDs();
                showDLEDs(0);
            }
            case CLR -> {
                //TODO: turn all LEDs on and then turn all off
                altairController.clear();
                showALEDs();
                showDLEDs(0);
            }
        }
    }

    public int getFunctionType() {
        return functionType;
    }

    public AltairController getAltairController() {
        return altairController;
    }

    private void updateToggles() {
        byte toggleMap = altairController.getToggleMap();
        for (int i = 0; i < 8; i++) {
            Toggle toggle = altairController.getToggles()[i];
            if ((toggleMap & 1) == 1) {
                toggle.pushUp();
            } else {
                toggle.pullDown();
            }
            toggleMap >>= 1;
        }
    }

    // show A-LED(s) according to toggles
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
}
