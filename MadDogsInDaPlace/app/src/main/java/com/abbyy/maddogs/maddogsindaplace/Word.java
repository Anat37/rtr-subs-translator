package com.abbyy.maddogs.maddogsindaplace;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public class Word {
    static private final Integer srcLang = 1033;
    static private final Integer dstLang = 1049;

    private String srcWord;
    private String dstWord;

    public Word(String _srcWord) {
        srcWord = _srcWord;
        WordTranslator.getInstance().getTranslation(srcWord, srcLang, dstLang, new WordTranslator.CallbackLike() {
            @Override
            public void onResponse(String _dstWord) {
                dstWord = _dstWord;
            }
        });
    }

    public String getSrcWord() {
        return srcWord;
    }

    public String getDstWord() {
        return dstWord;
    }

    public Integer getSrcLang() {
        return srcLang;
    }

    public Integer getDstLang() {
        return dstLang;
    }
}
