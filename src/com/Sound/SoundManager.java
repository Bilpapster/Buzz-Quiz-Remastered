package com.Sound;

import com.Constants;

import javax.sound.sampled.FloatControl;
import java.util.HashMap;

public class SoundManager {

    private HashMap<String, SoundClip> clips;
    private Constants constants;

    public SoundManager() {
        constants = new Constants();
        createSoundMap();
    }


    private void createSoundMap() {

        clips = new HashMap<>();

        for(String filename: constants.SOUND_PATHS) {

            String[] tokenResult = filename.split("/");
            String clipName = tokenResult[tokenResult.length - 1].split("\\.")[0];
            SoundClip soundClip;
            if (clipName.contains("theme")) {
                soundClip = new SoundClip(filename, clipName, true, SoundType.Theme);
            } else {
                soundClip = new SoundClip(filename, clipName, false, SoundType.Clip);
            }

            clips.put(clipName, soundClip);

        }

    }

    public void adjustSound(float soundLevel, SoundType soundType) {
        for(String clipName: clips.keySet()) {
            SoundClip clip = clips.get(clipName);
            if (clip.getSoundType() == soundType)
                clip.setSoundLevel(soundLevel);
        }
    }

    public void playClip(String name) {
        if (clips.containsKey(name))
            clips.get(name).play();
        else
            System.out.println("Clip doesn't exist...");
    }

    public void stopClip(String name) {
        if (clips.containsKey(name))
            clips.get(name).stop();
        else
            System.out.println("Clip doesn't exist...");
    }

    public FloatControl getFloatControl() {
        return clips.get("main_menu_theme").getFloatControl();
    }


}
