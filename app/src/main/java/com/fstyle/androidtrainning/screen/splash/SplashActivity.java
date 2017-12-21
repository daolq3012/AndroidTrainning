package com.fstyle.androidtrainning.screen.splash;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.screen.main.MainActivity;

/**
 * Splash Screen.
 */
public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";
    private Thread mSplashThread;
    private LinearLayout mLinearLayout;
    private ImageView mImageView;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();
        startAnimations();
    }

    private void initViews() {
        mLinearLayout = findViewById(R.id.linear_layout);
        mImageView = findViewById(R.id.image_splash);
    }

    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        mLinearLayout.clearAnimation();
        mLinearLayout.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        mImageView.clearAnimation();
        mImageView.startAnimation(anim);

        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 2500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    Log.e(TAG, "run: ", e);
                } finally {
                    SplashActivity.this.finish();
                }
            }
        };
        mSplashThread.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
