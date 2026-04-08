package com.exemplo.todolistjava;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTarefa;
    private Button buttonAdicionar;
    private ListView listViewTarefas;
    private ArrayList<String> listaTarefas;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTarefa = findViewById(R.id.editTextTarefa);
        buttonAdicionar = findViewById(R.id.buttonAdicionar);
        listViewTarefas = findViewById(R.id.listViewTarefas);

        listaTarefas = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listaTarefas);
        listViewTarefas.setAdapter(adapter);

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novaTarefa = editTextTarefa.getText().toString().trim();
                if (!novaTarefa.isEmpty()) {
                    listaTarefas.add(novaTarefa);
                    adapter.notifyDataSetChanged(); 
                    editTextTarefa.setText(""); 
                    Toast.makeText(MainActivity.this,
                            "Tarefa adicionada!", Toast.LENGTH_SHORT).show();
                } 
                
                else {
                    Toast.makeText(MainActivity.this,
                            "Digite uma tarefa válida", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listViewTarefas.setOnItemClickListener((parent, view, position, id) -> {
            String tarefaRemovida = listaTarefas.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this,
                    "Removido: " + tarefaRemovida, Toast.LENGTH_SHORT).show();
        });
    }
}