package com.ray.proj.model;

import javax.swing.*;
import java.awt.*;

public class LED {

    private JLabel label;
    private boolean lighted;
    private int x, y;
    private int width, height;

    public static ImageIcon ON_ICON = new ImageIcon(LED.class.getResource("/img/led-on.png"));
    public static ImageIcon OFF_ICON = new ImageIcon(LED.class.getResource("/img/led-off.png"));

    public LED(int x, int y, int width, int height) {
        Image image, temp;
        label = new JLabel();

        // resize led-off icon
        image = OFF_ICON.getImage();
        temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        OFF_ICON = new ImageIcon(temp);

        // resize led-on icon
        image = ON_ICON.getImage();
        temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ON_ICON = new ImageIcon(temp);

        label.setIcon(OFF_ICON);
        label.setBounds(x, y, width, height);
        lighted = false;
    }


    public JLabel getLabel() {
        return label;
    }

    public boolean isLighted() {
        return lighted;
    }

    public void turnOn() {
        label.setIcon(ON_ICON);
        lighted = true;
    }

    public void turnOff() {
        label.setIcon(OFF_ICON);
        lighted = false;
    }
}
