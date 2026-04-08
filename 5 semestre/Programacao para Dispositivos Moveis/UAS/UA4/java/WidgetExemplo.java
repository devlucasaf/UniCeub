package com.example.widgetexemplo; // Ajuste o pacote conforme seu projeto

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WidgetExemplo extends AppCompatActivity {

    private CheckBox checkBoxOpcao1;
    private CheckBox checkBoxOpcao2;

    private Switch switchWifi;

    private RadioGroup radioGroupEscolha;
    private RadioButton radioA;
    private RadioButton radioB;

    private Spinner spinnerPaises;
    private String paisSelecionado;

    private final String[] paises = {"Brasil", "Argentina", "Chile", "Uruguai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_exemplo);

        checkBoxOpcao1 = findViewById(R.id.checkbox_opcao1);
        checkBoxOpcao2 = findViewById(R.id.checkbox_opcao2);
        switchWifi = findViewById(R.id.switch_wifi);
        radioGroupEscolha = findViewById(R.id.radio_group_escolha);
        radioA = findViewById(R.id.radio_opcao_a);
        radioB = findViewById(R.id.radio_opcao_b);
        spinnerPaises = findViewById(R.id.spinner_paises);

        checkBoxOpcao1.setOnCheckedChangeListener((buttonView, isChecked) -> { });
        checkBoxOpcao2.setOnCheckedChangeListener((buttonView, isChecked) -> { });

        switchWifi.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "ligado" : "desligado";
            Toast.makeText(WidgetExemplo.this, "Wi-Fi " + status, Toast.LENGTH_SHORT).show();
        });

        radioA.setChecked(true);

        radioGroupEscolha.setOnCheckedChangeListener((group, checkedId) -> {
            String escolha;
            if (checkedId == R.id.radio_opcao_a) {
                escolha = "A";
            } 
            
            else {
                escolha = "B";
            }
            Toast.makeText(WidgetExemplo.this, "Opção escolhida: " + escolha, Toast.LENGTH_SHORT).show();
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, paises);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaises.setAdapter(adapter);

        int posicaoBrasil = 0;
        for (int i = 0; i < paises.length; i++) {
            if (paises[i].equals("Brasil")) {
                posicaoBrasil = i;
                break;
            }
        }
        spinnerPaises.setSelection(posicaoBrasil);
        paisSelecionado = paises[posicaoBrasil];

        spinnerPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paisSelecionado = paises[position];
                Toast.makeText(WidgetExemplo.this, "País: " + paisSelecionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void exibirEstado() {
        boolean opcao1 = checkBoxOpcao1.isChecked();
        boolean opcao2 = checkBoxOpcao2.isChecked();
        boolean wifi = switchWifi.isChecked();
        int radioId = radioGroupEscolha.getCheckedRadioButtonId();
        String escolha = (radioId == R.id.radio_opcao_a) ? "A" : "B";
        String pais = paisSelecionado;

        String estado = String.format("Op1:%b, Op2:%b, Wifi:%b, Escolha:%s, País:%s",
                opcao1, opcao2, wifi, escolha, pais);
        Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
    }
}