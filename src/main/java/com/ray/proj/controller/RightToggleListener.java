package com.ray.proj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for switches numbered 0 ~ 7
 */
public class RightToggleListener implements ActionListener {

    AltairController altairController;
    private int toggleId;

    public RightToggleListener(AltairController altairController, int toggleId) {
        this.altairController = altairController;
        this.toggleId = toggleId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.printf("Toggled at %d\n", toggleId);
        int bit = altairController.toggle(toggleId);
        if (bit == 1) {
            altairController.getToggles()[toggleId].pushUp();
        } else {
            altairController.getToggles()[toggleId].pullDown();
        }
    }
}
