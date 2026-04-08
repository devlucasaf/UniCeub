import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StatefulWidgetExemploActivity extends AppCompatActivity {

    private int contador = 0; 
    private TextView textViewContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewLabel = findViewById(R.id.textViewLabel);
        textViewContador = findViewById(R.id.textViewContador);
        Button buttonIncrementar = findViewById(R.id.buttonIncrementar);

        buttonIncrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementar();
            }
        });

        atualizarUI();
    }

    private void incrementar() {
        contador++; 
        atualizarUI(); 
    }

    private void atualizarUI() {
        textViewContador.setText(String.valueOf(contador));
    }
}