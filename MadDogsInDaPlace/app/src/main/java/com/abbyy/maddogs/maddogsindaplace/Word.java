package com.abbyy.maddogs.maddogsindaplace;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public class Word {
    static private final Integer srcLang = 1033;
    static private final Integer dstLang = 1049;

    private String word;
    private String inRussian;

    public Word(String _word) {
        word = _word;
        inRussian = "";
    }

    public String getWord() {
        return word;
    }

    public String translateToRussian() {
        if (!inRussian.equals("")) {
            return inRussian;
        }
        return inRussian;
    }
}
