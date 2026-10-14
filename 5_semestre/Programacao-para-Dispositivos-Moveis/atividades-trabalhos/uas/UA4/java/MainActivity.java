package com.example.contadorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private int contador = 0;
    private TextView textViewContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewContador = findViewById(R.id.text_contador);
        FloatingActionButton fab = findViewById(R.id.fab_incrementar);

        atualizarTexto();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementar();
            }
        });
    }

    private void incrementar() {
        contador++;
        atualizarTexto();
    }

    private void atualizarTexto() {
        textViewContador.setText("Valor: " + contador);
    }
}