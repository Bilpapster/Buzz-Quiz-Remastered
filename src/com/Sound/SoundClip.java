package com.Sound;

import javax.sound.sampled.*;
import java.io.File;

/**
 * A class representing an audio clip, with all the necessary methods for playback, audio level etc. The class has
 * an access modifier of 'Package-private', since the the only other class to which SoundClip is supposed to be visible
 * to is SoundManager
 *
 * @author Fotios-Dimitrios Malakis
 */
class SoundClip {

    private String fileLocation;
    private String clipName;
    private Clip clip;
    private AudioInputStream audioInputStream;
    private FloatControl floatControl;
    private BooleanControl booleanControl;
    private boolean muted = false;
    private float soundLevel = 0.0f;
    private boolean looping;
    private SoundType soundType;

    /**
     * Default SoundClip class constructor which initializes all the necessary objects for
     * the sound clip to be able to handle playback
     *
     * @param fileLocation the location of the sound file inside the directory
     *                     <b>Note: {@code javax.sound} only allows for .wav file playback</b>
     * @param clipName arbitrary name given to clip for ease of access from the {@code SoundManager} class
     * @param looping whether the clip will need to be looping everytime it'll be played back or not
     */
    protected SoundClip(String fileLocation, String clipName, boolean looping, SoundType soundType) {
        this.soundType = soundType;
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
     * Rewinds clip to its start and commences playback. If the {@code SoundClip} object's
     * {@code looping} attribute was previously set to {@code true}, then this will cause the clip to loop continuously
     */
    protected void play() {
        clip.setFramePosition(0);
        if (!looping) {
            clip.start();
        } else {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Checks whether or not the clip is currently playing, and if it is, ceases playback
     */
    protected void stop() {
        if (!clip.isRunning())
            return;
        clip.stop();
    }

    /**
     * Sets the clips' sound level to the given sound level
     * @param soundLevel value to which the clips' sound level will be set to
     */
    protected void setSoundLevel(float soundLevel) {
        this.soundLevel = soundLevel;
        floatControl.setValue(soundLevel);
    }

    /**
     * Toggles current clips' mute
     */
    protected void toggleMute() {
        muted = !muted;
        booleanControl.setValue(muted);
    }

    /**
     * Returns the clips' FloatControl object
     * @return clips' FloatControl object
     */
    protected FloatControl getFloatControl() {
        return floatControl;
    }

    /**
     * Returns the clips' SoundType (currently, either {@bold THEME} or {@bold CLIP}
     * @return the type of the clip
     */
    public SoundType getSoundType() {
        return soundType;
    }



}
