package com.hipo.tryouts.androidsdk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tryouts {

    private static Context applicationContext;

    private static String appIdentifier;
    private static String apiKey;
    private static String apiSecret;

    public static void init(
            Context context,
            String appIdentifier,
            String apiKey,
            String apiSecret
            ) {

        Tryouts.applicationContext = context;
        Tryouts.appIdentifier = appIdentifier;
        Tryouts.apiKey = apiKey;
        Tryouts.apiSecret = apiSecret;
        TryoutsService.init();

        controlVersion();
    }

    private static void controlVersion() {


        Call<Application> appReleaseCall = TryoutsService.getApi().getLatestVersion(appIdentifier);

        appReleaseCall.enqueue(new Callback<Application>() {
            @Override
            public void onResponse(Call<Application> call, Response<Application> response) {

                if(response.isSuccessful()) {

                    compareWithRelease(response.body().getLatestRelease());

                }

            }

            @Override
            public void onFailure(Call<Application> call, Throwable t) {

            }
        });

    }

    private static void compareWithRelease(AppRelease appRelease) {

        try {
            PackageInfo pinfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0);
            int versionNumber = pinfo.versionCode;
            String versionName = pinfo.versionName;

            Log.d("Hello", versionName + " " + versionNumber);

            if(versionName.compareTo(appRelease.getReleaseNumber()) < 0) {
                applicationContext.startActivity(TransparentActivity.newIntent(applicationContext, appRelease));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static String getAppIdentifier() {
        return appIdentifier;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getApiSecret() {
        return apiSecret;
    }


}
