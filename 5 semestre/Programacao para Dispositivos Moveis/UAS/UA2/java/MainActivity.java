package com.example.mediaapp; 

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editNota1, editNota2;
    private Button btnCalcularMedia;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNota1 = findViewById(R.id.editNota1);
        editNota2 = findViewById(R.id.editNota2);
        btnCalcularMedia = findViewById(R.id.btnCalcularMedia);
        txtResultado = findViewById(R.id.txtResultado);

        btnCalcularMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularMedia();
            }
        });
    }

    private void calcularMedia() {
        String n1Str = editNota1.getText().toString().trim();
        String n2Str = editNota2.getText().toString().trim();

        if (n1Str.isEmpty() || n2Str.isEmpty()) {
            Toast.makeText(this, "Preencha ambas as notas", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double n1 = Double.parseDouble(n1Str);
            double n2 = Double.parseDouble(n2Str);
            double media = (n1 + n2) / 2;

            txtResultado.setText(String.format("Média: %.2f", media));
            Toast.makeText(this, "Média calculada: " + String.format("%.2f", media), Toast.LENGTH_SHORT).show();
        } 
        
        catch (NumberFormatException e) {
            Toast.makeText(this, "Digite números válidos", Toast.LENGTH_SHORT).show();
        }
    }
}