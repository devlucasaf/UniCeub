package com.unitask.app.activities;

import com.unitask.app.R;
import com.unitask.app.database.AppDatabase;
import com.unitask.app.models.Usuario;
import com.unitask.app.utils.ValidationUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout     tilNome;
    private TextInputLayout     tilEmail;
    private TextInputLayout     tilSenha;
    private TextInputLayout     tilConfirmarSenha;
    private TextInputLayout     tilCurso;
    private TextInputEditText   etNome;
    private TextInputEditText   etEmail;
    private TextInputEditText   etSenha;
    private TextInputEditText   etConfirmarSenha;
    private TextInputEditText   etCurso;
    private MaterialButton      btnCadastrar;
    private AppDatabase         database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = AppDatabase.getInstance(this);
        initViews();
        setupListeners();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.title_cadastro));
        }
    }

    private void initViews() {
        tilNome = findViewById(R.id.tilNome);
        tilEmail = findViewById(R.id.tilEmail);
        tilSenha = findViewById(R.id.tilSenha);
        tilConfirmarSenha = findViewById(R.id.tilConfirmarSenha);
        tilCurso = findViewById(R.id.tilCurso);
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);
        etCurso = findViewById(R.id.etCurso);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }

    private void setupListeners() {
        btnCadastrar.setOnClickListener(v -> realizarCadastro());
    }

    private void realizarCadastro() {
        String nome = etNome.getText() != null ? etNome.getText().toString().trim() : "";
        String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
        String senha = etSenha.getText() != null ? etSenha.getText().toString().trim() : "";
        String confirmarSenha = etConfirmarSenha.getText() != null ? etConfirmarSenha.getText().toString().trim() : "";
        String curso = etCurso.getText() != null ? etCurso.getText().toString().trim() : "";

        // Limpar erros
        tilNome.setError(null);
        tilEmail.setError(null);
        tilSenha.setError(null);
        tilConfirmarSenha.setError(null);

        boolean valido = true;

        if (!ValidationUtils.isNomeValido(nome)) {
            tilNome.setError(getString(R.string.erro_nome_invalido));
            valido = false;
        }

        if (!ValidationUtils.isEmailValido(email)) {
            tilEmail.setError(getString(R.string.erro_email_invalido));
            valido = false;
        } else if (database.usuarioDao().emailExiste(email) > 0) {
            tilEmail.setError(getString(R.string.erro_email_cadastrado));
            valido = false;
        }

        if (!ValidationUtils.isSenhaValida(senha)) {
            tilSenha.setError(getString(R.string.erro_senha_curta));
            valido = false;
        }

        if (!senha.equals(confirmarSenha)) {
            tilConfirmarSenha.setError(getString(R.string.erro_senhas_diferentes));
            valido = false;
        }

        if (!valido) {
            return;
        }

        Usuario usuario = new Usuario(nome, email, senha, TextUtils.isEmpty(curso) ? null : curso);
        long id = database.usuarioDao().inserir(usuario);

        if (id > 0) {
            Toast.makeText(this, getString(R.string.sucesso_cadastro), Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, getString(R.string.erro_cadastro), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
