package com.example.enviodedadosviabundle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.enviodedadosviabundle.model.Pessoa;

import org.w3c.dom.Text;

public class SegundaActivity extends AppCompatActivity {

    TextView txtNome;
    TextView txtIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);

        txtNome = (TextView)findViewById(R.id.lblNome);
        txtIdade = (TextView)findViewById(R.id.lblIdade);

        Bundle dados = getIntent().getExtras();
        //String nome = dados.getString("nome");
        //int idade = dados.getInt("idade");

        Pessoa pessoa = (Pessoa) dados.getSerializable("meuObjeto");

        //txtNome.setText(nome);
        //txtIdade.setText(String.valueOf(idade));

        txtNome.setText(pessoa.getNome());
        txtIdade.setText(String.valueOf(pessoa.getIdade()));
    }
}




