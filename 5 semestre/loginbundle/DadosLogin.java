package com.example.loginbundle;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginbundle.model.Usuario;

public class DadosLogin extends AppCompatActivity {
    TextView tvLogin;
    TextView tvSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dados_login);

        tvLogin = (TextView) findViewById(R.id.lblLogin);
        tvSenha = (TextView) findViewById(R.id.lblSenha);

        Bundle dados = getIntent().getExtras();
        Usuario usuario = (Usuario) dados.getSerializable("meuObjeto");
        tvLogin.setText(usuario.getEmail());
        tvSenha.setText(usuario.getSenha());
    }
}
