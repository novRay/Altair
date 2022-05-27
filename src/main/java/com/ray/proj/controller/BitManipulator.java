package com.ray.proj.controller;

public abstract class BitManipulator {

    protected byte reverseBitAt(byte bitmap, int index) {
        if (index > 8 || index < 0) {
            throw new IllegalArgumentException("Index out of bound.");
        }
        return (byte) (bitmap ^ (1 << index));
    }

    protected int getBitAt(byte bitmap, int index) {
        if (index > 8 || index < 0) {
            throw new IllegalArgumentException("Index out of bound.");
        }
        return (bitmap >> index) & 1;
    }

    // Get unsigned value
    protected int getValue(byte bitmap) {
        return Byte.toUnsignedInt(bitmap);
    }

}
