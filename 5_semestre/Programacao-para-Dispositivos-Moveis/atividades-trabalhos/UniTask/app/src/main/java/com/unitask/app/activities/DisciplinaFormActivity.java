package com.unitask.app.activities;

import com.unitask.app.R;
import com.unitask.app.database.AppDatabase;
import com.unitask.app.models.Disciplina;
import com.unitask.app.utils.SessionManager;
import com.unitask.app.utils.ValidationUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DisciplinaFormActivity extends AppCompatActivity {

    public static final String EXTRA_DISCIPLINA_ID = "disciplina_id";

    private TextInputLayout     tilNome;
    private TextInputLayout     tilProfessor;
    private TextInputLayout     tilCor;
    private TextInputLayout     tilPeriodo;
    private TextInputEditText   etNome;
    private TextInputEditText   etProfessor;
    private TextInputEditText   etPeriodo;
    private TextInputEditText   etCor;
    private View[]              botoesCores;
    private MaterialButton      btnSalvar;
    private AppDatabase         database;
    private SessionManager      sessionManager;
    private int                 disciplinaId = -1;
    private Disciplina          disciplinaEditando;
    private String corSelecionada = ValidationUtils.CORES_PADRAO[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina_form);

        database = AppDatabase.getInstance(this);
        sessionManager = new SessionManager(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        disciplinaId = getIntent().getIntExtra(EXTRA_DISCIPLINA_ID, -1);

        initViews();
        setupBotoesCores();
        setupListeners();

        if (disciplinaId != -1) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(getString(R.string.title_editar_disciplina));
            }
            carregarDisciplina();
        } else {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(getString(R.string.title_nova_disciplina));
            }
            selecionarCor(corSelecionada);
        }
    }

    private void initViews() {
        tilNome = findViewById(R.id.tilNome);
        tilProfessor = findViewById(R.id.tilProfessor);
        tilCor = findViewById(R.id.tilCor);
        tilPeriodo = findViewById(R.id.tilPeriodo);
        etNome = findViewById(R.id.etNome);
        etProfessor = findViewById(R.id.etProfessor);
        etPeriodo = findViewById(R.id.etPeriodo);
        etCor = findViewById(R.id.etCor);
        btnSalvar = findViewById(R.id.btnSalvar);

        botoesCores = new View[] {
            findViewById(R.id.cor0),
            findViewById(R.id.cor1),
            findViewById(R.id.cor2),
            findViewById(R.id.cor3),
            findViewById(R.id.cor4),
            findViewById(R.id.cor5),
            findViewById(R.id.cor6),
            findViewById(R.id.cor7),
            findViewById(R.id.cor8),
            findViewById(R.id.cor9)
        };
    }

    private void setupBotoesCores() {
        for (int i = 0; i < botoesCores.length; i++) {
            final String cor = ValidationUtils.CORES_PADRAO[i];
            botoesCores[i].setBackgroundColor(android.graphics.Color.parseColor(cor));
            botoesCores[i].setOnClickListener(v -> selecionarCor(cor));
        }
    }

    private void selecionarCor(String cor) {
        corSelecionada = cor;
        etCor.setText(cor);
        View previewCor = findViewById(R.id.viewPreviewCor);
        if (previewCor != null) {
            previewCor.setBackgroundColor(android.graphics.Color.parseColor(cor));
        }
    }

    private void setupListeners() {
        btnSalvar.setOnClickListener(v -> salvarDisciplina());

        etCor.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String cor = etCor.getText() != null ? etCor.getText().toString().trim() : "";
                if (ValidationUtils.isCorHexValida(cor)) {
                    selecionarCor(cor);
                }
            }
        });
    }

    private void carregarDisciplina() {
        disciplinaEditando = database.disciplinaDao().buscarPorId(disciplinaId);
        if (disciplinaEditando == null) {
            Toast.makeText(this, getString(R.string.erro_disciplina_nao_encontrada), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        etNome.setText(disciplinaEditando.getNome());
        etProfessor.setText(disciplinaEditando.getProfessor());
        etPeriodo.setText(String.valueOf(disciplinaEditando.getPeriodo()));
        if (disciplinaEditando.getCor() != null) {
            selecionarCor(disciplinaEditando.getCor());
        }
    }

    private void salvarDisciplina() {
        String nome = etNome.getText() != null ? etNome.getText().toString().trim() : "";
        String professor = etProfessor.getText() != null ? etProfessor.getText().toString().trim() : "";
        String periodoStr = etPeriodo.getText() != null ? etPeriodo.getText().toString().trim() : "";
        String corDigitada = etCor.getText() != null ? etCor.getText().toString().trim() : "";

        tilNome.setError(null);
        tilPeriodo.setError(null);

        boolean valido = true;

        if (!ValidationUtils.isNomeValido(nome)) {
            tilNome.setError(getString(R.string.erro_campo_obrigatorio));
            valido = false;
        }

        int periodo = 0;
        if (!TextUtils.isEmpty(periodoStr)) {
            try {
                periodo = Integer.parseInt(periodoStr);
                if (periodo < 1 || periodo > 20) {
                    tilPeriodo.setError(getString(R.string.erro_periodo_invalido));
                    valido = false;
                }
            } catch (NumberFormatException e) {
                tilPeriodo.setError(getString(R.string.erro_periodo_invalido));
                valido = false;
            }
        }

        if (!TextUtils.isEmpty(corDigitada) && ValidationUtils.isCorHexValida(corDigitada)) {
            corSelecionada = corDigitada;
        }

        if (!valido) {
            return;
        }

        if (disciplinaEditando != null) {
            disciplinaEditando.setNome(nome);
            disciplinaEditando.setProfessor(TextUtils.isEmpty(professor) ? null : professor);
            disciplinaEditando.setCor(corSelecionada);
            disciplinaEditando.setPeriodo(periodo);
            database.disciplinaDao().atualizar(disciplinaEditando);
            Toast.makeText(this, getString(R.string.sucesso_disciplina_atualizada), Toast.LENGTH_SHORT).show();
        } else {
            Disciplina nova = new Disciplina(
                    sessionManager.getUserId(), nome,
                    TextUtils.isEmpty(professor) ? null : professor,
                    corSelecionada, periodo
            );
            database.disciplinaDao().inserir(nova);
            Toast.makeText(this, getString(R.string.sucesso_disciplina_criada), Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
