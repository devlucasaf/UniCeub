package com.example.loginbundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginbundle.model.Usuario;

public class MainActivity extends AppCompatActivity {
    Button btnEnviarDados;
    EditText edtEmail;
    EditText edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtEmail = (EditText) findViewById(R.id.txtEmail);
        edtSenha = (EditText) findViewById(R.id.txtSenha);
        btnEnviarDados = (Button) findViewById((R.id.cmdAutenticarDados));
        btnEnviarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DadosLogin.class);

                Usuario usuario = new Usuario();

                usuario.setEmail(edtEmail.getText().toString());
                usuario.setSenha(edtSenha.getText().toString());
                intent.putExtra("meuObjeto", usuario);
                startActivity(intent);
            }
        });
    }
}
