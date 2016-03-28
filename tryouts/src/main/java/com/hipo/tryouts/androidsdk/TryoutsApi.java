package com.hipo.tryouts.androidsdk;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface TryoutsApi {

    @GET("applications/{app-identifier}/")
    Call<Application> getLatestVersion(
            @Path("app-identifier") String appIdentifier
    );

}
