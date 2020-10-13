package com.exoplayer.hilt.binds;

import android.util.Log;

import com.exoplayer.hilt.BuildConfig;
import com.exoplayer.hilt.qualifier.AmericaEngine;

import javax.inject.Inject;

/**
 * @author zhangshuai
 * @date 2020/10/9
 * @emial zhangshuai@dushu.io
 * @description 中国引擎
 */
public class ChinaEngine implements Engine {

    private static final String TAG = AmericaEngine.TAG;

    @Inject
    public ChinaEngine() {

    }

    @Override
    public void on() {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "ChinaEngine on: ");
        }
    }

    @Override
    public void off() {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "ChinaEngine off: ");
        }
    }
}
