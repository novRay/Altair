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
                System.out.println("Examined value is :" + value);
                showDLEDs(value);
                showALEDs();
            }
            case EXAMINE_NEXT -> {
                int value = altairController.examineNext();
                showDLEDs(value);
                showALEDs();
            }
            case DEPOSIT -> {
                int value = altairController.deposit();
                showDLEDs(value);
            }
            case DEPOSIT_NEXT -> {
                int value = altairController.depositNext();
                showDLEDs(value);
                showALEDs();
            }
            case RESET -> {
                //TODO: turn all LEDs on and then turn all off
                altairController.reset();
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
}
