package com.Sound;

import com.Constants;

import javax.sound.sampled.FloatControl;
import java.util.HashMap;

public class SoundManager {

    private static boolean isMutedMusic = false;
    private static boolean isMutedSFX = false;
    private static HashMap<String, SoundClip> clips;
    private static Constants constants;
    private static float[] floatControlValuesSFX, floatControlValuesTHEME;

    public SoundManager() {
        constants = new Constants();
        createSoundMap();
        createFloatValues();
    }


    private static void createSoundMap() {

        clips = new HashMap<>();

        for(String filename: constants.SOUND_PATHS) {

            String[] tokenResult = filename.split("/");
            String clipName = tokenResult[tokenResult.length - 1].split("\\.")[0];
            SoundClip soundClip;
            if (clipName.contains("theme")) {
                soundClip = new SoundClip(filename, clipName, true, SoundType.THEME);
            } else {
                soundClip = new SoundClip(filename, clipName, false, SoundType.CLIP);
            }

            clips.put(clipName, soundClip);

        }

    }

    private void createFloatValues() {
        FloatControl floatControlSFX = getFloatControlSFX();
        FloatControl floatControlTHEME = getFloatControlTHEME();
//        floatControlValues = new ArrayList<>();
//        floatControlValues.add(floatControl.getMinimum());
//        floatControlValues.add(floatControl.getMaximum());
//        floatControlValues.add(floatControl.getValue());

        floatControlValuesSFX = new float[]{floatControlSFX.getMinimum(), floatControlSFX.getMaximum(), floatControlSFX.getValue()};
        floatControlValuesTHEME = new float[]{floatControlTHEME.getMinimum(), floatControlTHEME.getMaximum(), floatControlTHEME.getValue()};
    }

    public static void adjustSound(float soundLevel, SoundType soundType) {
        for(String clipName: clips.keySet()) {
            SoundClip clip = clips.get(clipName);
            if (clip.getSoundType() == soundType)
                clip.setSoundLevel(soundLevel);
            if (soundType == SoundType.CLIP)
                floatControlValuesSFX[2] = soundLevel;
            else
                floatControlValuesTHEME[2] = soundLevel;
        }
    }

    public static void toggleMusicMute() {
        isMutedMusic = !isMutedMusic;
        for(String clipName: clips.keySet()) {
            SoundClip clip = clips.get(clipName);
            if (clip.getSoundType() == SoundType.THEME)
                clip.toggleMute();
        }
    }

    public static void toggleSFXMute() {
        isMutedSFX = !isMutedSFX;
        for(String clipName: clips.keySet()) {
            SoundClip clip = clips.get(clipName);
            if (clip.getSoundType() == SoundType.CLIP)
                clip.toggleMute();
        }
    }


    public static float[] getFloatControlValuesSFX() {
        return floatControlValuesSFX;
    }

    public static float[] getFloatControlValuesTHEME() {
        return floatControlValuesTHEME;
    }

    public static void playClip(String name) {
        if (clips.containsKey(name))
            clips.get(name).play();
        else
            System.out.println("Clip doesn't exist...");
    }

    public static void stopClip(String name) {
        if (clips.containsKey(name))
            clips.get(name).stop();
        else
            System.out.println("Clip doesn't exist...");
    }

    public static FloatControl getFloatControlSFX() {
        return clips.get("main_menu_theme").getFloatControl();
    }

    public static FloatControl getFloatControlTHEME() {
        return clips.get("correct_answer").getFloatControl();
    }

    public static boolean isIsMutedMusic() {
        return isMutedMusic;
    }

    public static boolean isIsMutedSFX() {
        return isMutedSFX;
    }
}
