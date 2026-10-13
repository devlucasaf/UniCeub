package com.unitask.app.activities;

import com.unitask.app.R;
import com.unitask.app.adapters.AtividadeComDisciplinaAdapter;
import com.unitask.app.database.AppDatabase;
import com.unitask.app.models.AtividadeComDisciplina;
import com.unitask.app.utils.SessionManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView                        tvTotalAtividades;
    private TextView                        tvPendentes;
    private TextView                        tvConcluidas;
    private TextView                        tvAtrasadas;
    private TextView                        tvNomeUsuario;
    private RecyclerView                    rvProximasAtividades;
    private FloatingActionButton            fabNovaAtividade;
    private AppDatabase                     database;
    private SessionManager                  sessionManager;
    private AtividadeComDisciplinaAdapter   adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getInstance(this);
        sessionManager = new SessionManager(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        setupRecyclerView();
        setupListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarDados();
    }

    private void initViews() {
        tvTotalAtividades = findViewById(R.id.tvTotalAtividades);
        tvPendentes = findViewById(R.id.tvPendentes);
        tvConcluidas = findViewById(R.id.tvConcluidas);
        tvAtrasadas = findViewById(R.id.tvAtrasadas);
        tvNomeUsuario = findViewById(R.id.tvNomeUsuario);
        rvProximasAtividades = findViewById(R.id.rvProximasAtividades);
        fabNovaAtividade = findViewById(R.id.fabNovaAtividade);

        String nome = sessionManager.getUserName();
        String primeiroNome = nome.contains(" ") ? nome.substring(0, nome.indexOf(" ")) : nome;
        tvNomeUsuario.setText(getString(R.string.ola_usuario, primeiroNome));
    }

    private void setupRecyclerView() {
        adapter = new AtividadeComDisciplinaAdapter(this, null, atividade -> {
            Intent intent = new Intent(this, AtividadeFormActivity.class);
            intent.putExtra(AtividadeFormActivity.EXTRA_ATIVIDADE_ID, atividade.getId());
            startActivity(intent);
        });
        rvProximasAtividades.setLayoutManager(new LinearLayoutManager(this));
        rvProximasAtividades.setAdapter(adapter);
        rvProximasAtividades.setNestedScrollingEnabled(false);
    }

    private void setupListeners() {
        fabNovaAtividade.setOnClickListener(v -> {
            startActivity(new Intent(this, AtividadeFormActivity.class));
        });

        findViewById(R.id.cardDisciplinas).setOnClickListener(v -> {
            startActivity(new Intent(this, DisciplinasActivity.class));
        });

        findViewById(R.id.cardAtividades).setOnClickListener(v -> {
            startActivity(new Intent(this, AtividadesActivity.class));
        });
    }

    private void carregarDados() {
        int usuarioId = sessionManager.getUserId();
        long agora = System.currentTimeMillis();

        int total = database.atividadeDao().contarTotal(usuarioId);
        int pendentes = database.atividadeDao().contarPendentes(usuarioId);
        int concluidas = database.atividadeDao().contarConcluidas(usuarioId);
        int atrasadas = database.atividadeDao().contarAtrasadas(usuarioId, agora);

        tvTotalAtividades.setText(String.valueOf(total));
        tvPendentes.setText(String.valueOf(pendentes));
        tvConcluidas.setText(String.valueOf(concluidas));
        tvAtrasadas.setText(String.valueOf(atrasadas));

        List<AtividadeComDisciplina> proximas = database.atividadeDao().proximasAtividades(usuarioId, 5);
        adapter.atualizarLista(proximas);

        View tvSemAtividades = findViewById(R.id.tvSemAtividades);
        if (proximas.isEmpty()) {
            tvSemAtividades.setVisibility(View.VISIBLE);
            rvProximasAtividades.setVisibility(View.GONE);
        } else {
            tvSemAtividades.setVisibility(View.GONE);
            rvProximasAtividades.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            confirmarLogout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void confirmarLogout() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_logout))
                .setMessage(getString(R.string.msg_logout))
                .setPositiveButton(getString(R.string.btn_sair), (dialog, which) -> {
                    sessionManager.logout();
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton(getString(R.string.btn_cancelar), null)
                .show();
    }
}
