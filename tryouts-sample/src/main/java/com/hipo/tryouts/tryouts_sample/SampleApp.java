package com.hipo.tryouts.tryouts_sample;

import android.app.Application;

import com.hipo.tryouts.androidsdk.Tryouts;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Tryouts.init(
                getApplicationContext(),
                "eZFkWyPh",
                "7a6c125ece74b790172f5061748240ee",
                "ea72c7874771c22e316f40475bde130a09b82dd0"
        );

    }
}
