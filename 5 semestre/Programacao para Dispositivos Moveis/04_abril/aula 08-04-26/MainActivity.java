package com.example.enviodedadosviabundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.enviodedadosviabundle.model.Usuario;

public class MainActivity extends AppCompatActivity {

    Button btnEnviarDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnEnviarDados = (Button)findViewById((R.id.cmdEnviarDados));
        btnEnviarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);

                Pessoa pessoa = new Pessoa();

                pessoa.setNome("Leanderson da Silva");
                pessoa.setIdade(910);
                intent.putExtra("meuObjeto", pessoa);
                startActivity(intent);
            }
        });
    }
}