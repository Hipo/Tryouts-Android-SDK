package com.hipo.tryouts.androidsdk;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tryouts {

    private final static String LAST_CHECK_TIME = "LastCheckTime";
    private final static String tryoutsFolderName = "TryoutsScreenshots";

    private static Context applicationContext;
    private final static long checkInverval = 15 * 60 * 1000; // 15 Minutes
    private static long lastCheckTime;

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

    public static void sendFeedback(Context activityContext) {
        applicationContext.startActivity(FeedbackActivity.newIntent(applicationContext, getScreenshotBase64(activityContext)));
    }

    private static String getScreenshotBase64 (Context context) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            View v1 = ((Activity) context).getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.DEFAULT);


//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();
//
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
//            Uri uri = Uri.fromFile(imageFile);
//            intent.setDataAndType(uri, "image/*");
//            context.startActivity(intent);

        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
            return null;
        }
    }


    private static void controlVersion() {

        lastCheckTime = SharedPrefUtil.getLong(applicationContext, LAST_CHECK_TIME, 0);

        if (lastCheckTime == 0 || checkInverval < System.currentTimeMillis() - lastCheckTime) {

            Call<Application> appReleaseCall = TryoutsService.getApi().getLatestVersion(appIdentifier);

            appReleaseCall.enqueue(new Callback<Application>() {
                @Override
                public void onResponse(Call<Application> call, Response<Application> response) {

                    if (response.isSuccessful()) {
                        lastCheckTime = System.currentTimeMillis();
                        SharedPrefUtil.putLong(applicationContext, LAST_CHECK_TIME, lastCheckTime);
                        compareWithRelease(response.body().getLatestRelease());
                    }

                }

                @Override
                public void onFailure(Call<Application> call, Throwable t) {

                }
            });
        }

    }

    private static void compareWithRelease(AppRelease appRelease) {

        try {
            PackageInfo pinfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0);
            int versionNumber = pinfo.versionCode;
            String versionName = pinfo.versionName;
            String[] installedAppMajorMinors = versionName.split("\\.");
            String[] latestAppMajorMinors = appRelease.getReleaseNumber().split("\\.");
            for (int i = 0; i < installedAppMajorMinors.length; i++) {
                if (latestAppMajorMinors != null && i < latestAppMajorMinors.length) {
                    if (Integer.valueOf(installedAppMajorMinors[i]) < Integer.valueOf(latestAppMajorMinors[i])) {
                        applicationContext.startActivity(TransparentActivity.newIntent(applicationContext, appRelease));
                    }
                }
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
