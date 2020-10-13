package com.exoplayer.hilt.qualifier;

import com.exoplayer.hilt.binds.ChinaCar;
import com.exoplayer.hilt.binds.ChinaEngine;

import dagger.Module;
import dagger.Provides;
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
public class AmericaModule {

    @Provides
    @MadeInCN
    ChinaCar provideChinaCar() {
        return new ChinaCar(new ChinaEngine());
    }

    @Provides
    @MadeInUSA
    ChinaCar provideChinaCar2() {
        return new ChinaCar(new AmericaEngine());
    }
}
