package com.example.mylove;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Part3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part3);

        Button option1 = findViewById(R.id.button_option_1);
        Button option2 = findViewById(R.id.button_option_2);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a próxima atividade (por exemplo, Part4Activity) quando a opção 1 for selecionada
                Intent intent = new Intent(Part3Activity.this, Part4Activity.class);
                startActivity(intent);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a próxima atividade (por exemplo, Part4Activity)
                Intent intent = new Intent(Part3Activity.this, Part4Activity.class);
                startActivity(intent);
            }
        });
    }
}