package com.example.ratingbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    RatingBar   ratingAvaliacao;
    TextView    tvAvaliacao;
    Button      btnEnviarAvaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvAvaliacao         = (TextView)    findViewById(R.id.ldlAvaliacao);
        ratingAvaliacao     = (RatingBar)   findViewById(R.id.rbAvaliacao);
        btnEnviarAvaliacao  = (Button)      findViewById(R.id.cmdAvaliacao);

        btnEnviarAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String avaliacao;
                avaliacao = String.valueOf(ratingAvaliacao.getRating());
                tvAvaliacao.setText("A sua avaliação foi " + avaliacao);
            }
        });
    }
}