package com.abbyy.maddogs.maddogsindaplace;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public interface AbbyyLingvoApiService {
    @Headers("Authorization: Basic ZWNkNjc3NTItNDhiNS00ZDM1LTlkMjAtZDVjYWMwMWNkMTg3OjMzMDQ2MzY4M2NlNzRjNTI4MzFiYjFlZmU0YzA3ZmQy")
    @POST("/api/v1.1/authenticate")
    Call<String> getToken();

    @GET("/api/v1/Minicard")
    Call<ResponseBody> getMinicard(@Query("text") String text, @Query("srcLang") Integer srcLang, @Query("dstLang") Integer dstLang);
}
