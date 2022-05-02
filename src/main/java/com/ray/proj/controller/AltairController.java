package com.ray.proj.controller;

import com.ray.proj.model.BitMap;
import com.ray.proj.model.MemoryModel;

import javax.swing.*;

public class AltairController {

    public static final int PLAYER1_ADDRESS = 0;
    public static final int PLAYER2_ADDRESS = 1;
    public static final int GAME_SPEED_ADDRESS = 2;
    public static final int GAME_ACC_ADDRESS = 3;

    private MemoryModel memoryModel;

    private BitMap memorySwitches;    // 8-bit for switch 0~7. 0 represents toggled down, 1 represents toggled up
//    private BitMap gameSwitches;    // 8-bit for switch 8~15. 0 represents toggled down, 1 represents toggled up

    private JButton[] functionBtns;

//    private int stepCursor;

    public AltairController(JButton[] functionBtns) {
        this.functionBtns = functionBtns;
        memoryModel = new MemoryModel();
        memorySwitches = new BitMap();
//        gameSwitches = new BitMap();
//        stepCursor = 0;
    }

    public AltairController() {
        memoryModel = new MemoryModel();
        memorySwitches = new BitMap();
    }

//    public int singleStep() {
//        stepCursor++;
//        return stepCursor;
//    }

    /**
     * Examine data at binary address represented by 8 switches
     * @return Data at binary address represented by 8 switches
     */
    public int examine() {
        int address = memorySwitches.getValue();
        return memoryModel.getValue(address);
    }

    /**
     * Examine data at given address
     * @param address Data address
     * @return Data at given address
     */
    public int examineAt(int address) {
        return memoryModel.getValue(address);
    }

    /**
     * Store data represented by 8 switches at given address
     * @param address Memory location according to the A0~A8 LEDs
     * @return
     */
    public void deposit(int address) {
        int data = memorySwitches.getValue();
        memoryModel.setValue(address, data);
    }

    public void reset() {
//        stepCursor = 0;
    }

    /**
     * Set 0 for all memory locations
     */
    public void clear() {
        memoryModel.clear();
    }

    /**
     * Toggle memory switch at #{index}
     * @param index at which the switch to be toggled
     */
    public void toggle(int index) {
        memorySwitches.reverseBitAt(index);
    }

    public void setMemory(int address, int value) {
        memoryModel.setValue(address, value);
    }

    public void addOneAt(int address) {
        memoryModel.incrValue(address);
    }

    public JButton[] getFunctionBtns() {
        return functionBtns;
    }


}
