package com.ray.proj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for switches numbered 0 ~ 7
 */
public class RightSwitchListener implements ActionListener {

    AltairController altairController;
    private int switchId;

    public RightSwitchListener(AltairController altairController, int switchId) {
        this.altairController = altairController;
        this.switchId = switchId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.printf("Toggled switch %d\n", switchId);
        altairController.toggle(switchId);
        //TODO: change switch direction on panel
    }
}
