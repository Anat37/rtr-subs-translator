package com.abbyy.maddogs.maddogsindaplace;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

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

    public String getInRussian() {
        if (inRussian.equals("")) {
            inRussian = translateToRussian();
        }
        return inRussian;
    }

    public String translateToRussian() {
        Log.wtf("word-translate", "Вход!!!"); // TODO
        if (!inRussian.equals("")) {
            Log.wtf("word-translate", "Выход в начале"); // TODO
            return inRussian;
        }

        Log.wtf("word", "-1"); // TODO
        String url = String.format("https://developers.lingvolive.com/api/v1/Minicard?text=%s&srcLang=%d&dstLang=%d", word, srcLang, dstLang);
        Log.wtf("word", "0"); // TODO
        try {
            Log.wtf("word", "1"); // TODO
            URL requestUrl = new URL(url);
            Log.wtf("word", "2"); // TODO
            HttpURLConnection huc = (HttpURLConnection) requestUrl.openConnection();
            Log.wtf("word", "2.4"); // TODO
            huc.setRequestMethod("GET");
            Log.wtf("word", "2.8"); // TODO
            huc.connect();
            Log.wtf("word", "3"); // TODO
            huc.getResponseCode(); // TODO
            Log.wtf("word", "4"); // TODO
            Log.wtf("word-translate", String.valueOf(huc.getResponseCode())); // TODO
        } catch (Exception exception) {
            // TODO
            Log.wtf("AAAAA!!!1!!!1!11!", exception); // TODO
            Log.wtf("word-translate", "Упс!"); // TODO
            inRussian = "";
        }

        return inRussian;
    }
}
