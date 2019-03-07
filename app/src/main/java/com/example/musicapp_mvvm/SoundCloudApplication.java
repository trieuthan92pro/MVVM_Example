package com.example.musicapp_mvvm;

import android.app.Application;

public class SoundCloudApplication extends Application {
    private static SoundCloudApplication sSoundCloudApplication;

    public static SoundCloudApplication getInstance(){
        return sSoundCloudApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sSoundCloudApplication = this;
    }
}
