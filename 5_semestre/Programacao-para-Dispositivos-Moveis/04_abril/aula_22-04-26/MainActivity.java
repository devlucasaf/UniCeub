package com.example.componentes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText        edtNome;
    CheckBox        checkBoxBackend;
    CheckBox        checkBoxFrontend;
    RadioButton     radioGrupoSexo;
    RadioButton     radioFeminino;
    RadioButton     radioMasculino;
    ToggleButton    toggleNotificacao;
    Switch          switchNotificacao;
    Button          btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtNome             = (EditText)        findViewById(R.id.edtNome);
        checkBoxBackend     = (CheckBox)        findViewById(R.id.checkBoxBackend);
        checkBoxFrontend    = (CheckBox)        findViewById(R.id.checkBoxFrontend);
        radioGrupoSexo      = (RadioButton)     findViewById(R.id.radioGrupoSexo);
        radioFeminino       = (RadioButton)     findViewById(R.id.radioFeminino);
        radioMasculino      = (RadioButton)     findViewById(R.id.radioMasculino);
        toggleNotificacao   = (ToggleButton)    findViewById(R.id.toggleNotificacao);
        switchNotificacao   = (Switch)          findViewById(R.id.switchNotificacao);
        btnCadastrar        = (Button)          findViewById(R.id.btnCadastrar);
    }
}