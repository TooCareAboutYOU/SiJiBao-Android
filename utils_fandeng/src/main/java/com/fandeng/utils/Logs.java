package com.fandeng.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Logs {

    public static void error() {
        LogUtils.e();
    }

    public static int getAssets(Context context) {
        int lines = 0;
        AssetManager manager = context.getAssets();
        try {
            InputStream inputStream = manager.open("joke.txt");
            InputStreamReader isr = new InputStreamReader(inputStream,
                                                          StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String length;
            while ((length = br.readLine()) != null) {
                sb.append(length + "\n");
                lines++;
            }
            //关流
            br.close();
            isr.close();
            inputStream.close();
//            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
