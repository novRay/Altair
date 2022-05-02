package com.ray.proj.controller;

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
                //TODO: stop the game
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
                altairController.examine();
                //TODO: show D-LED(s) according to memory value
                //TODO: show A-LED(s) according to switches
            }
            case EXAMINE_NEXT -> {
                int address = 1; // hard coded value for testing, should be obtained from A0~A7 View
                altairController.examineAt(address);
                //TODO: light next LED
            }
            case DEPOSIT -> {
                int address = 0;    // hard coded value for testing, should be obtained from A0~A7 View
                altairController.deposit(address);
                //TODO: show D-LED(s) according to memory value just deposited
            }
            case DEPOSIT_NEXT -> {
                int address = 1;    // // hard coded value for testing, should be obtained from A0~A7 View
                altairController.deposit(address);
            }
            case RESET -> {
                //TODO: turn all LEDs on and then turn all off
                altairController.reset();   //trivial method
            }
            case CLR -> {
                //TODO: turn all LEDs on and then turn all off
                altairController.clear();
            }
        }
    }

    public int getFunctionType() {
        return functionType;
    }

    public AltairController getAltairController() {
        return altairController;
    }
}
