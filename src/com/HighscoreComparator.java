package com;

import java.util.Comparator;
import java.util.Map;

/**
 * A stand-in class which implements the compare method for use in sorting the highscores
 */
public class HighscoreComparator implements Comparator<Map.Entry<String,Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return (o2.getValue().compareTo(o1.getValue()));
    }
}
