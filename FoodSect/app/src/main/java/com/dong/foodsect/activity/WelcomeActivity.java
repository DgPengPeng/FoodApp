package com.dong.foodsect.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import com.dong.foodsect.R;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 16/11/25.
 *
 * 这是欢迎页
 */
public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView welcomeIv;
    private CountDownTimer countDownTimer;
    @Override
    int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    void initView() {
        welcomeIv = (ImageView) findViewById(R.id.iv_welcome);
        welcomeIv.setOnClickListener(this);
        // 三方 分享
        ShareSDK.initSDK(this);

    }

    @Override
    void initData() {
        CountDownTimer();
        countDownTimer.start();

    }

    /**
     * 欢迎页 倒计时
     *
     */
    private void CountDownTimer() {
        countDownTimer = new CountDownTimer(6000,1000) {
            int a = 6;
            @Override
            public void onTick(long l) {
                --a;
                if (a == 1){
                    onFinish();
                }
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                countDownTimer.cancel();
                finish();
            }
        };
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        countDownTimer.cancel();
        finish();
    }
}
