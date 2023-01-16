package com.sergiu.heapupboxgame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioController {
    private final String audioFilePath;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;

    public AudioController(String path) {
        this.audioFilePath = path;
    }

    public void play() {
        try {
            audioStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        audioClip.stop();
    }
}
