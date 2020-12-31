package com;

import javax.swing.ImageIcon;
import java.awt.*;
import java.util.HashMap;

/**
 * An enumeration that represents the type of a question. The enumeration contains all the question types that are
 * supported by the game up to that moment.
 *
 * @author Fotios-Dimitrios Malakis
 */
public enum QuestionType {
    History, Movies_and_Series, Science, Sports, Technology, Music, Food_and_Culture, Geography;

    /* stores the color associated with each one of the question types*/
    private static final HashMap<QuestionType, Color> colors = new HashMap<>();

    /* stores the background image associated with each one of the question types */
    private static final HashMap<QuestionType, ImageIcon> backgroundImages = new HashMap<>();

    static {
        initializeColors();
        initializeBackgroundImages();
    }

    /**
     * Initializes the colors associated with each one of the question types.
     */
    private static void initializeColors() {
        colors.put(History, new Color(218, 83, 44));                // dark orange
        colors.put(Movies_and_Series, new Color(126, 56, 120));     // purple
        colors.put(Science, new Color(0, 163, 0));                  // green
        colors.put(Sports, new Color(30, 144, 255));                // dodger blue (a lighter version of bleu roi)
        colors.put(Technology, new Color(169, 3, 8));               // darkish red
        colors.put(Music, new Color(255, 0, 151));                  // almost magenda - light pink
        colors.put(Food_and_Culture, new Color(254, 75, 3));        // pumpkin orange
        colors.put(Geography, new Color(51, 85, 139));              // pantone classic blue
    }

    /**
     * Initializes the background images associated with each one of the question types.
     */
    private static void initializeBackgroundImages() {
        for (QuestionType questionType : QuestionType.values()) {
            backgroundImages.put(questionType, new ImageIcon("src/resources/Background Images/" + questionType.toString() + ".png"));
        }
    }

    /**
     * Getter for the color associated with the question type.
     *
     * @param questionType the question type to get the color of
     * @return the color associated with the given question type
     */
    public static Color getColorOf(QuestionType questionType) {
        return colors.get(questionType);
    }

    /**
     * Getter for the background image associated with the question type.
     *
     * @param questionType the question type to get the background image of
     * @return the background image associated with the given question type
     */
    public static ImageIcon getBackgroundImageOf(QuestionType questionType) {
        return backgroundImages.get(questionType);
    }

    /**
     * Overridden <code>toString</code> method for the question type representation as String.
     *
     * @return the question type name in capital letters. The words separated by space.
     */
    @Override
    public String toString() {
        return super.toString().toUpperCase().replaceAll("_", " ");
    }
}
