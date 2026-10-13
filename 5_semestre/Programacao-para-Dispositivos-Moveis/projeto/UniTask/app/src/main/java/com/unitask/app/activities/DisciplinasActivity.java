package com.unitask.app.activities;

import com.unitask.app.R;
import com.unitask.app.adapters.DisciplinaAdapter;
import com.unitask.app.database.AppDatabase;
import com.unitask.app.models.Disciplina;
import com.unitask.app.utils.SessionManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DisciplinasActivity extends AppCompatActivity {

    private RecyclerView            rvDisciplinas;
    private FloatingActionButton    fabNovaDisciplina;
    private TextView                tvSemDisciplinas;
    private AppDatabase             database;
    private SessionManager          sessionManager;
    private DisciplinaAdapter       adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplinas);

        database = AppDatabase.getInstance(this);
        sessionManager = new SessionManager(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.title_disciplinas));
        }

        initViews();
        setupRecyclerView();
        setupListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarDisciplinas();
    }

    private void initViews() {
        rvDisciplinas = findViewById(R.id.rvDisciplinas);
        fabNovaDisciplina = findViewById(R.id.fabNovaDisciplina);
        tvSemDisciplinas = findViewById(R.id.tvSemDisciplinas);
    }

    private void setupRecyclerView() {
        adapter = new DisciplinaAdapter(this, null,
                disciplina -> {
                    Intent intent = new Intent(this, DisciplinaFormActivity.class);
                    intent.putExtra(DisciplinaFormActivity.EXTRA_DISCIPLINA_ID, disciplina.getId());
                    startActivity(intent);
                },
                disciplina -> confirmarExclusao(disciplina));

        rvDisciplinas.setLayoutManager(new LinearLayoutManager(this));
        rvDisciplinas.setAdapter(adapter);
    }

    private void setupListeners() {
        fabNovaDisciplina.setOnClickListener(v -> {
            startActivity(new Intent(this, DisciplinaFormActivity.class));
        });
    }

    private void carregarDisciplinas() {
        List<Disciplina> disciplinas = database.disciplinaDao().listarPorUsuario(sessionManager.getUserId());
        adapter.atualizarLista(disciplinas);

        if (disciplinas.isEmpty()) {
            tvSemDisciplinas.setVisibility(View.VISIBLE);
            rvDisciplinas.setVisibility(View.GONE);
        } else {
            tvSemDisciplinas.setVisibility(View.GONE);
            rvDisciplinas.setVisibility(View.VISIBLE);
        }
    }

    private void confirmarExclusao(Disciplina disciplina) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_excluir_disciplina))
                .setMessage(getString(R.string.msg_excluir_disciplina, disciplina.getNome()))
                .setPositiveButton(getString(R.string.btn_excluir), (dialog, which) -> {
                    database.disciplinaDao().deletar(disciplina);
                    Toast.makeText(this, getString(R.string.sucesso_disciplina_excluida), Toast.LENGTH_SHORT).show();
                    carregarDisciplinas();
                })
                .setNegativeButton(getString(R.string.btn_cancelar), null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
