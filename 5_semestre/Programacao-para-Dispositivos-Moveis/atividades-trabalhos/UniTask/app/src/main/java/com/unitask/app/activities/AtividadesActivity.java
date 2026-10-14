package com.unitask.app.activities;

import com.unitask.app.R;
import com.unitask.app.adapters.AtividadeComDisciplinaAdapter;
import com.unitask.app.database.AppDatabase;
import com.unitask.app.models.AtividadeComDisciplina;
import com.unitask.app.models.Disciplina;
import com.unitask.app.utils.NotificationHelper;
import com.unitask.app.utils.SessionManager;
import com.unitask.app.models.Atividade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AtividadesActivity extends AppCompatActivity {

    private RecyclerView                    rvAtividades;
    private FloatingActionButton            fabNovaAtividade;
    private TextView                        tvSemAtividades;
    private ChipGroup                       chipGroupFiltros;
    private AutoCompleteTextView            spinnerFiltroDisc;
    private AutoCompleteTextView            spinnerFiltroPrior;
    private AutoCompleteTextView            spinnerFiltroSit;
    private AppDatabase                     database;
    private SessionManager                  sessionManager;
    private AtividadeComDisciplinaAdapter   adapter;
    private List<Disciplina>                disciplinas;
    private int                             filtroDiscId = 0;
    private String                          filtroPrioridade = "";
    private String                          filtroSituacao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);

        database = AppDatabase.getInstance(this);
        sessionManager = new SessionManager(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.title_atividades));
        }

        initViews();
        setupFiltros();
        setupRecyclerView();
        setupListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarAtividades();
    }

    private void initViews() {
        rvAtividades = findViewById(R.id.rvAtividades);
        fabNovaAtividade = findViewById(R.id.fabNovaAtividade);
        tvSemAtividades = findViewById(R.id.tvSemAtividades);
        spinnerFiltroDisc = findViewById(R.id.spinnerFiltroDisc);
        spinnerFiltroPrior = findViewById(R.id.spinnerFiltroPrior);
        spinnerFiltroSit = findViewById(R.id.spinnerFiltroSit);
    }

    private void setupFiltros() {
        disciplinas = database.disciplinaDao().listarPorUsuario(sessionManager.getUserId());
        List<String> nomesDisciplinas = new ArrayList<>();
        nomesDisciplinas.add(getString(R.string.filtro_todas));
        for (Disciplina d : disciplinas) {
            nomesDisciplinas.add(d.getNome());
        }
        spinnerFiltroDisc.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, nomesDisciplinas));
        spinnerFiltroDisc.setText(getString(R.string.filtro_todas), false);

        // Prioridades
        String[] prioridades = {getString(R.string.filtro_todas),
            getString(R.string.prioridade_baixa),
            getString(R.string.prioridade_media),
            getString(R.string.prioridade_alta)};
        spinnerFiltroPrior.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, prioridades));
        spinnerFiltroPrior.setText(prioridades[0], false);

        // Situações
        String[] situacoes = {
            getString(R.string.filtro_todas),
            getString(R.string.situacao_pendente),
            getString(R.string.situacao_concluida)
        };
        spinnerFiltroSit.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, situacoes));
        spinnerFiltroSit.setText(situacoes[0], false);

        spinnerFiltroDisc.setOnItemClickListener((parent, view, position, id) -> {
            filtroDiscId = position == 0 ? 0 : disciplinas.get(position - 1).getId();
            carregarAtividades();
        });

        spinnerFiltroPrior.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) {
                filtroPrioridade = "";
            } else if (position == 1) {
                filtroPrioridade = "BAIXA";
            } else if (position == 2) {
                filtroPrioridade = "MEDIA";
            } else {
                filtroPrioridade = "ALTA";
            }
            carregarAtividades();
        });

        spinnerFiltroSit.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) {
                filtroSituacao = "";
            } else if (position == 1) {
                filtroSituacao = "PENDENTE";
            } else {
                filtroSituacao = "CONCLUIDA";
            }
            carregarAtividades();
        });
    }

    private void setupRecyclerView() {
        adapter = new AtividadeComDisciplinaAdapter(this, null, atividade -> {
            Intent intent = new Intent(this, AtividadeFormActivity.class);
            intent.putExtra(AtividadeFormActivity.EXTRA_ATIVIDADE_ID, atividade.getId());
            startActivity(intent);
        });
        adapter.setOnLongClickListener(atividade -> confirmarExclusao(atividade));
        rvAtividades.setLayoutManager(new LinearLayoutManager(this));
        rvAtividades.setAdapter(adapter);
    }

    private void setupListeners() {
        fabNovaAtividade.setOnClickListener(v -> {
            startActivity(new Intent(this, AtividadeFormActivity.class));
        });
    }

    private void carregarAtividades() {
        int usuarioId = sessionManager.getUserId();
        List<AtividadeComDisciplina> atividades = database.atividadeDao()
                .filtrarCombinado(usuarioId, filtroDiscId, filtroPrioridade, filtroSituacao);

        adapter.atualizarLista(atividades);

        if (atividades.isEmpty()) {
            tvSemAtividades.setVisibility(View.VISIBLE);
            rvAtividades.setVisibility(View.GONE);
        } else {
            tvSemAtividades.setVisibility(View.GONE);
            rvAtividades.setVisibility(View.VISIBLE);
        }
    }

    private void confirmarExclusao(AtividadeComDisciplina atividadeComDisciplina) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_excluir_atividade))
                .setMessage(getString(R.string.msg_excluir_atividade, atividadeComDisciplina.getTitulo()))
                .setPositiveButton(getString(R.string.btn_excluir), (dialog, which) -> {
                    Atividade atividade = new Atividade();

                    atividade.setId(atividadeComDisciplina.getId());
                    atividade.setUsuarioId(atividadeComDisciplina.getUsuarioId());
                    atividade.setDisciplinaId(atividadeComDisciplina.getDisciplinaId());
                    atividade.setTitulo(atividadeComDisciplina.getTitulo());
                    atividade.setDescricao(atividadeComDisciplina.getDescricao());
                    atividade.setTipo(atividadeComDisciplina.getTipo());
                    atividade.setPrioridade(atividadeComDisciplina.getPrioridade());
                    atividade.setSituacao(atividadeComDisciplina.getSituacao());
                    atividade.setDataEntrega(atividadeComDisciplina.getDataEntrega());

                    NotificationHelper.cancelarNotificacoes(this, atividadeComDisciplina.getId());
                    database.atividadeDao().deletar(atividade);
                    Toast.makeText(this, getString(R.string.sucesso_atividade_excluida), Toast.LENGTH_SHORT).show();
                    carregarAtividades();
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
