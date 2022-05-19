package com.ray.proj.controller;

import com.ray.proj.model.ClickableToggle;
import com.ray.proj.model.LED;
import com.ray.proj.model.Toggle;
import com.ray.proj.view.AltairComponents;

import javax.swing.*;

public class AltairController extends BitManipulator {

    protected static final int PLAYER1_ADDRESS = 0;
    protected static final int PLAYER2_ADDRESS = 1;
    protected static final int GAME_SPEED_ADDRESS = 2;
    protected static final int GAME_ACC_ADDRESS = 3;

    public final int MAX_VALUE = 0xFF;

    private final byte[] memory;  // byte array for memory data storage, represented by D0~D7 LEDs
    private final LED[] DLEDs;    // D0~D7 LEDs for presenting stored data
    /**/
    private final LED[] statusLEDs;     //statusLEDs
    private final LED[] ALEDs;  // A0~A7 LEDs
    private byte ledMap;        // 8-bit map for LED 0~7. 0 represents turned-off. 1 represents turned-on.

    private final ClickableToggle[] toggles;   // toggle 0~15
    private byte rightToggleMap;    // 8-bit map for toggle 0~7. 0 represents toggled down. 1 represents toggled up
    private byte leftToggleMap;     // 8-bit map for toggle 8~15

    private final JButton[] functionBtns;
    private final Toggle[] functionToggles;

    public AltairController(AltairComponents altairComponents) {
        DLEDs = altairComponents.getDLEDs();
        ALEDs = altairComponents.getALEDs();
        /**/
        statusLEDs = altairComponents.getStatusLEDs();
        toggles = altairComponents.getToggleSwitches();
        functionBtns = altairComponents.getFunctionBtns();
        functionToggles = altairComponents.getFunctionToggles();
        memory = new byte[256];
        ledMap = 0;
        rightToggleMap = 0;
        leftToggleMap = 0;
    }

    /**
     * Examines data at binary address represented by 8 switches
     *
     * @return Data at binary address represented by 8 switches
     */
    public int examine() {
        int address = getValue(rightToggleMap);
        ledMap = rightToggleMap;
        return getValue(memory[address]);
    }

    /**
     * Examines data at given address
     *
     * @param address Data address
     * @return Data at given address
     */
    public int examineAt(int address) {
        return getValue(memory[address]);
    }

    /**
     * Examines data at next address represented by A0~A7 LEDs
     *
     * @return Data at next address
     */
    public int examineNext() {
        int address = getValue(++ledMap);
        return getValue(memory[address]);
    }

    /**
     * Stores data represented by 8 toggles at binary address represented by A0~A7 LEDs
     *
     * @return stored data value
     */
    public int deposit() {
        int ret = getValue(rightToggleMap);
        int address = getValue(ledMap);
        memory[address] = rightToggleMap;
        return ret;
    }

    /**
     * Stores data represented by 8 toggles at next binary address represented by A0~A7 LEDs
     *
     * @return stored data value
     */
    public int depositNext() {
        int ret = getValue(rightToggleMap);
        int address = getValue(++ledMap);
        memory[address] = rightToggleMap;
        return ret;
    }

    /**
     * Zeros out LED map
     */
    public void reset() {
        ledMap = 0;
    }

    /**
     * Zeros out LED map and all memory locations
     */
    public void clear() {
        reset();
        for (int i = 0; i < 256; i++) {
            memory[i] = 0;
        }
    }

    /**
     * Switch memory toggle at #{index}
     *
     * @param index at which the toggle to be switched
     * @return the bit at index after reversed
     */
    public int toggle(int index) {
        if (index < 8) {
            rightToggleMap = reverseBitAt(rightToggleMap, index);
            printMaps();
            return getBitAt(rightToggleMap, index);
        } else {
            leftToggleMap = reverseBitAt(leftToggleMap, index - 8);
            return getBitAt(leftToggleMap, index - 8);
        }
    }

    /**
     * Increments one at given address
     *
     * @param address of memory to be incremented
     */
    public void addOneAt(int address) {
        if (address > memory.length || address < 0) {
            throw new IllegalArgumentException("Address out of bound.");
        }
        memory[address]++;
    }

    /**
     * Turns A0~A7 and D0~D7 on
     */
    public void turnAllOn() {
        for (LED led : ALEDs) {
            led.turnOn();
        }
        for (LED led : DLEDs) {
            led.turnOn();
        }
    }

    /**
     * Turns A0~A7 and D0~D7 off
     */
    public void turnAllOff() {
        for (LED led : ALEDs) {
            led.turnOff();
        }
        for (LED led : DLEDs) {
            led.turnOff();
        }
    }

    public JButton[] getFunctionBtns() {
        return functionBtns;
    }

    public LED[] getDLEDs() {
        return DLEDs;
    }

    public LED[] getALEDs() {
        return ALEDs;
    }

    public LED[] getStatusLEDs() {
        return statusLEDs;
    }

    public ClickableToggle[] getToggles() {
        return toggles;
    }

    public Toggle[] getFunctionToggles() {
        return functionToggles;
    }

    public byte getLedMap() {
        return ledMap;
    }

    public void printMaps() {
        System.out.println("Toggle map is :" + Integer.toString(rightToggleMap, 2));
        System.out.println("LED map is :" + Integer.toString(ledMap, 2));
    }
}
