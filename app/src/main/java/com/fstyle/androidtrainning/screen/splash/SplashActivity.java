package com.fstyle.androidtrainning.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.screen.main.MainActivity;

public class SplashActivity extends BaseActivity {

    private RelativeLayout mRelativeLayout;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();
        doAnimation();
        doGoToMainActivity();
    }

    private void initViews() {
        mImageView = findViewById(R.id.image_logo);
        mRelativeLayout = findViewById(R.id.parentLayout);
    }

    private void doAnimation() {
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        Animation animMove = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        mRelativeLayout.setAnimation(animFadeIn);
        mImageView.setAnimation(animMove);
    }

    private void doGoToMainActivity() {
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };
        Handler mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 3000);
    }
}
