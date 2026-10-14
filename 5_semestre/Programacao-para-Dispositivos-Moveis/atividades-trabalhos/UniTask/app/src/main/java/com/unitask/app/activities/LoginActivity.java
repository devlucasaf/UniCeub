package com.unitask.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.unitask.app.R;
import com.unitask.app.database.AppDatabase;
import com.unitask.app.models.Usuario;
import com.unitask.app.utils.SessionManager;
import com.unitask.app.utils.ValidationUtils;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout     tilEmail;
    private TextInputLayout     tilSenha;
    private TextInputEditText   etEmail;
    private TextInputEditText   etSenha;
    private MaterialButton      btnLogin;
    private MaterialButton      btnCadastrar;
    private AppDatabase         database;
    private SessionManager      sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = AppDatabase.getInstance(this);
        sessionManager = new SessionManager(this);

        initViews();
        setupListeners();
    }

    private void initViews() {
        tilEmail = findViewById(R.id.tilEmail);
        tilSenha = findViewById(R.id.tilSenha);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(v -> realizarLogin());
        btnCadastrar.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    private void realizarLogin() {
        String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
        String senha = etSenha.getText() != null ? etSenha.getText().toString().trim() : "";

        boolean valido = true;

        tilEmail.setError(null);
        tilSenha.setError(null);

        if (!ValidationUtils.isEmailValido(email)) {
            tilEmail.setError(getString(R.string.erro_email_invalido));
            valido = false;
        }

        if (TextUtils.isEmpty(senha)) {
            tilSenha.setError(getString(R.string.erro_campo_obrigatorio));
            valido = false;
        }

        if (!valido) {
            return;
        }

        Usuario usuario = database.usuarioDao().login(email, senha);
        if (usuario != null) {
            sessionManager.createSession(usuario.getId(), usuario.getNome(), usuario.getEmail());
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.erro_credenciais), Toast.LENGTH_LONG).show();
        }
    }
}
