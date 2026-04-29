package com.example.componentes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.componentes.model.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText        edtNome;
    CheckBox        checkBoxBackend;
    CheckBox        checkBoxFrontend;
    RadioGroup      radioGrupoSexo;
    RadioButton     radioFeminino;
    RadioButton     radioMasculino;
    ToggleButton    toggleNotificacao;
    Switch          switchNotificacao;
    Button          btnEnviarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtNome             = (EditText)        findViewById(R.id.edtNome);
        checkBoxBackend     = (CheckBox)        findViewById(R.id.checkBoxBackend);
        checkBoxFrontend    = (CheckBox)        findViewById(R.id.checkBoxFrontend);
        radioGrupoSexo      = (RadioGroup)      findViewById(R.id.radioGrupoSexo);
        radioFeminino       = (RadioButton)     findViewById(R.id.radioFeminino);
        radioMasculino      = (RadioButton)     findViewById(R.id.radioMasculino);
        toggleNotificacao   = (ToggleButton)    findViewById(R.id.toggleNotificacao);
        switchNotificacao   = (Switch)          findViewById(R.id.switchNotificacao);
        btnEnviarCadastro   = (Button)          findViewById(R.id.btnEnviarCadastro);

        btnEnviarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Usuario usuario = new Usuario();
                StringBuilder resultado = new StringBuilder();

                usuario.setNome(edtNome.getText().toString());
                usuario.setAreaAtuacao(resultado.toString());

                if (radioGrupoSexo.getCheckedRadioButtonId() == R.id.radioFeminino) {
                    usuario.setSexo("Feminino");
                } else {
                    usuario.setSexo("Masculino");
                }

                if (checkBoxBackend.isChecked()) {
                    resultado.append("\nBackEnd");
                }

                if (checkBoxFrontend.isChecked()) {
                    resultado.append("\nFrontEnd");
                }

                if (switchNotificacao.isChecked()) {
                    usuario.setNotificacao("Sim");
                } else {
                    usuario.setNotificacao("Não");
                }

                intent.putExtra("objeto", usuario);
                startActivity(intent);
            }
        });
    }
}