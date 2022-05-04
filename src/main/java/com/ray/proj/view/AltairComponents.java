package com.ray.proj.view;

import com.ray.proj.model.ClickableToggle;
import com.ray.proj.model.LED;

import javax.swing.*;
import java.awt.*;

public class AltairComponents {

    private LED[] gameLEDs;
    private LED[] ALEDs;
    private LED[] DLEDs;
    private ClickableToggle[] rightToggles;

    private JButton[] functionBtns;

    private JButton btn8, btn15;

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
        rightToggles = new ClickableToggle[8];
        for (int i = 0; i < 8; i++) {
            rightToggles[i] = new ClickableToggle(1250 - 50 * i, 300, 30, 30, false);
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

            functionBtns[i + 1] = new JButton();
            functionBtns[i + 1].setBounds(295 + 50 * i, 480, 45, 20);
            //      stopBtn.setBorderPainted(false);
            functionBtns[i + 1].setContentAreaFilled(false);
            functionBtns[i + 1].setFocusPainted(false);
            functionBtns[i + 1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            i += 2;
        }

        btn8 = new JButton();
        btn8.setBounds(780, 350, 45, 20);
        btn8.setContentAreaFilled(false);
        btn8.setFocusPainted(false);
        btn8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn15 = new JButton();
        btn15.setBounds(345, 350, 45, 20);
        btn15.setContentAreaFilled(false);
        btn15.setFocusPainted(false);
        btn15.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

    public ClickableToggle[] getRightToggles() {
        return rightToggles;
    }

    public JButton[] getFunctionBtns() {
        return functionBtns;
    }

    public JButton getBtn8() {
        return btn8;
    }

    public JButton getBtn15() {
        return btn15;
    }
}
