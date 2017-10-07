package com.abbyy.maddogs.maddogsindaplace;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public class WordTranslator {

    private static WordTranslator instance;
    AbbyyLingvoApiService service;

    private WordTranslator() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://developers.lingvolive.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(AbbyyLingvoApiService.class);
    }

    static public WordTranslator getInstance() {
        if (instance == null) {
            instance = new WordTranslator();
        }
        return instance;
    }

    interface MyCallback {
        void onResponse(String _dstWord);
    }

    public void getTranslation(String srcWord, Integer srcLang, Integer dstLang, final MyCallback myCallback) {
        service.getMinicard(srcWord, srcLang, dstLang).enqueue(new Callback<Minicard>() {
            @Override
            public void onResponse(Call<Minicard> call, Response<Minicard> response) {
                try {
                    myCallback.onResponse(response.body().getTranslation().getTranslation());
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Minicard> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}