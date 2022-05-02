package com.ray.proj.model;

public class BitMap {

    private byte bitmap;    // -128 ~ 127

    public BitMap() {
        bitmap = 0;
    }

    public void reverseBitAt(int index) {
        if (index > 8 || index < 0) {
            throw new IllegalArgumentException("Index out of bound.");
        }
        bitmap ^= (1 << index);
    }

    public int getBitAt(int index) {
        if (index > 8 || index < 0) {
            throw new IllegalArgumentException("Index out of bound.");
        }
        return (bitmap >> index) & 1;
    }

    // Get original bitmap with the first bit as the sign bit.
    public byte getBitmap() {
        return bitmap;
    }

    // Get actual value
    public int getValue() {
        return Byte.toUnsignedInt(bitmap);
    }

    public void reset() {
        bitmap = 0;
    }
}
