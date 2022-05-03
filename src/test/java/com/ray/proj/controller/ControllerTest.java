package com.ray.proj.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {

    AltairController ac = new AltairController();

    @Test
    public void Test1() {
        assertEquals(0, ac.examine());

        // store 0000 0011 at address 0000 0000
        ac.toggle(0);
        ac.toggle(1);
        ac.deposit(0);
        assertEquals(0, ac.examine());  // value at address 0000 0011 should be 0000 0000

        ac.toggle(1);
        ac.toggle(0);
        assertEquals(3, ac.examine());  // value at address 0000 0000 should be 0000 0011

//        ac.setMemory(128, 55);  // store 0011 0111 at address 1000 0000
//        ac.toggle(7);
//        assertEquals(55, ac.examine()); // value at address 1000 0000 should be 0011 0111
//        ac.toggle(7);
    }
}
