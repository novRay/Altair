package com.ray.proj.model;

import javax.swing.*;
import java.awt.*;

public class Toggle {

    private JLabel label;
    private int x, y;
    private int width, height;

    public static ImageIcon SWITCH_MID = new ImageIcon(Toggle.class.getResource("/img/switch-mid.png"));
    public static ImageIcon SWITCH_UP = new ImageIcon(Toggle.class.getResource("/img/switch-up.png"));
    public static ImageIcon SWITCH_DOWN = new ImageIcon(Toggle.class.getResource("/img/switch-down.png"));

    public Toggle(int x, int y, int width, int height, boolean isFunctionToggle) {
        Image image, temp;
        label = new JLabel();

        // resize switch-mid icon
        image = SWITCH_MID.getImage();
        temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        SWITCH_MID = new ImageIcon(temp);

        // resize switch-up icon
        image = SWITCH_UP.getImage();
        temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        SWITCH_UP = new ImageIcon(temp);

        // resize switch-down icon
        image = SWITCH_DOWN.getImage();
        temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        SWITCH_DOWN = new ImageIcon(temp);

        if (isFunctionToggle) {
            label.setIcon(SWITCH_MID);
            label.setBounds(x, y, width, height);
        } else {
            label.setIcon(SWITCH_DOWN);
            label.setBounds(x, y, width, height);
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public void pushUp() {
        label.setIcon(SWITCH_UP);
    }

    public void reset() {
        label.setIcon(SWITCH_MID);
    }

    public void pullDown() {
        label.setIcon(SWITCH_DOWN);
    }

}
