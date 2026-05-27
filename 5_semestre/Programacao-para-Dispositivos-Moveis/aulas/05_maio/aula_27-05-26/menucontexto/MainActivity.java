package com.example.menucontexto;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ListView        listViewFilmes;
    private ArrayAdapter    arrayAdapter;
    private String          itemSelecionado;
    private String[]        filmes = {
            "Forest Gump", "Star Wars", "Gente Grande", "Casa Monstro",
            "Exterminador do futuro", "Kill Bill", "Carros", "Green Book", "Homem de Ferro",
            "O Poderoso Chefinho", "Detona Ralph", "Whiplash", "Barbie", "Aviões", "Ratatouille",
            "O Incrível Hulk", "O Resgate do Soldado Ryan", "Pelotão em Apuros", "Golpe Baixo",
            "Simpsons: O Filme", "Shrek", "A Creche do Papai", "Capitão América", "O Show de Truman"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listViewFilmes = (ListView) findViewById(R.id.lstFilmes);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                filmes
        );
        listViewFilmes.setAdapter(adapter);
        registerForContextMenu(listViewFilmes);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menu de contexto");
        menu.add(0, v.getId(), 0, "Upload");
        menu.add(0, v.getId(), 0, "Pesquisar");
        menu.add(0, v.getId(), 0, "Excluir");
        menu.add(0, v.getId(), 0, "Alterar");
    }
}