package com.example.classificacaoimc;

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

    TextView    tvClassificacaoIMC;
    TextView    tvImc;
    EditText    edtDoublePeso;
    EditText    edtDoubleAltura;
    Button      btnClassificarIMC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtDoublePeso = (EditText) findViewById(R.id.txtPeso);
        edtDoubleAltura = (EditText) findViewById(R.id.txtAltura);
        tvImc = (TextView) findViewById(R.id.lblIMC);
        tvClassificacaoIMC = (TextView) findViewById(R.id.lblClassificacaoIMC);
        btnClassificarIMC = (Button) findViewById(R.id.cmdClassificarIMC);

        btnClassificarIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mostrarIMC;

                double peso = Double.parseDouble(edtDoublePeso.getText().toString());
                double altura = Double.parseDouble(edtDoubleAltura.getText().toString());

                double imc = peso / (altura * altura);

                if (imc < 18.5) {
                    mostrarIMC = "Você está abaixo do peso!";
                }

                else if (imc > 18.5 && imc <= 24.9) {
                    mostrarIMC = "O seu peso está normal!";
                }

                else if (imc > 24.9 && imc <= 29.9) {
                    mostrarIMC = "Você está com sobrepeso!";
                }

                else if (imc > 29.9 && imc <= 34.9) {
                    mostrarIMC = "Você está obeso! Seu grau de obesidade é I";
                }

                else if (imc > 34.9 && imc <= 39.9) {
                    mostrarIMC = "Você está obeso! Seu grau de obesidade é II";
                }

                else {
                    mostrarIMC = "Você está obeso! Seu grau de obesidade é III";
                }

                tvImc.setText(String.valueOf(imc));
                tvClassificacaoIMC.setText(mostrarIMC);
            }
        });
    }
}
