package com.ray.proj.controller;

public abstract class BitManipulator {

    public void reverseBitAt(byte bitmap, int index) {
        if (index > 8 || index < 0) {
            throw new IllegalArgumentException("Index out of bound.");
        }
        bitmap ^= (1 << index);
    }

    public int getBitAt(byte bitmap, int index) {
        if (index > 8 || index < 0) {
            throw new IllegalArgumentException("Index out of bound.");
        }
        return (bitmap >> index) & 1;
    }

    // Get actual value
    public int getValue(byte bitmap) {
        return Byte.toUnsignedInt(bitmap);
    }

    public void reset(byte bitmap) {
        bitmap = 0;
    }
}
