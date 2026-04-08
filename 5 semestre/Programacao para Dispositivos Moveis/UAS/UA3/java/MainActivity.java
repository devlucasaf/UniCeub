package com.example.meuapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout imagesContainer;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagesContainer = findViewById(R.id.imagesContainer);
        titleTextView = findViewById(R.id.titleTextView);

        ajustarLayoutPelaOrientacao();

        ajustarTamanhoFontePelaTela();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ajustarLayoutPelaOrientacao();
    }

    private void ajustarLayoutPelaOrientacao() {
        int orientacao = getResources().getConfiguration().orientation;
        if (orientacao == Configuration.ORIENTATION_LANDSCAPE) {
            imagesContainer.setOrientation(LinearLayout.HORIZONTAL);
        } 
        
        else {
            imagesContainer.setOrientation(LinearLayout.VERTICAL);
        }
    }

    private void ajustarTamanhoFontePelaTela() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int larguraPixels = displayMetrics.widthPixels;
        int alturaPixels = displayMetrics.heightPixels;

        float tamanhoSp = larguraPixels * 0.05f;
        float scaledSize = tamanhoSp / getResources().getDisplayMetrics().scaledDensity;
        titleTextView.setTextSize(scaledSize);

        int paddingPx = (int) (larguraPixels * 0.02);
        titleTextView.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);
    }
}
