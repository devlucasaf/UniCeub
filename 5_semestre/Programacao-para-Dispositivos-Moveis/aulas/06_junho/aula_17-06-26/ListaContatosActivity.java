package com.example.contatosdb;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contatosdb.adapter.AdapterContato;
import com.example.contatosdb.model.Contato;
import com.example.contatosdb.persistencia.ContatoDAO;

import java.util.ArrayList;
import java.util.List;

public class ListaContatosActivity extends AppCompatActivity {

    Button                  btnNovo;
    RecyclerView            recyclerContato;
    private AdapterContato  contatoAdapter;
    private Contato         contatoSelecionado;
    private List<Contato>   contatoLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_contatos);

        recyclerContato = (RecyclerView)    findViewById(R.id.rvListaContatos);
        btnNovo         = (Button)          findViewById(R.id.btnNovo);
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaContatosActivity.this, AddContatoActivity.class);
                startActivity(intent);
            }
        });

        recyclerContato.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerContato, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onItemClick(View view, int position) {
                contatoSelecionado = contatoLista.get(position);
                Intent intent = new Intent(ListaContatosActivity.this, AddContatoActivity.class);
                intent.putExtra("contatosSelecionado", contatoSelecionado);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar o contato que será excluído
                contatoSelecionado = contatoLista.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListaContatosActivity.this);
                //configurar o AlertDialog.Builder
                dialog.setTitle("Confirmar a exclusão");
                dialog.setMessage("Deseja excluir o contato:" + contatoSelecionado.getNomeContato() + "?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
                        if (contatoDAO.excluir(contatoSelecionado)) {
                            carregarListaContatos();
                            Toast.makeText(ListaContatosActivity.this, "Sucesso ao excluir o registro", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ListaContatosActivity.this, "Erro ao excluir o registro", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.setNegativeButton("Não",null);
                dialog.create();
                dialog.show();
            }
        }));
    }

    public void carregarListaContatos() {
        ContatoDAO contatoDAO = new ContatoDAO(getApplicationContext());
        contatoLista = contatoDAO.listar();
        //Configurar o meu adapter
        contatoAdapter = new AdapterContato(contatoLista);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerContato.setLayoutManager(layoutManager);
        recyclerContato.setHasFixedSize(true);
        recyclerContato.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerContato.setAdapter(contatoAdapter);
    }

    @Override
    protected void onStart() {
        carregarListaContatos();
        super.onStart();
    }
}
