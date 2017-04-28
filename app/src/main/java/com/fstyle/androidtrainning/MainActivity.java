package com.fstyle.androidtrainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.fstyle.androidtrainning.widget.customView.TouchEventView;

public class MainActivity extends AppCompatActivity {

    private TouchEventView mTouchEventView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTouchEventView = (TouchEventView) findViewById(R.id.touchView);
    }

    public void onButtonEraseClicked(View view) {
        mTouchEventView.erase();
    }
}
