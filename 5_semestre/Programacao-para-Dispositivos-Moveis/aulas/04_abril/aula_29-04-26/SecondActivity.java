package com.example.componentes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.componentes.model.Usuario;

public class SecondActivity extends AppCompatActivity {

    TextView tvNome;
    TextView tvAreaAtuacao;
    TextView tvSexo;
    TextView tvNotificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        tvNome          = (TextView) findViewById(R.id.lblNome);
        tvAreaAtuacao   = (TextView) findViewById(R.id.lblAreaAtuacao);
        tvSexo          = (TextView) findViewById(R.id.lblSexo);
        tvNotificacao   = (TextView) findViewById(R.id.lblNotificacao);

        Bundle dados = getIntent().getExtras();
        Usuario usuario = (Usuario) dados.getSerializable("objeto");

        tvNome.setText(usuario.getNome());
        tvAreaAtuacao.setText(usuario.getAreaAtuacao());
        tvSexo.setText(usuario.getSexo());
        tvNotificacao.setText(usuario.getNotificacao());

    }
}
