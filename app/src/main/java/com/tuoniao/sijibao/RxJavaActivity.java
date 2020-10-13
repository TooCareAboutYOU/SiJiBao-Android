package com.tuoniao.sijibao;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivitys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
                e.onError(new Throwable("发生错误了"));
//                e.onComplete();
            }
        })
                // 1. 当Observable每发送1次数据事件就会调用1次
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Log.d(TAG, "doOnEach: " + integerNotification.getValue());
                    }
                })
                // 2. 执行Next事件前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doOnNext: " + integer);
                    }
                })
                // 3. 执行Next事件后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doAfterNext: " + integer);
                    }
                })
                // 4. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doOnComplete: ");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "doOnError: " + throwable.getMessage());
                    }
                })
                // 6. 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe: ");
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doAfterTerminate: ");
                    }
                })
                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doFinally: ");
                    }
                })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(final Observable<Throwable> mThrowableObservable) throws
                            Exception {
                        return Observable.error(mThrowableObservable.blockingLast());
                    }
                })
//                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(final Observable<Throwable> mThrowableObservable) throws
//                            Exception {
//                        return mThrowableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
//                            @Override
//                            public ObservableSource<?> apply(final Throwable mThrowable) throws
//                                    Exception {
//                                Log.e(TAG, "retryWhen ");
//                                return Observable.error(mThrowable);
//                            }
//                        });
//                    }
//                })
//                //不拦截
//                .onErrorResumeNext(new Observable<Integer>() {
//                    @Override
//                    protected void subscribeActual(final Observer<? super Integer> observer) {
//                        Log.e(TAG, "onErrorResumeNext ");
//                        observer.onNext(2);
//                        observer.onComplete();
//                    }
//                })
//                //拦截
//                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
//                    @Override
//                    public ObservableSource<? extends Integer> apply(final Throwable mThrowable) throws
//                            Exception {
//                        Log.e(TAG, "onErrorResumeNext ");
//                        return Observable.just(4);
//                    }
//                })
//                //重新发送一次
//                .retry(1, new Predicate<Throwable>() {
//                    @Override
//                    public boolean test(final Throwable mThrowable) throws Exception {
//                    Log.e(TAG, "retry: ");
//                        return true;
//                    }
//                })
//                //是否重写发送数据 false 持续发送
//                .retryUntil(new BooleanSupplier() {
//                    @Override
//                    public boolean getAsBoolean() throws Exception {
//                        Log.e(TAG, "retryUntil: ");
//                        return false;
//                    }
//                })
//                //错误异常捕捉后，自行处理1个特殊事件，正常完成
//                .onErrorReturn(new Function<Throwable, Integer>() {
//                    @Override
//                    public Integer apply(final Throwable mThrowable) throws Exception {
//                        Log.e(TAG, "onErrorReturn: ");
//                        return mThrowable.hashCode();
//                    }
//                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "接收到了订阅事件");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件：" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

    }
}
