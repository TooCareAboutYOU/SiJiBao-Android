package com.google.cronet.app;

import com.google.android.gms.net.CronetProviderInstaller;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import android.app.Application;

import org.chromium.net.CronetEngine;

import androidx.annotation.NonNull;

/**
 * @author zhangshuai
 */
public class CronetApplication extends Application {

    private static CronetEngine cronetEngine = null;

    public static CronetEngine getCronetEngine() {
        return cronetEngine;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initCronet();
    }

    /**
     * 创建网络请求
     */
    private void initCronet() {
        Task<Void> task = CronetProviderInstaller.installProvider(this);
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull final Exception mE) {

            }
        });

        CronetEngine.Builder builder = new CronetEngine.Builder(this);
        cronetEngine = builder
                .enableHttpCache(CronetEngine.Builder.HTTP_CACHE_IN_MEMORY, 100 * 1024)
                .enableHttp2(true)
                .enableQuic(true)
                .build();
    }

}
