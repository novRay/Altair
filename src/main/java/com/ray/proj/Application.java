package com.ray.proj;

import javax.swing.*;
import java.awt.*;

@Deprecated
public class Application extends JFrame {

    public final static int X_BUTTON = 98;
    public final static int Y_UP_BUTTON = 438;
    public final static int Y_DOWN_BUTTON = 480;
    public final static int WIDTH_BUTTON = 45;
    public final static int HEIGHT_BUTTON = 20;

    public final static int X_SWITCH = 103;
    public final static int Y_SWITCH = 454;


    ImageIcon ON_ICON = new ImageIcon("src/main/resources/img/led-on.png");
    ImageIcon OFF_ICON = new ImageIcon("src/main/resources/img/led-off.png");
    ImageIcon SWITCH_MID = new ImageIcon("src/main/resources/img/switch-mid.png");
    ImageIcon SWITCH_UP = new ImageIcon("src/main/resources/img/switch-up.png");
    ImageIcon SWITCH_DOWN = new ImageIcon("src/main/resources/img/switch-down.png");
    ImageIcon BACKGROUND = new ImageIcon("src/main/resources/img/panel.png");

    private JFrame jf;
    private JButton btnStart;
    private JButton btnOff;
    private JLabel label;
    private JLabel background;
    private JLabel LED;

    Application() {
        jf = new JFrame("Test Image change");
        jf.setBounds(0, 0, 1500, 700);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);

        Image image = BACKGROUND.getImage();
        Image temp = image.getScaledInstance(1477, 663, Image.SCALE_SMOOTH);
        ImageIcon background_img = new ImageIcon(temp);
        background = new JLabel("");
        background.setIcon(background_img);
        background.setBounds(0, 0, 1477, 663);

        image = SWITCH_MID.getImage();
        temp = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        SWITCH_MID = new ImageIcon(temp);
        image = SWITCH_UP.getImage();
        temp = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        SWITCH_UP = new ImageIcon(temp);
        image = SWITCH_DOWN.getImage();
        temp = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        SWITCH_DOWN = new ImageIcon(temp);

        label = new JLabel("");
        label.setIcon(SWITCH_MID);
        label.setBounds(X_SWITCH, Y_SWITCH, 30, 30);

        btnStart = new JButton();
        btnStart.addActionListener(e -> {
            btnStart.setEnabled(false);
            btnOff.setEnabled(true);
            label.setIcon(SWITCH_DOWN);
            boot();
        });
        btnStart.setBounds(X_BUTTON, Y_DOWN_BUTTON, WIDTH_BUTTON, HEIGHT_BUTTON);
        btnStart.setBorderPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setFocusPainted(false);
        btnStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jf.getContentPane().add(btnStart);

        btnOff = new JButton();
        btnOff.addActionListener(e -> {
            btnStart.setEnabled(true);
            btnOff.setEnabled(false);
            label.setIcon(SWITCH_UP);
            exit();
        });
        btnOff.setBounds(X_BUTTON, Y_UP_BUTTON, WIDTH_BUTTON, HEIGHT_BUTTON);
        btnOff.setBorderPainted(false);
        btnOff.setContentAreaFilled(false);
        btnOff.setFocusPainted(false);
        btnOff.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel[] leds = new JLabel[8];
        for (int i = 0; i < 8; i++) {
            JLabel ledIcon = new JLabel();
            image = OFF_ICON.getImage();
            temp = image.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
            ON_ICON = new ImageIcon(temp);
            ledIcon.setIcon(ON_ICON);
            ledIcon.setBounds(354 + i * 50, 238, 22, 22);
            leds[i] = ledIcon;
        }
        for (int i = 0; i < 8; i++) {
            jf.getContentPane().add(leds[i]);
        }

        jf.getContentPane().add(btnOff);
        jf.getContentPane().add(label);

        jf.add(background);
        jf.setVisible(true);
    }

    private void boot() {
        System.out.println("Booting...");
    }

    private void exit() {
        System.out.println("Exit.");
    }
}
