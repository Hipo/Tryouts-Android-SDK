package com.hipo.tryouts.tryouts_sample;

import android.app.Application;

import com.hipo.tryouts.androidsdk.Tryouts;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Tryouts.init(
                getApplicationContext(),
                "m3u7UXmu",
                "d3ff2df1206cad28af8b7da49e05cdb9",
                "b8adeb074bd764df2e32ca5a6332acf5d2597e10"
        );

    }
}
