package com;

import java.awt.*;
import java.util.HashMap;

/**
 * Class containing all the possible types for the questions
 */
public enum QuestionType {
    History, Movies_and_Series, Science, Sports, Technology, Music, Food_and_Culture, Geography;

    private static final HashMap<QuestionType, Color> colors;

    static {
        colors = new HashMap<>();

        colors.put(History, new Color(218, 83, 44)); // dark orange
        colors.put(Movies_and_Series, new Color(126, 56, 120)); // purple
        colors.put(Science, new Color(0, 163, 0)); // green
        colors.put(Sports, new Color(30,144,255)); // dodger blue (a lighter version of bleu roi)
        colors.put(Technology, new Color(169,3,8)); // darkish red
        colors.put(Music, new Color(255,0,151)); // magenda light pink
        colors.put(Food_and_Culture, new Color(254,75,3)); // blood orange
        colors.put(Geography, new Color(51, 85, 139)); // pantone classic blue
    }

    public static Color getColorOf(QuestionType questionType) {
        return colors.get(questionType);
    }

    @Override
    public String toString() {
        return super.toString().toUpperCase().replaceAll("_", " ");
    }
}
