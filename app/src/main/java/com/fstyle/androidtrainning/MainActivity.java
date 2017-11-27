package com.fstyle.androidtrainning;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView txtViewPhepTru;
    private EditText edtSo1, edtSo2;
    private Button btnTru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnTru.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int sothu1 = Integer.parseInt(edtSo1.getText().toString().trim());
                int sothu2 = Integer.parseInt(edtSo2.getText().toString().trim());
                int ketquaTru = sothu1 - sothu2;

                txtViewPhepTru.setText(" " + ketquaTru);
            }
        });
    }

    private void initViews() {
        txtViewPhepTru = findViewById(R.id.txtViewPhepTru);
        edtSo1 = findViewById(R.id.edtSo1);
        edtSo2 = findViewById(R.id.edtSo2);
        btnTru = findViewById(R.id.btnPhepTru);
    }
}
