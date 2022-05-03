package com.ray.proj.controller;

import com.ray.proj.model.LED;
import com.ray.proj.model.Toggle;
import com.ray.proj.view.AltairComponents;

import javax.swing.*;

public class AltairController extends BitManipulator {

    public static final int PLAYER1_ADDRESS = 0;
    public static final int PLAYER2_ADDRESS = 1;
    public static final int GAME_SPEED_ADDRESS = 2;
    public static final int GAME_ACC_ADDRESS = 3;

    public final int MAX_VALUE = 255;

    private byte[] memory;  // byte array for memory data storage, represented by D0~D7 LEDs
    private LED[] DLEDs;    // D0~D7 LEDs for presenting stored data

    private LED[] ALEDs;    // A0~A7 LEDs
    private byte ledMap;    // 8-bit map for LED 0~7. 0 represents turned-off. 1 represents turned-on.

    private Toggle[] toggles;   // toggle 0~7
    private byte toggleMap;    // 8-bit map for toggle 0~7. 0 represents toggled down. 1 represents toggled up

    private JButton[] functionBtns;
    private Toggle[] functionToggles;

//    private int stepCursor;

    public AltairController(JButton[] functionBtns) {
        this.functionBtns = functionBtns;
        memory = new byte[256];
        ledMap = 0;
        toggleMap = 0;
//        stepCursor = 0;
    }

    public AltairController(AltairComponents altairComponents) {
        DLEDs = altairComponents.getDLEDs();
        ALEDs = altairComponents.getALEDs();
        toggles = altairComponents.getRightToggles();
        functionBtns = altairComponents.getFunctionBtns();
        memory = new byte[256];
        ledMap = 0;
        toggleMap = 0;
    }

    public AltairController() {
        memory = new byte[256];
        ledMap = 0;
        toggleMap = 0;
    }

    /**
     * Examine data at binary address represented by 8 switches
     * @return Data at binary address represented by 8 switches
     */
    public int examine() {
        int address = getValue(toggleMap);
        return getValue(memory[address]);
    }

    /**
     * Examine data at given address
     * @param address Data address
     * @return Data at given address
     */
    public int examineAt(int address) {
        return getValue(memory[address]);
    }

    /**
     * Examine data at next address represented by A0~A7 LEDs
     * @return Data at next address
     */
    public int examineNext() {
        int address = getValue(++ledMap);
        return getValue(memory[address]);
    }

    /**
     * Store data represented by 8 switches at given address
     * @param address Memory location according to the A0~A7 LEDs
     */
    public void deposit(int address) {
        memory[address] = toggleMap;
    }

    /**
     * Store data represented by 8 toggles at address represented by A0~A7 LEDs
     */
    public int deposit() {
        int ret = getValue(toggleMap);
        int address = getValue(ledMap);
        memory[address] = toggleMap;
        return ret;
    }

    public int depositNext() {
        int ret = getValue(toggleMap);
        int address = getValue(++ledMap);
        memory[address] = toggleMap;
        return ret;
    }


    public void reset() {
        ledMap = 0;
    }

    /**
     * Set 0 for all memory locations
     */
    public void clear() {
        reset();
        for (int i = 0; i < 256; i++) {
            memory[i] = 0;
        }
    }

    /**
     * Toggle memory switch at #{index}
     * @param index at which the switch to be toggled
     */
    public void toggle(int index) {
        reverseBitAt(toggleMap, index);
    }

    public void setMemory(int address, int value) {
        if (address > memory.length || address < 0) {
            throw new IllegalArgumentException("Address out of bound.");
        }
        if (value > MAX_VALUE) {
            throw new IllegalArgumentException("Value out of bound.");
        }
        memory[address] = (byte) value;
    }

    public void addOneAt(int address) {
        if (address > memory.length || address < 0) {
            throw new IllegalArgumentException("Address out of bound.");
        }
        memory[address]++;
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

    public Toggle[] getToggles() {
        return toggles;
    }

    public Toggle[] getFunctionToggles() {
        return functionToggles;
    }

    public byte[] getMemory() {
        return memory;
    }

    public byte getLedMap() {
        return ledMap;
    }

    public byte getToggleMap() {
        return toggleMap;
    }
}
