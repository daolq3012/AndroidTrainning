package com.fstyle.androidtrainning.screen.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

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
        mImageView = findViewById(R.id.logoImageView);
        mRelativeLayout = findViewById(R.id.parentLayout);
    }

    private void doAnimation() {
        Animation animfadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        Animation animmove = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        mRelativeLayout.setAnimation(animfadein);
        mImageView.setAnimation(animmove);
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
