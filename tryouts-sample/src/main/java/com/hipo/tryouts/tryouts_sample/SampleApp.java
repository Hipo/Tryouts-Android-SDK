package com.hipo.tryouts.tryouts_sample;

import android.app.Application;

import com.hipo.tryouts.androidsdk.Tryouts;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Tryouts.init(
                getApplicationContext(),
                "plxK3N44",
                "fad2323eefd547b8baf2536bce98277c",
                "c51ba4a687f508d436fafc15a88bdbf102878954"
        );

    }
}
