package com.abbyy.maddogs.maddogsindaplace;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Stack;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public class Word {
    private Integer srcLang = 1033;
    private Integer dstLang = 1049;

    private String srcWord;
    private String dstWord;
    private Stack<TranslationCallback> queue;

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putCharArray("sourceWord", srcWord.toCharArray());
        if (dstWord != null) {
            bundle.putCharArray("translatedWord", dstWord.toCharArray());
        }
        bundle.putInt("sourceLang", srcLang);
        bundle.putInt("destLang", dstLang);
        return bundle;
    }

    public interface TranslationCallback {
        public void onTranslate(String translation);
    }

    public Word(Bundle bundle) {
        srcWord = new String(bundle.getCharArray("sourceWord"));
        char[] trans = bundle.getCharArray("translatedWord");
        if (trans != null) {
            dstWord =  new String(trans);
        } else {
            dstWord = null;
        }
        srcLang = bundle.getInt("sourceLang");
        dstLang = bundle.getInt("destLang");
    }

    public Word(String _srcWord) {
        srcWord = _srcWord;
        queue = new Stack<>();
        WordTranslator.getInstance().getTranslation(srcWord, srcLang, dstLang, new WordTranslator.CallbackLike() {
            @Override
            public void onResponse(String _dstWord) {
                dstWord = _dstWord;
                while(!queue.isEmpty()) {
                    TranslationCallback callback = queue.pop();
                    Log.d("Loop", "working");
                    callback.onTranslate(dstWord);
                }
            }
        });
    }

    public Word(String _srcWord, String _destWord, Integer _srcLang, Integer _destLang) {
        srcWord = _srcWord;
        dstWord = _destWord;
        srcLang = _srcLang;
        dstLang = _destLang;
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
