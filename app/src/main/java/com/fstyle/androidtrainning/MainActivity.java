package com.fstyle.androidtrainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("phong","conflict");
        //TODO merge
        Toast.makeText(this, "Test Merge", Toast.LENGTH_SHORT).show();
    }
}
