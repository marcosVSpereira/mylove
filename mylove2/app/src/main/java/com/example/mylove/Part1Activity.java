package com.example.mylove;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Part1Activity extends AppCompatActivity {
    private Button buttonOptionA;
    private Button buttonOptionB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);

        buttonOptionA = findViewById(R.id.button_option_a);
        buttonOptionB = findViewById(R.id.button_option_b);

        buttonOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part1Activity.this, Part2aActivity.class);
                startActivity(intent);
            }
        });

        buttonOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part1Activity.this, Part2bActivity.class);
                startActivity(intent);
            }
        });
    }
}