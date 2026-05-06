package com.example.yourapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editProductName;
    private SeekBar seekBarQuantity;
    private TextView textQuantityValue;
    private RadioGroup radioGroupDelivery;
    private Spinner spinnerRegion;
    private CheckBox checkPromotions;
    private Button buttonSubmit;

    private final String[] regions = {"Centro", "Norte", "Sul", "Leste", "Oeste"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editProductName = findViewById(R.id.editProductName);
        seekBarQuantity = findViewById(R.id.seekBarQuantity);
        textQuantityValue = findViewById(R.id.textQuantityValue);
        radioGroupDelivery = findViewById(R.id.radioGroupDelivery);
        spinnerRegion = findViewById(R.id.spinnerRegion);
        checkPromotions = findViewById(R.id.checkPromotions);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, regions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegion.setAdapter(adapter);

        seekBarQuantity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int quantity = progress + 1;
                textQuantityValue.setText(String.valueOf(quantity));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarQuantity.setProgress(0);
        textQuantityValue.setText("1");

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        String product = editProductName.getText().toString().trim();

        if (product.isEmpty()) {
            Toast.makeText(this, "Por favor, informe o nome do produto", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = seekBarQuantity.getProgress() + 1;

        int selectedId = radioGroupDelivery.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Por favor, selecione o tipo de entrega", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selectedRadio = findViewById(selectedId);
        String deliveryType = selectedRadio.getText().toString();

        String region = spinnerRegion.getSelectedItem().toString();

        boolean acceptsPromotions = checkPromotions.isChecked();

        Log.d("Pedido", "=== Dados do pedido ===");
        Log.d("Pedido", "Produto: " + product);
        Log.d("Pedido", "Quantidade: " + quantity);
        Log.d("Pedido", "Tipo de entrega: " + deliveryType);
        Log.d("Pedido", "Região: " + region);
        Log.d("Pedido", "Aceita promoções: " + (acceptsPromotions ? "Sim" : "Não"));

        Toast.makeText(this, "Pedido cadastrado! Verifique o Logcat.", Toast.LENGTH_SHORT).show();
    }
}