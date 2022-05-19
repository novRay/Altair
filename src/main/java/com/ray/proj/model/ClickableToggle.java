package com.ray.proj.model;

import javax.swing.*;
import java.awt.*;

public class ClickableToggle extends Toggle {

    private final JButton button;

    public ClickableToggle(int x, int y, int width, int height, boolean isFunctionToggle) {
        super(x, y, width, height, isFunctionToggle);
        button = new JButton();
        button.setBounds(x + 2, y + 2, width - 5, height - 5);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public JButton getButton() {
        return button;
    }
}
