package com.abbyy.maddogs.maddogsindaplace;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public interface AbbyyLingvoApiService {
    @GET("/api/v1/Minicard")
    Call<Minicard> getMinicard(@Query("text") String text, @Query("srcLang") Integer srcLang, @Query("dstLang") Integer dstLang);
}
