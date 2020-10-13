package com.exoplayer.hilt;

import android.os.Bundle;

import com.exoplayer.hilt.bean.DogBean;
import com.exoplayer.hilt.bean.UserBean;
import com.exoplayer.hilt.binds.ChinaCar;
import com.exoplayer.hilt.qualifier.MadeInCN;
import com.exoplayer.hilt.qualifier.MadeInUSA;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author zhangshuai
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    UserBean mUserBean;
    @Inject
    ChinaCar mChinaCar;
    @Inject
    DogBean mDogBean;

    @Inject
    @MadeInCN
    ChinaCar mChinaCar1;
    @Inject
    @MadeInUSA
    ChinaCar mChinaCar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatTextView acTv = findViewById(R.id.acTv);
        mUserBean.setName("Android");
        mUserBean.setAge(2020);
        mChinaCar.setName("比亚迪");
        mChinaCar.getEngine()
                .on();
        mChinaCar.getEngine()
                .off();
        acTv.setOnClickListener(v -> {
            mUserBean.showToast();

            acTv.setText(mChinaCar.getName() + ", " + mDogBean.getName());

            mChinaCar1.getEngine()
                    .on();
            mChinaCar1.getEngine()
                    .off();

            mChinaCar2.getEngine()
                    .on();
            mChinaCar2.getEngine()
                    .off();
        });
    }
}
