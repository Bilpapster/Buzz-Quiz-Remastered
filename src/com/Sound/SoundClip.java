package com.Sound;

import javax.sound.sampled.*;
import java.io.File;

/**
 * A class representing an audio clip, with all the necessary methods for playback, audio level etc.
 *
 * @author Fotios-Dimitrios Malakis
 */
public class SoundClip {

    private String fileLocation;
    private String clipName;
    private Clip clip;
    private AudioInputStream audioInputStream;
    private FloatControl floatControl;
    private BooleanControl booleanControl;
    private boolean muted = false;
    private float soundLevel = 0.0f;
    private boolean looping = false;

    /**
     * Default SoundClip class constructor which initializes all the necessary objects for
     * the sound clip to be able to handle playback
     *
     * @param fileLocation the location of the sound file inside the directory
     *                     <b>Note: {@code javax.sound} only allows for .wav file playback</b>
     * @param clipName arbitrary name given to clip for ease of access from the {@code SoundManager} class
     * @param looping whether the clip will need to be looping everytime it'll be played back or not
     */
    protected SoundClip(String fileLocation, String clipName, boolean looping) {
        this.looping = looping;
        this.fileLocation = fileLocation;
        this.clipName = clipName;

        try {

            audioInputStream = AudioSystem.getAudioInputStream(new File(fileLocation).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(soundLevel);
            booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(muted);

        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Checks whether or not the clip is already playing, and if not, commences playback. If the {@code SoundClip} object's
     * {@code looping} attribute was previously set to {@code true}, then this will cause the clip to loop continuously
     * @return {@code true} if clip has commenced playback, {@code false} if clip is already playing
     */
    protected boolean play() {
        if (clip.isRunning())
            return false;

        if (!looping) {
            clip.start();
        } else {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        return true;
    }

    protected boolean stop() {
        if (!clip.isRunning())
            return false;

        clip.stop();
        return true;
    }

    protected void setSoundLevel(float soundLevel) {
        this.soundLevel = soundLevel;
        floatControl.setValue(soundLevel);
    }

    protected void toggleMute() {
        muted = !muted;
        booleanControl.setValue(muted);
    }


}
