package com.ray.proj.controller.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Sound extends Thread {
    private final String fileName;
    InputStream audio;

    public Sound(String wavFile) {
        fileName = wavFile;
        audio = new BufferedInputStream(getClass().getResourceAsStream("/media/" + fileName + ".wav"));
    }

    @Override
    public void run() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream beep = AudioSystem.getAudioInputStream(audio);
            clip.open(beep);
            clip.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
