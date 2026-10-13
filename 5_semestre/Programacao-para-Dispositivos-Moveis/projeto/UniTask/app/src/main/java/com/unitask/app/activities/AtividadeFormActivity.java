package com.unitask.app.activities;

import com.unitask.app.R;
import com.unitask.app.database.AppDatabase;
import com.unitask.app.models.Atividade;
import com.unitask.app.models.Disciplina;
import com.unitask.app.utils.DateUtils;
import com.unitask.app.utils.NotificationHelper;
import com.unitask.app.utils.SessionManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class AtividadeFormActivity extends AppCompatActivity {

    public static final String EXTRA_ATIVIDADE_ID = "atividade_id";
    public static final String EXTRA_DISCIPLINA_ID = "disciplina_id";

    private TextInputLayout         tilTitulo;
    private TextInputLayout         tilDescricao;
    private TextInputLayout         tilDataEntrega;
    private TextInputEditText       etTitulo;
    private TextInputEditText       etDescricao;
    private TextInputEditText       etDataEntrega;
    private AutoCompleteTextView    spinnerDisciplina;
    private AutoCompleteTextView    spinnerTipo;
    private AutoCompleteTextView    spinnerPrioridade;
    private AutoCompleteTextView    spinnerSituacao;
    private MaterialButton          btnSalvar;
    private AppDatabase             db;
    private SessionManager          sessionManager;
    private int                     atividadeId = -1;
    private Atividade               atividadeEditando;
    private List<Disciplina>        disciplinas;
    private Calendar                calendarioEntrega = Calendar.getInstance();

    private final String[] TIPOS = {
            "TRABALHO",
            "PROVA",
            "EXERCICIO",
            "SEMINARIO",
            "PROJETO"
    };

    private final String[] PRIORIDADES = {
            "BAIXA",
            "MEDIA",
            "ALTA"
    };

    private final String[] SITUACOES = {
            "PENDENTE",
            "CONCLUIDA"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_form);

        db = AppDatabase.getInstance(this);
        sessionManager = new SessionManager(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        atividadeId = getIntent().getIntExtra(EXTRA_ATIVIDADE_ID, -1);

        initViews();
        setupSpinners();
        setupListeners();

        if (atividadeId != -1) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(getString(R.string.title_editar_atividade));
            }
            carregarAtividade();
        } else {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(getString(R.string.title_nova_atividade));
            }

            int disciplinaIdExtra = getIntent().getIntExtra(EXTRA_DISCIPLINA_ID, -1);
            if (disciplinaIdExtra != -1) {
                preselecionarDisciplina(disciplinaIdExtra);
            }
        }
    }

    private void initViews() {
        tilTitulo = findViewById(R.id.tilTitulo);
        tilDescricao = findViewById(R.id.tilDescricao);
        tilDataEntrega = findViewById(R.id.tilDataEntrega);
        etTitulo = findViewById(R.id.etTitulo);
        etDescricao = findViewById(R.id.etDescricao);
        etDataEntrega = findViewById(R.id.etDataEntrega);
        spinnerDisciplina = findViewById(R.id.spinnerDisciplina);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        spinnerPrioridade = findViewById(R.id.spinnerPrioridade);
        spinnerSituacao = findViewById(R.id.spinnerSituacao);
        btnSalvar = findViewById(R.id.btnSalvar);
    }

    private void setupSpinners() {
        disciplinas = db.disciplinaDao().listarPorUsuario(sessionManager.getUserId());
        String[] nomesDisciplinas = new String[disciplinas.size()];
        for (int i = 0; i < disciplinas.size(); i++) {
            nomesDisciplinas[i] = disciplinas.get(i).getNome();
        }
        spinnerDisciplina.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, nomesDisciplinas));

        String[] tiposExibicao = {
            getString(R.string.tipo_trabalho),
            getString(R.string.tipo_prova),
            getString(R.string.tipo_exercicio),
            getString(R.string.tipo_seminario),
            getString(R.string.tipo_projeto)
        };
        spinnerTipo.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, tiposExibicao));

        String[] prioridadesExibicao = {
            getString(R.string.prioridade_baixa),
            getString(R.string.prioridade_media),
            getString(R.string.prioridade_alta)
        };
        spinnerPrioridade.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, prioridadesExibicao));

        String[] situacoesExibicao = {
            getString(R.string.situacao_pendente),
            getString(R.string.situacao_concluida)
        };
        spinnerSituacao.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, situacoesExibicao));

        spinnerTipo.setText(tiposExibicao[0], false);
        spinnerPrioridade.setText(prioridadesExibicao[0], false);
        spinnerSituacao.setText(situacoesExibicao[0], false);
    }

    private void setupListeners() {
        etDataEntrega.setOnClickListener(v -> mostrarDateTimePicker());
        tilDataEntrega.setEndIconOnClickListener(v -> mostrarDateTimePicker());

        btnSalvar.setOnClickListener(v -> salvarAtividade());
    }

    private void mostrarDateTimePicker() {
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendarioEntrega.set(Calendar.YEAR, year);
            calendarioEntrega.set(Calendar.MONTH, month);
            calendarioEntrega.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            new TimePickerDialog(this, (view2, hourOfDay, minute) -> {
                calendarioEntrega.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendarioEntrega.set(Calendar.MINUTE, minute);
                calendarioEntrega.set(Calendar.SECOND, 0);

                etDataEntrega.setText(DateUtils.formatarDataHora(calendarioEntrega.getTimeInMillis()));
            }, calendarioEntrega.get(Calendar.HOUR_OF_DAY), calendarioEntrega.get(Calendar.MINUTE), true).show();

        }, calendarioEntrega.get(Calendar.YEAR),
           calendarioEntrega.get(Calendar.MONTH),
           calendarioEntrega.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void preselecionarDisciplina(int disciplinaId) {
        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getId() == disciplinaId) {
                spinnerDisciplina.setText(disciplinas.get(i).getNome(), false);
                break;
            }
        }
    }

    private void carregarAtividade() {
        atividadeEditando = db.atividadeDao().buscarPorId(atividadeId);
        if (atividadeEditando == null) {
            Toast.makeText(this, getString(R.string.erro_atividade_nao_encontrada), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        etTitulo.setText(atividadeEditando.getTitulo());
        etDescricao.setText(atividadeEditando.getDescricao());
        calendarioEntrega.setTimeInMillis(atividadeEditando.getDataEntrega());
        etDataEntrega.setText(DateUtils.formatarDataHora(atividadeEditando.getDataEntrega()));

        preselecionarDisciplina(atividadeEditando.getDisciplinaId());

        String[] tiposExibicao = {
            getString(R.string.tipo_trabalho),
            getString(R.string.tipo_prova),
            getString(R.string.tipo_exercicio),
            getString(R.string.tipo_seminario),
            getString(R.string.tipo_projeto)
        };
        for (int i = 0; i < TIPOS.length; i++) {
            if (TIPOS[i].equals(atividadeEditando.getTipo())) {
                spinnerTipo.setText(tiposExibicao[i], false);
                break;
            }
        }

        String[] prioridadesExibicao = {
            getString(R.string.prioridade_baixa),
            getString(R.string.prioridade_media),
            getString(R.string.prioridade_alta)
        };
        for (int i = 0; i < PRIORIDADES.length; i++) {
            if (PRIORIDADES[i].equals(atividadeEditando.getPrioridade())) {
                spinnerPrioridade.setText(prioridadesExibicao[i], false);
                break;
            }
        }

        String[] situacoesExibicao = {
            getString(R.string.situacao_pendente),
            getString(R.string.situacao_concluida)
        };
        for (int i = 0; i < SITUACOES.length; i++) {
            if (SITUACOES[i].equals(atividadeEditando.getSituacao())) {
                spinnerSituacao.setText(situacoesExibicao[i], false);
                break;
            }
        }
    }

    private void salvarAtividade() {
        String titulo = etTitulo.getText() != null ? etTitulo.getText().toString().trim() : "";
        String descricao = etDescricao.getText() != null ? etDescricao.getText().toString().trim() : "";
        String dataStr = etDataEntrega.getText() != null ? etDataEntrega.getText().toString().trim() : "";
        String disciplinaSelecionada = spinnerDisciplina.getText().toString().trim();
        String tipoSelecionado = spinnerTipo.getText().toString().trim();
        String prioridadeSelecionada = spinnerPrioridade.getText().toString().trim();
        String situacaoSelecionada = spinnerSituacao.getText().toString().trim();

        tilTitulo.setError(null);
        boolean valido = true;

        if (TextUtils.isEmpty(titulo)) {
            tilTitulo.setError(getString(R.string.erro_campo_obrigatorio));
            valido = false;
        }

        if (TextUtils.isEmpty(dataStr)) {
            tilDataEntrega.setError(getString(R.string.erro_campo_obrigatorio));
            valido = false;
        }

        if (TextUtils.isEmpty(disciplinaSelecionada) || disciplinas.isEmpty()) {
            Toast.makeText(this, getString(R.string.erro_selecione_disciplina), Toast.LENGTH_SHORT).show();
            valido = false;
        }

        if (!valido) {
            return;
        }

        int disciplinaId = -1;
        for (Disciplina d : disciplinas) {
            if (d.getNome().equals(disciplinaSelecionada)) {
                disciplinaId = d.getId();
                break;
            }
        }

        if (disciplinaId == -1) {
            Toast.makeText(this, getString(R.string.erro_selecione_disciplina), Toast.LENGTH_SHORT).show();
            return;
        }

        String[] tiposExibicao = {
            getString(R.string.tipo_trabalho),
            getString(R.string.tipo_prova),
            getString(R.string.tipo_exercicio),
            getString(R.string.tipo_seminario),
            getString(R.string.tipo_projeto)
        };
        
        String tipo = TIPOS[0];
        for (int i = 0; i < tiposExibicao.length; i++) {
            if (tiposExibicao[i].equals(tipoSelecionado)) {
                tipo = TIPOS[i];
                break;
            }
        }

        String[] prioridadesExibicao = {
            getString(R.string.prioridade_baixa),
            getString(R.string.prioridade_media),
            getString(R.string.prioridade_alta)
        };

        String prioridade = PRIORIDADES[0];
        for (int i = 0; i < prioridadesExibicao.length; i++) {
            if (prioridadesExibicao[i].equals(prioridadeSelecionada)) {
                prioridade = PRIORIDADES[i];
                break;
            }
        }

        // Resolver situação
        String[] situacoesExibicao = {
            getString(R.string.situacao_pendente),
            getString(R.string.situacao_concluida)
        };
        String situacao = SITUACOES[0];
        for (int i = 0; i < situacoesExibicao.length; i++) {
            if (situacoesExibicao[i].equals(situacaoSelecionada)) {
                situacao = SITUACOES[i];
                break;
            }
        }

        long dataEntrega = calendarioEntrega.getTimeInMillis();

        if (atividadeEditando != null) {
            atividadeEditando.setTitulo(titulo);
            atividadeEditando.setDescricao(descricao);
            atividadeEditando.setDisciplinaId(disciplinaId);
            atividadeEditando.setTipo(tipo);
            atividadeEditando.setPrioridade(prioridade);
            atividadeEditando.setSituacao(situacao);
            atividadeEditando.setDataEntrega(dataEntrega);
            atividadeEditando.setNotificacaoEnviada(false);
            db.atividadeDao().atualizar(atividadeEditando);

            NotificationHelper.cancelarNotificacoes(this, atividadeEditando.getId());
            if ("PENDENTE".equals(situacao)) {
                NotificationHelper.agendarNotificacoes(this, atividadeEditando);
            }

            Toast.makeText(this, getString(R.string.sucesso_atividade_atualizada), Toast.LENGTH_SHORT).show();
        } else {
            Atividade novaAtividade = new Atividade(
                    sessionManager.getUserId(), disciplinaId, titulo, descricao,
                    tipo, prioridade, situacao, dataEntrega
            );
            long id = db.atividadeDao().inserir(novaAtividade);
            novaAtividade.setId((int) id);

            if ("PENDENTE".equals(situacao)) {
                NotificationHelper.agendarNotificacoes(this, novaAtividade);
            }

            Toast.makeText(this, getString(R.string.sucesso_atividade_criada), Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
