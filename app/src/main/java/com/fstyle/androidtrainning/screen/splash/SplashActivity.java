package com.fstyle.androidtrainning.screen.splash;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
    public static final int REQUEST_PERMISSION_CODE = 101;
    public static final int TIME_HANDLER = 3000;
    public static final int SDK_DEFAULT = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //permission
        requestStoragePermissions();
        initViews();
        doAnimation();
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
        mHandler.postDelayed(mRunnable, TIME_HANDLER);
    }

    private void requestStoragePermissions() {
        if (Build.VERSION.SDK_INT >= SDK_DEFAULT) {
            if (ActivityCompat.checkSelfPermission(SplashActivity.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SplashActivity.this, new String[] {
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, REQUEST_PERMISSION_CODE);
            } else {
                doGoToMainActivity();
            }
        } else {
            doGoToMainActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    finish();
                }
                break;
        }
    }
}
