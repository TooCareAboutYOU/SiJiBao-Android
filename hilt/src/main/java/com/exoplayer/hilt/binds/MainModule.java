package com.exoplayer.hilt.binds;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author zhangshuai
 * @date 2020/10/9
 * @emial zhangshuai@dushu.io
 * @description
 */
@Module
@InstallIn(ActivityComponent.class)
public interface MainModule {
    /**
     * 绑定引擎
     */
    @Binds
    Engine bindEngine(ChinaEngine mChinaEngine);
}
