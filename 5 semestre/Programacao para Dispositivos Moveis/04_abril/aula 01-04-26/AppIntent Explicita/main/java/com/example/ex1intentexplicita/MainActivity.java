package com.example.ex1intentexplicita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnSecondActivity;
    Button btnThirdActivity;
    Button btnFourActivity;
    Button btnFiveActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnSecondActivity   = (Button) findViewById(R.id.cmdAbrir2Activity);
        btnThirdActivity    = (Button) findViewById(R.id.cmdAbrir3Activity);
        btnFourActivity     = (Button) findViewById(R.id.cmdAbrir4Activity);
        btnFiveActivity     = (Button) findViewById(R.id.cmdAbrir5Activity);

        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, SegundaActivity.class);
                startActivity(intencao);
            }
        });

        btn3Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, TerceiraActivity.class);
                startActivity(intencao);
            }
        });

        btn4Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, QuartaActivity.class);
                startActivity(intencao);
            }
        });

        btn5Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, QuintaActivity.class);
                startActivity(intencao);
            }
        });
    }
}