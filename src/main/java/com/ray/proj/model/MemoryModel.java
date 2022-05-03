package com.ray.proj.model;

import java.util.Arrays;

@Deprecated
public class MemoryModel {

    public final int NUM_BIT = 8;

    public final int MAX_VALUE = 255;

    private final int[] memory;

    public MemoryModel() {
        memory = new int[(1 << NUM_BIT) - 1];
    }

    public int getValue(int address) {
        if (address > memory.length || address < 0) {
            throw new IllegalArgumentException("Address out of bound.");
        }
        return memory[address];
    }

    public void setValue(int address, int value) {
        if (address > memory.length || address < 0) {
            throw new IllegalArgumentException("Address out of bound.");
        }
        if (value > MAX_VALUE) {
            throw new IllegalArgumentException("Value out of bound.");
        }
        memory[address] = value;
    }

    public void incrValue(int address) {
        if (address > memory.length || address < 0) {
            throw new IllegalArgumentException("Address out of bound.");
        }
        memory[address]++;
        if (memory[address] > MAX_VALUE) {
            memory[address] = 0;
        }
    }

    public void clear() {
        Arrays.fill(memory, 0);
    }
}
