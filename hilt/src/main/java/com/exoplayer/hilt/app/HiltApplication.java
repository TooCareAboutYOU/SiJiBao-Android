package com.exoplayer.hilt.app;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * @author zhangshuai
 */
@HiltAndroidApp
public class HiltApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
