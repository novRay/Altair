package com.ray.proj.controller.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound extends Thread {
    private final String fileName;

    public Sound(String wavFile) {
        fileName = wavFile;
    }

    @Override
    public void run() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream beep = AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream("/media/" + fileName + ".wav"));
            clip.open(beep);
            clip.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
