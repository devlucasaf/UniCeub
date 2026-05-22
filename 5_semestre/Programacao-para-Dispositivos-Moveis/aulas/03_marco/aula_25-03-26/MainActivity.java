package com.example.ciclodevida;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnChamarSegundaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnChamarSegundaActivity = (Button) findViewById(R.id.cmdChamarSegundaActivity);
        btnChamarSegundaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                startActivity(intent);
            }
        });

        Log.i("Ciclo de Vida", "Método onCreate ativado");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ciclo de Vida", "Método OnStart ativado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo de Vida", "Método OnResume ativado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo de Vida", "Método OnPause ativado");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ciclo de Vida", "Método OnRestart ativado");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo de Vida", "Método OnStop ativado");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo de Vida", "Método OnDestroy ativado");
    }
}