package com.fandeng.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author zhangshuai
 */
public class ToastUtil {
    public static void show(Context context, String txt) {
        adshow(context, txt);
    }

    private static void adshow(Context context, String txt) {
        Toast.makeText(context, txt, Toast.LENGTH_LONG)
                .show();
    }
}
