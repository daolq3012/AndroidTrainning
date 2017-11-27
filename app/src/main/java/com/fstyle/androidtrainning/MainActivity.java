package com.fstyle.androidtrainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView resultTextView;
    private EditText so1EditText, so2EditText;
    private Button resultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();
    }

    private void initViews() {
        resultTextView = findViewById(R.id.resultTextView);
        so1EditText = findViewById(R.id.so1EditText);
        so2EditText = findViewById(R.id.so2EditText);
        resultButton = findViewById(R.id.resultButton);
    }

    private void initEvents() {
        resultButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.resultButton:
                cong();
                break;
        }
    }

    private void cong() {
        int so1 = Integer.parseInt(so1EditText.getText().toString());
        int so2 = Integer.parseInt(so2EditText.getText().toString());


        resultTextView.setText(so1 + so2 + "");
    }
}
