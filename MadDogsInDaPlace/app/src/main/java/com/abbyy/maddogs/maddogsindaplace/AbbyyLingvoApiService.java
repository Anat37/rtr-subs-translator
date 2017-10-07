package com.abbyy.maddogs.maddogsindaplace;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public interface AbbyyLingvoApiService {
    @POST("/api/v1.1/authenticate")
    Call<String> getToken();

    @GET("/api/v1/Minicard")
    Call<Minicard> getMinicard(@Header("Authorization: Bearer {token}") String token, @Query("text") String text, @Query("srcLang") Integer srcLang, @Query("dstLang") Integer dstLang);
}
