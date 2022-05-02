package com.ray.proj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestController implements ActionListener {

    private int[] values;
    JLabel label;

    private int actionType;

    public TestController(int actionType, int[] values) {
        this.values = values;
        this.actionType = actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public void setValue(int idx, int val) {
        values[idx] = val;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (actionType) {
            case 0 -> {
                System.out.println(values[actionType]);
                label.setText("SWITCH-DOWN");
                label.setIcon(TestView.SWITCH_DOWN);
                turnToMid();
            }
            case 1 -> {
                System.out.println(values[actionType]);
                label.setText("SWITCH-UP");
                label.setIcon(TestView.SWITCH_UP);
                turnToMid();
            }
        }
    }

    private void turnToMid() {
        Timer timer = new Timer(600, e -> {
            label.setText("SWITCH-MID");
            label.setIcon(TestView.SWITCH_MID);
        });
        timer.setRepeats(false);
        timer.start();
    }

    public int[] getValues() {
        return values;
    }

    public int getActionType() {
        return actionType;
    }

    public void play() {

    }
}
