package com.fstyle.androidtrainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnMulti;
    private EditText mEdtNumber1, mEdtNumber2;
    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        mBtnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float numb1 = Float.parseFloat(mEdtNumber1.getText().toString());
                float numb2 = Float.parseFloat(mEdtNumber2.getText().toString());
                float result = numb1 * numb2;
                mTxtResult.setText("Result:   " + result);
            }
        });
    }

    private void initViews() {
        mBtnMulti = findViewById(R.id.multiButton);
        mEdtNumber1 = findViewById(R.id.number1Edittext);
        mEdtNumber2 = findViewById(R.id.number2Edittext);
        mTxtResult = findViewById(R.id.resultTextView);
    }
}
