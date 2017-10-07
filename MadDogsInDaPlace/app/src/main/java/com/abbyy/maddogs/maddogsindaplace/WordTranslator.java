package com.abbyy.maddogs.maddogsindaplace;

import android.util.Log;

import java.util.ArrayDeque;

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
    private AbbyyLingvoApiService service;
    private ArrayDeque<Invocation> queue;
    private String token;

    private WordTranslator() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://developers.lingvolive.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(AbbyyLingvoApiService.class);
        queue = new ArrayDeque<>();
        getToken(new WordTranslator.CallbackLike() {
            @Override
            public void onResponse(String _token) {
                token = _token;
            }
        });
    }

    static public WordTranslator getInstance() {
        if (instance == null) {
            instance = new WordTranslator();
        }
        return instance;
    }

    public void getToken(final CallbackLike callbackLike) {
        service.getToken().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    callbackLike.onResponse(response.body());
                    while (!queue.isEmpty()) {
                        Invocation i = queue.removeFirst();
                        invoke(i);
                    }
                } catch (Exception e) {
                    Log.d("Error", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void getTranslation(String srcWord, Integer srcLang, Integer dstLang, final CallbackLike callbackLike) {
        Invocation i = new Invocation(srcWord, srcLang, dstLang, callbackLike);
        if (token != null) {
            invoke(i);
        } else {
            queue.addFirst(i);
        }
    }

    private void invoke(final Invocation i) {
        service.getMinicard(token, i.srcWord, i.srcLang, i.dstLang).enqueue(new Callback<Minicard>() {
            @Override
            public void onResponse(Call<Minicard> call, Response<Minicard> response) {
                try {
                    i.callbackLike.onResponse(response.body().getTranslation().getTranslation());
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

    interface CallbackLike {
        void onResponse(String _dstWord);
    }

    private class Invocation {
        public String srcWord;
        public Integer srcLang;
        public Integer dstLang;
        public CallbackLike callbackLike;

        public Invocation(String _srcWord, Integer _srcLang, Integer _dstLang, CallbackLike _callbackLike) {
            srcWord = _srcWord;
            srcLang = _srcLang;
            dstLang = _dstLang;
            callbackLike = _callbackLike;
        }
    }
}