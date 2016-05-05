package com.hipo.tryouts.androidsdk;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface TryoutsApi {

    @GET("applications/{app-identifier}/")
    Call<Application> getLatestVersion(
            @Path("app-identifier") String appIdentifier
    );

    @Headers("Content-Type: application/json")
    @POST("applications/{app-identifier}/feedback/")
    Call<Feedback> sendFeedback(
            @Header("Authorization") String keyAndSecret,
            @Path("app-identifier") String appID,
            @Body Feedback feedback
    );
}


