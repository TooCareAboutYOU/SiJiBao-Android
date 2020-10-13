package com.exoplayer.hilt.bean;

/**
 * @author zhangshuai
 * @date 2020/10/9
 * @emial zhangshuai@dushu.io
 * @description
 */
public class DogBean {
    private String name;

    public DogBean(final String mName) {
        name = mName;
    }

    public String getName() {
        return name;
    }

    public void setName(final String mName) {
        name = mName;
    }
}
