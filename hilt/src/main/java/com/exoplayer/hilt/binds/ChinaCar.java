package com.exoplayer.hilt.binds;

import javax.inject.Inject;

/**
 * @author zhangshuai
 * @date 2020/10/9
 * @emial zhangshuai@dushu.io
 * @description 中国车
 */
public class ChinaCar {

    private Engine mEngine;
    private String name;

    @Inject
    public ChinaCar(final Engine mEngine) {
        this.mEngine = mEngine;
    }

    public String getName() {
        return name;
    }

    public void setName(final String mName) {
        name = mName;
    }

    public Engine getEngine() {
        return mEngine;
    }
}
