package com.example.faixaetaria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    TextView tvFaixaEtaria;
    EditText edtIdade;
    Button btnVerificarFaixaEtaria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvFaixaEtaria = (TextView) findViewById(R.id.lblFaixaEtaria);
        edtIdade = (EditText) findViewById(R.id.txtIdade);
        btnVerificarFaixaEtaria = (Button) findViewById(R.id.cmdVerificarFaixaEtaria);

        btnVerificarFaixaEtaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idade = Integer.parseInt(edtIdade.getText().toString());
                String faixaEtaria;

                if(idade > 0 && idade <= 12) {
                    faixaEtaria = "Essa infeliz é uma criança!";
                }

                else if (idade > 12 && idade <= 17) {
                    faixaEtaria = "Esse aurudo é adolescente";
                }

                else if (idade == 67) {
                    faixaEtaria = "Six Seven";
                }

                else {
                    faixaEtaria = "Esse sigma é um adulto";
                }

                tvFaixaEtaria.setText(faixaEtaria);
            }
        });
    }
}
