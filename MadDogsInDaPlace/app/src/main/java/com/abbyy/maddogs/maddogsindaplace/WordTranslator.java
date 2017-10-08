package com.abbyy.maddogs.maddogsindaplace;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayDeque;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public class WordTranslator {

    private static WordTranslator instance;
    private AbbyyLingvoApiService minicardService;
    private AbbyyLingvoApiService tokenService;
    private ArrayDeque<Invocation> queue;
    private String token;

    private WordTranslator() {
//        Retrofit retrofitMinicard = new Retrofit.Builder()
//                .baseUrl("https://developers.lingvolive.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        minicardService = retrofitMinicard.create(AbbyyLingvoApiService.class);


        Retrofit retrofitToken = new Retrofit.Builder()
                .baseUrl("https://developers.lingvolive.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tokenService = retrofitToken.create(AbbyyLingvoApiService.class);
        
        queue = new ArrayDeque<>();
        getToken(new WordTranslator.CallbackLike() {
            @Override
            public void onResponse(String _token) {
                token = _token;
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                }).build();
                Retrofit retrofitMinicard = new Retrofit.Builder()
                        .client(client)
                        .baseUrl("https://developers.lingvolive.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                minicardService = retrofitMinicard.create(AbbyyLingvoApiService.class);
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
        tokenService.getToken().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Log.wtf("onResonse-NError-Token", "Ok?1");
                    callbackLike.onResponse(response.body());
                    Log.wtf("onResonse-NError-Token", "Ok?2");
                    Log.wtf("onResonse-NError-Token", response.body());
                    while (!queue.isEmpty()) {
                        Invocation i = queue.removeFirst();
                        invoke(i);
                    }
                } catch (Exception e) {
                    Log.wtf("onResonse-Error-Token", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.wtf("onFailure-Error-Token", t.getMessage());
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
        minicardService.getMinicard(i.srcWord, i.srcLang, i.dstLang).enqueue(new Callback<Minicard>() {
            @Override
            public void onResponse(Call<Minicard> call, Response<Minicard> response) {
                try {
                    Log.wtf("-Error- response.body", "!!!" + response.body() + "!!!");
                    Log.wtf("-Error- response.", "!!!" + response + "!!!");
                    Log.wtf("-Error- response.raw", "!!!" + response.raw() + "!!!");
                    Log.wtf("-Error- response.tosrt", "!!!" + response.toString() + "!!!");
//                    i.callbackLike.onResponse(response.body().getTranslation().getTranslation());
                } catch (Exception e) {
                    Log.wtf("onResponse-Error-Invoke", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Minicard> call, Throwable t) {
                Log.wtf("onFailure-Error-Invoke", t.getMessage());
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