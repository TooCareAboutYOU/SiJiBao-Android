package com.exoplayer.hilt.qualifier;

import android.util.Log;

import com.exoplayer.hilt.BuildConfig;
import com.exoplayer.hilt.binds.Engine;

import javax.inject.Inject;

/**
 * @author zhangshuai
 * @date 2020/10/9
 * @emial zhangshuai@dushu.io
 * @description
 */
public class AmericaEngine implements Engine {

    public static final String TAG = "Engine";

    @Inject
    public AmericaEngine() {

    }

    @Override
    public void on() {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "AmericaEngine on: ");
        }
    }

    @Override
    public void off() {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "AmericaEngine off: ");
        }
    }
}
