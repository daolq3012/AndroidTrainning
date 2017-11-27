package com.fstyle.androidtrainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtSo1, edtSo2, edtKetQua;
    private Button btCong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int so1 = Integer.parseInt(edtSo1.getText().toString());
                int so2 = Integer.parseInt(edtSo2.getText().toString());
                int tong = so1 + so2;
                edtKetQua.setText("" + tong);
            }
        });
    }

    private void initViews() {
        edtSo1 = findViewById(R.id.so1);
        edtSo2 = findViewById(R.id.so2);
        edtKetQua = findViewById(R.id.ketQua);
        btCong = findViewById(R.id.cong);
    }
}
