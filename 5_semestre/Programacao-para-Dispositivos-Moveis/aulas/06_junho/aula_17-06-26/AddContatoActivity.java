package com.example.contatosdb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contatosdb.model.Contato;
import com.example.contatosdb.persistencia.ContatoDAO;

public class AddContatoActivity extends AppCompatActivity {

    EditText        edtNome;
    EditText        edtTelefone;
    Button          btnSalvar;
    private Contato contatoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contato);

        edtNome         = (EditText) findViewById(R.id.textViewNome);
        edtTelefone     = (EditText) findViewById(R.id.textViewTelefone);
        btnSalvar       = (Button) findViewById(R.id.btnSalvar);
        contatoAtual    = (Contato) getIntent().getSerializableExtra("contatosSelecionado");

        if (contatoAtual != null) {
            edtNome.setText(contatoAtual.getNomeContato());
            edtTelefone.setText(contatoAtual.getTelefoneContato());
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
                //Se for diferente de nulo, existe registro e eu vou querer alterar
                String nomeContato = edtNome.getText().toString();
                String telefoneContato = edtTelefone.getText().toString();

                if (contatoAtual != null) {
                    if (!nomeContato.isEmpty()) {
                        Contato contato = new Contato();
                        contato.setNomeContato(nomeContato);
                        contato.setTelefoneContato(telefoneContato);
                        contato.setId(contatoAtual.getId());
                        if (contatoDAO.atualizar(contato)) {
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao atualizar o registro",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Erro ao atualizar o registro",Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    //Criar um novo registro -insert
                    if (!nomeContato.isEmpty()) {
                        Contato contato = new Contato();
                        contato.setNomeContato(nomeContato);
                        contato.setTelefoneContato(telefoneContato);

                        if (contatoDAO.salvar(contato)) {
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao inserir o registro",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Erro ao inserir o registro",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
}