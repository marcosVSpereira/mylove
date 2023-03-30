package com.example.mylove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Part5Activity extends AppCompatActivity {
    private Button buttonOptionA;
    private Button buttonOptionB;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5);

        buttonOptionA = findViewById(R.id.button_option_a);
        buttonOptionB = findViewById(R.id.button_option_b);
        tvResult = findViewById(R.id.tv_result);

        buttonOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("Você usa o tesouro para ajudar os necessitados e melhorar o mundo...");
            }
        });
        buttonOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("Você guarda o tesouro mágico apenas para si e começa a usá-lo para satisfazer seus desejos pessoais...");
            }
        });
    }
}