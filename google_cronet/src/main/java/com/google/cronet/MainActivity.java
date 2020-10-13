package com.google.cronet;

import com.google.cronet.app.CronetApplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author zhangshuai
 */
public class MainActivity extends AppCompatActivity {

    private Executor mExecutor;
    private String HTTP_URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRequest();
    }

    private void initRequest() {
        mExecutor = Executors.newSingleThreadExecutor();

        UrlRequest.Builder builder = CronetApplication.getCronetEngine()
                .newUrlRequestBuilder(HTTP_URL, new MyUrlRequestCallBack(), mExecutor);
        final UrlRequest urlRequest = builder.build();

        urlRequest.getStatus(new UrlRequest.StatusListener() {
            @Override
            public void onStatus(final int status) {
                Log.i("MyUrlRequestCallBack", "onStatus: " + status);
            }
        });

        findViewById(R.id.acBtn_Cornet_Start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                urlRequest.start();
            }
        });
    }

    /**
     * 提供请求回调的实现
     */
    public static class MyUrlRequestCallBack extends UrlRequest.Callback {

        /**
         * 在服务器发出 HTTP 重定向代码以响应原始请求时调用。
         * 要遵循重定向到达新的目的地，请使用 followRedirect() 方法。
         * 否则，请使用 cancel() 方法
         */
        @Override
        public void onRedirectReceived(final UrlRequest request,
                                       final UrlResponseInfo info,
                                       final String newLocationUrl) throws Exception {
            Log.w("MyUrlRequestCallBack", "onRedirectReceived: " + info.toString());
            request.followRedirect();
        }

        /**
         * 在收到最后一组标头时调用。
         * 仅在遵循所有重定向后才调用 onResponseStarted() 方法。
         */
        @Override
        public void onResponseStarted(final UrlRequest request, final UrlResponseInfo info) throws
                Exception {
            Log.w("MyUrlRequestCallBack", "onResponseStarted: " + info.toString());
            request.read(ByteBuffer.allocateDirect(102400));
        }

        /**
         * 在读取部分响应正文后调用。
         */
        @Override
        public void onReadCompleted(final UrlRequest request,
                                    final UrlResponseInfo info,
                                    final ByteBuffer byteBuffer) throws Exception {
            Log.w("MyUrlRequestCallBack", "onReadCompleted: " + info.toString());
            request.read(ByteBuffer.allocateDirect(102400));
        }

        /**
         * 在网络请求成功完成后调用。
         */
        @Override
        public void onSucceeded(final UrlRequest request, final UrlResponseInfo info) {
            Log.i("MyUrlRequestCallBack", "onSucceeded: " + info.toString());
        }

        /**
         * 在调用 start() 方法后，如果由于任何原因而导致请求失败，则调用此方法。
         */
        @Override
        public void onFailed(UrlRequest request,
                             UrlResponseInfo info,
                             CronetException error) {
            Log.e("MyUrlRequestCallBack", "onFailed: 异常：" + error.toString());
        }

        /**
         * 如果使用 cancel() 方法取消该请求，则调用该方法。
         * 调用该方法之后，就不会再调用 UrlRequest.Callback 类的其他方法。
         * 您可以使用此方法释放为处理请求而分配的资源。
         */
        @Override
        public void onCanceled(final UrlRequest request, final UrlResponseInfo info) {
            super.onCanceled(request, info);

            Log.e("MyUrlRequestCallBack", "onCanceled: ");
        }
    }

}
