import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import java.util.ArrayList;
import java.util.List;

public class FormExemploJava extends AppCompatActivity {

    private EditText nomeEditText;
    private CheckBox aceitaTermosCheckBox;
    private RadioGroup generoRadioGroup;
    private Spinner cidadeSpinner;
    private Button enviarButton;

    private String generoSelecionado = "Masculino";
    private String cidadeSelecionada = "São Paulo";
    private boolean aceitaTermos = false;

    private final List<String> cidades = new ArrayList<String>() {{
        add("São Paulo");
        add("Rio de Janeiro");
        add("Belo Horizonte");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_exemplo); 

        nomeEditText = findViewById(R.id.nomeEditText);
        aceitaTermosCheckBox = findViewById(R.id.aceitaTermosCheckBox);
        generoRadioGroup = findViewById(R.id.generoRadioGroup);
        cidadeSpinner = findViewById(R.id.cidadeSpinner);
        enviarButton = findViewById(R.id.enviarButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, cidades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cidadeSpinner.setAdapter(adapter);
        cidadeSpinner.setSelection(cidades.indexOf(cidadeSelecionada));

        generoRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            generoSelecionado = radioButton.getText().toString();
        });

        aceitaTermosCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> aceitaTermos = isChecked);

        cidadeSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                cidadeSelecionada = cidades.get(position);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                cidadeSelecionada = cidades.get(0);
            }
        });

        enviarButton.setOnClickListener(v -> enviarFormulario());
    }

    private void enviarFormulario() {
        String nome = nomeEditText.getText().toString();
        Log.d("FormExemplo", "Nome: " + nome);
        Log.d("FormExemplo", "Aceita termos: " + aceitaTermos);
        Log.d("FormExemplo", "Gênero: " + generoSelecionado);
        Log.d("FormExemplo", "Cidade: " + cidadeSelecionada);

        Toast.makeText(this, "Formulário enviado (ver Logcat)", Toast.LENGTH_SHORT).show();
    }
}