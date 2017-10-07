package com.abbyy.maddogs.maddogsindaplace;

import java.util.List;
import java.util.Stack;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public class Word {
    static private final Integer srcLang = 1033;
    static private final Integer dstLang = 1049;

    private String srcWord;
    private String dstWord;
    private Stack<TranslationCallback> queue;

    public interface TranslationCallback {
        public void onTranslate(String translation);
    }

    public Word(String _srcWord) {
        srcWord = _srcWord;
        queue = new Stack<>();
        WordTranslator.getInstance().getTranslation(srcWord, srcLang, dstLang, new WordTranslator.CallbackLike() {
            @Override
            public void onResponse(String _dstWord) {
                dstWord = _dstWord;
                while(!queue.empty()) {
                    TranslationCallback callback = queue.pop();
                    callback.onTranslate(dstWord);
                }
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

    public void tryTranslation(TranslationCallback callback) {
        if (dstWord != null) {
            callback.onTranslate(dstWord);
        } else {
            queue.push(callback);
        }
    }
}
