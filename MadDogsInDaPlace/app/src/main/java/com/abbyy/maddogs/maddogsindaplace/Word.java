package com.abbyy.maddogs.maddogsindaplace;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public class Word {
    private String word;
    private String inRussian;

    private static final Integer NO_IMAGE = -1;


    public Word(String _word, String _inRussian) {
        word = _word;
        inRussian = _inRussian;
    }

    public String getWord() {
        return word;
    }

    public String getInRussian() {
        return inRussian;
    }
}
