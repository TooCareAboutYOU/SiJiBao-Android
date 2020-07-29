package com.tuoniao.sijibao.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.widget.AppCompatTextView;
import com.tuoniao.sijibao.R;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * @author zhangshuai
 * 视频详情页 免费试听提示弹框
 */
public class FreeTrialDialogViews extends AppCompatTextView {

    private Context mContext;

    private Animation enterAnim, exitAnim;
    private Disposable mDisposable;
    private boolean isShowOne = false; //只显示一次

    public FreeTrialDialogViews(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public FreeTrialDialogViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public FreeTrialDialogViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    /**
     * 初始化绑定view
     */
    private void init() {
        enterAnim = AnimationUtils.loadAnimation(this.mContext, R.anim.anim_show);
        exitAnim = AnimationUtils.loadAnimation(this.mContext, R.anim.anim_hide);
        setBackgroundColor(Color.RED);
        setTextSize(10L);
        setTextColor(Color.WHITE);
        setGravity(Gravity.CENTER);
        setPadding(5,5,5,5);
        setHint("免费试看前0分0秒");
    }

    /**
     * 显示提示框
     * 倒计时5秒
     */
    @SuppressLint("CheckResult")
    public void show(String txt) {
        if (isShowOne) {
            return;
        }
        isShowOne = true;

        setText(txt);

        setVisibility(View.VISIBLE);

        this.startAnimation(enterAnim);
        mDisposable = Observable.interval(0, 1, TimeUnit.SECONDS).take(6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (aLong == 5) {
                            hide();
                        }
                    }
                });
    }

    /**
     * 隐藏提示框
     */
    private void hide() {
        this.startAnimation(exitAnim);
        exitAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isShowOne = false;
                setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    /**
     * 解绑
     */
    public void releaseView() {
        isShowOne = false;
        enterAnim = null;
        exitAnim = null;

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }


        if (this.mContext != null) {
            this.mContext = null;
        }
    }
}
