package com.exoplayer.hilt.provides;

import com.exoplayer.hilt.bean.DogBean;

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
public class DogModule {
    @Provides
    DogBean provideDogBean() {
        return new DogBean("京巴犬");
    }
}
