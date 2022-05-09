package com.ray.proj.controller;

import com.ray.proj.controller.util.Sound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for toggle switches 0 ~ 15
 */
public class ToggleSwitchListener implements ActionListener {

    AltairController altairController;
    private final int toggleId;

    public ToggleSwitchListener(AltairController altairController, int toggleId) {
        this.altairController = altairController;
        this.toggleId = toggleId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Sound("switch").start();
        System.out.printf("Toggled at %d\n", toggleId);
        int bit = altairController.toggle(toggleId);
        if (bit == 1) {
            altairController.getToggles()[toggleId].pushUp();
        } else {
            altairController.getToggles()[toggleId].pullDown();
        }
    }
}
