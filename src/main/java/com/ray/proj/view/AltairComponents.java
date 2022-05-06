package com.ray.proj.view;

import com.ray.proj.model.ClickableToggle;
import com.ray.proj.model.LED;
import com.ray.proj.model.Toggle;

import javax.swing.*;
import java.awt.*;

public class AltairComponents {

    private LED[] gameLEDs;
    private LED[] ALEDs;
    private LED[] DLEDs;
    private ClickableToggle[] toggleSwitches; // toggle 0~15

    private JButton[] functionBtns;
    private Toggle[] functionToggles;


    public AltairComponents() {
        loadLEDs();
        loadToggles();
        loadButtons();
    }

    private void loadLEDs() {
        gameLEDs = new LED[8];
        for (int i = 0; i < 8; i++) {
            gameLEDs[i] = new LED(354 + 50 * i, 238, 22, 22);
        }

        ALEDs = new LED[8];
        for (int i = 0; i < 8; i++) {
            ALEDs[i] = new LED(1250 - 50 * i, 238, 22, 22);
        }

        DLEDs = new LED[8];
        for (int i = 0; i < 8; i++) {
            DLEDs[i] = new LED(1250 - 50 * i, 100, 22, 22);
        }
    }

    private void loadToggles() {
        functionToggles = new Toggle[9];
        for (int i = 0; i < 9; i++) {
            functionToggles[i] = new Toggle(295 + 100 * i, 450, 30, 30, true);
        }
        functionToggles[0].pushUp();    // init power toggle as 'off'

        toggleSwitches = new ClickableToggle[16];
        for (int i = 0; i < 8; i++) {
            toggleSwitches[i] = new ClickableToggle(1250 - 50 * i, 300, 30, 30, false);
        }
        for (int i = 8; i < 16; i++) {
            toggleSwitches[i] = new ClickableToggle(1050 - 50 * i, 300, 30, 30, false);
        }

    }

    private void loadButtons() {
        functionBtns = new JButton[10];

        for (int i = 0; i < 10;) {
            functionBtns[i] = new JButton();
            functionBtns[i].setBounds(295 + 50 * i, 435, 45, 20);
            //      stopBtn.setBorderPainted(false);
            functionBtns[i].setContentAreaFilled(false);
            functionBtns[i].setFocusPainted(false);
            functionBtns[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            functionBtns[i].setEnabled(false);

            functionBtns[i + 1] = new JButton();
            functionBtns[i + 1].setBounds(295 + 50 * i, 480, 45, 20);
            //      stopBtn.setBorderPainted(false);
            functionBtns[i + 1].setContentAreaFilled(false);
            functionBtns[i + 1].setFocusPainted(false);
            functionBtns[i + 1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            functionBtns[i + 1].setEnabled(false);

            i += 2;
        }
        functionBtns[1].setEnabled(true);   // enable 'ON' button
    }

    public LED[] getGameLEDs() {
        return gameLEDs;
    }

    public LED[] getALEDs() {
        return ALEDs;
    }

    public LED[] getDLEDs() {
        return DLEDs;
    }

    public ClickableToggle[] getToggleSwitches() {
        return toggleSwitches;
    }

    public JButton[] getFunctionBtns() {
        return functionBtns;
    }

    public Toggle[] getFunctionToggles() {
        return functionToggles;
    }

}
