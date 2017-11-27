package com.fstyle.androidtrainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtNumber1, mEdtNumber2;
    private TextView mTxtResult;
    private Button mBtnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        mBtnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number1 = Integer.parseInt(mEdtNumber1.getText().toString());
                int number2 = Integer.parseInt(mEdtNumber2.getText().toString());
                int result = number1 - number2;
                mTxtResult.setText(String.valueOf(result));
            }
        });
    }

    private void initViews() {
        mEdtNumber1 = findViewById(R.id.edtNumber1);
        mEdtNumber2 = findViewById(R.id.edtNumber2);
        mBtnCalculate = findViewById(R.id.btnCalculate);
        mTxtResult = findViewById(R.id.txtResult);
    }
}
