package com.example.mylove;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Part2aActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2a);

        Button option1 = findViewById(R.id.button_option_1);
        Button option2 = findViewById(R.id.button_option_2);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a próxima atividade (por exemplo, Part3Activity) quando a opção 1 for selecionada
                Intent intent = new Intent(Part2aActivity.this, Part3Activity.class);
                startActivity(intent);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a próxima atividade (por exemplo, Part3Activity)
                // quando a opção 2 for selecionada
                Intent intent = new Intent(Part2aActivity.this, Part3Activity.class);
                startActivity(intent);
            }
        });
    }
}