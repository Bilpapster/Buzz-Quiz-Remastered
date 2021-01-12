package com.Sound;

import com.Constants;

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
            if (clipName.equals("main_menu_theme")) {
                soundClip = new SoundClip(filename, clipName, true);
            } else {
                soundClip = new SoundClip(filename, clipName, false);
            }

            clips.put(clipName, soundClip);

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


}
