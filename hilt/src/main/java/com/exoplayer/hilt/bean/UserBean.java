package com.exoplayer.hilt.bean;

import com.google.gson.Gson;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

/**
 * @author zhangshuai
 * @date 2020/10/9
 * @emial zhangshuai@dushu.io
 * @description
 */
@ActivityContext
public class UserBean {

    private Context mContext;
    private String name;
    private int age;

    @Inject
    public UserBean(@ActivityContext Context context) {
        this.mContext = context;
    }

    public void setName(final String mName) {
        name = mName;
    }

    public void setAge(final int mAge) {
        age = mAge;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * 弹框
     */
    public void showToast() {
        Toast.makeText(mContext, "name：" + name + "， " + "age：" + age, Toast.LENGTH_SHORT)
                .show();
    }
}
