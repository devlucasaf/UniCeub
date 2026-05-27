package com.example.menuopcoes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuSalvar) {
            Toast.makeText(MainActivity.this, "Item de menu salvar.", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menuExcluir) {
            Toast.makeText(MainActivity.this, "Item de menu excluir.", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menuPesquisar) {
            Toast.makeText(MainActivity.this, "Item de menu pesquisar.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Item de menu sair.", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}