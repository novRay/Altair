package com.ray.proj.view;

import com.ray.proj.model.ClickableToggle;
import com.ray.proj.model.LED;
import com.ray.proj.model.Toggle;

import javax.swing.*;
import java.awt.*;

public class AltairComponents {

    private LED[] gameLEDs;                     // LED A8~A15
    private LED[] ALEDs;                        // LED A0~A7
    private LED[] DLEDs;                        // LED D0~D7
    private LED[] statusLEDs;                   // LED status 12
    private ClickableToggle[] toggleSwitches;   // toggle 0~15

    private JButton[] functionBtns;
    private Toggle[] functionToggles;


    public AltairComponents() {
        loadLEDs();
        loadToggles();
        loadButtons();
    }

    private void loadLEDs() {

        //A15~A8
        gameLEDs = new LED[8];
        gameLEDs[0] = new LED(354, 238, 22, 22);
        for (int i = 1; i < 4; i++) {
            gameLEDs[i] = new LED(432 + 52 * (i - 1), 238, 22, 22);
        }
        for (int i = 4; i < 7; i++) {
            gameLEDs[i] = new LED(614 + 52 * (i - 4), 238, 22, 22);
        }
        gameLEDs[7] = new LED(799, 238, 22, 22);

        //A0~A7
        ALEDs = new LED[8];
        for (int i = 0; i < 3; i++) {
            ALEDs[i] = new LED(1268 - 52 * i, 238, 22, 22);
        }
        for (int i = 3; i < 6; i++) {
            ALEDs[i] = new LED(1086 - 52 * (i - 3), 238, 22, 22);
        }
        for (int i = 6; i < 8; i++) {
            ALEDs[i] = new LED(904 - 52 * (i - 6), 238, 22, 22);
        }

        //D0~D7
        DLEDs = new LED[8];
        for (int i = 0; i < 3; i++) {
            DLEDs[i] = new LED(1268 - 52 * i, 125, 22, 22);
        }
        for (int i = 3; i < 6; i++) {
            DLEDs[i] = new LED(1086 - 52 * (i - 3), 125, 22, 22);
        }
        for (int i = 6; i < 8; i++) {
            DLEDs[i] = new LED(904 - 52 * (i - 6), 125, 22, 22);
        }

        //status 12
        statusLEDs = new LED[12];
        for (int i = 0; i < 10; i++) {
            statusLEDs[i] = new LED(200 + 52 * i, 125, 22, 22);
        }
        statusLEDs[10] = new LED(200, 238, 22, 22);
        statusLEDs[11] = new LED(250, 238, 22, 22);

    }

    private void loadToggles() {
        functionToggles = new Toggle[9];
        functionToggles[0] = new Toggle(103, 453, 30, 30, true);
        for (int i = 1; i < 3; i++) {
            functionToggles[i] = new Toggle(255 + 100 * i, 453, 30, 30, true);
        }
        functionToggles[3] = new Toggle(560, 453, 30, 30, true);
        functionToggles[4] = new Toggle(660, 453, 30, 30, true);
        functionToggles[5] = new Toggle(767, 453, 30, 30, true);
        functionToggles[6] = new Toggle(872, 453, 30, 30, true);
        functionToggles[7] = new Toggle(977, 453, 30, 30, true);
        functionToggles[8] = new Toggle(1083, 453, 30, 30, true);

        functionToggles[0].pushUp();    // init power toggle as 'off'

        //toggleSwitches0~15
        toggleSwitches = new ClickableToggle[16];
        for (int i = 0; i < 3; i++) {
            toggleSwitches[i] = new ClickableToggle(1265 - 52 * i, 350, 30, 30, false);
        }
        for (int i = 3; i < 6; i++) {
            toggleSwitches[i] = new ClickableToggle(1083 - 52 * (i - 3), 350, 30, 30, false);
        }
        for (int i = 6; i < 8; i++) {
            toggleSwitches[i] = new ClickableToggle(901 - 52 * (i - 6), 350, 30, 30, false);
        }
        toggleSwitches[8] = new ClickableToggle(796, 350, 30, 30, false);
        for (int i = 9; i < 12; i++) {
            toggleSwitches[i] = new ClickableToggle(715 - 52 * (i - 9), 350, 30, 30, false);
        }
        for (int i = 12; i < 15; i++) {
            toggleSwitches[i] = new ClickableToggle(533 - 52 * (i - 12), 350, 30, 30, false);
        }
        toggleSwitches[15] = new ClickableToggle(351, 350, 30, 30, false);


    }

    private void loadButtons() {
        functionBtns = new JButton[10];

        for (int i = 0; i < 10; i += 2) {
            functionBtns[i] = new JButton();
            functionBtns[i].setBorderPainted(false);
            functionBtns[i].setContentAreaFilled(false);
            functionBtns[i].setFocusPainted(false);
            functionBtns[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            functionBtns[i].setEnabled(false);

            functionBtns[i + 1] = new JButton();
            functionBtns[i + 1].setBorderPainted(false);
            functionBtns[i + 1].setContentAreaFilled(false);
            functionBtns[i + 1].setFocusPainted(false);
            functionBtns[i + 1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            functionBtns[i + 1].setEnabled(false);
        }
        functionBtns[0].setBounds(96, 435, 45, 20);
        functionBtns[1].setBounds(96, 480, 45, 20);
        functionBtns[2].setBounds(345, 435, 45, 20);
        functionBtns[3].setBounds(345, 480, 45, 20);
        functionBtns[4].setBounds(550, 435, 45, 20);
        functionBtns[5].setBounds(550, 480, 45, 20);
        functionBtns[6].setBounds(655, 435, 45, 20);
        functionBtns[7].setBounds(655, 480, 45, 20);
        functionBtns[8].setBounds(760, 435, 45, 20);
        functionBtns[9].setBounds(760, 480, 45, 20);


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

    public LED[] getStatusLEDs() {
        return statusLEDs;
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
