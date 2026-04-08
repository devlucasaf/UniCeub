package com.exemplo.alinhamentos;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlinhamentosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(40, 40, 40, 40);

        root.addView(criarExemplo("MainAxisAlignment.start (horizontal)",
                LinearLayout.HORIZONTAL, Gravity.START, Gravity.CENTER_VERTICAL));

        root.addView(criarExemplo("MainAxisAlignment.center",
                LinearLayout.HORIZONTAL, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL));

        root.addView(criarExemplo("MainAxisAlignment.end",
                LinearLayout.HORIZONTAL, Gravity.END, Gravity.CENTER_VERTICAL));

        root.addView(criarExemploStretch("CrossAxisAlignment.stretch (altura preenchida)",
                LinearLayout.HORIZONTAL));

        root.addView(criarExemploSpaceBetween("MainAxisAlignment.spaceBetween (com weight)",
                LinearLayout.HORIZONTAL));

        root.addView(criarExemplo("Column - MainAxisAlignment.center (vertical)",
                LinearLayout.VERTICAL, Gravity.CENTER_VERTICAL, Gravity.CENTER_HORIZONTAL));

        setContentView(root);
    }

    /**
     * @param titulo       
     * @param orientation  
     * @param mainGravity  
     * @param crossGravity 
     * @return 
     */
    private LinearLayout criarExemplo(String titulo, int orientation, int mainGravity, int crossGravity) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        container.setPadding(0, 20, 0, 20);

        TextView tvTitulo = new TextView(this);
        tvTitulo.setText(titulo);
        tvTitulo.setTextSize(16);
        tvTitulo.setTextColor(Color.BLACK);
        tvTitulo.setPadding(0, 0, 0, 20);
        container.addView(tvTitulo);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(orientation);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                orientation == LinearLayout.HORIZONTAL ? 120 : ViewGroup.LayoutParams.WRAP_CONTENT));

        layout.setBackgroundColor(Color.LTGRAY);
        layout.setGravity(mainGravity | crossGravity);

        int[] cores = {Color.BLUE, Color.rgb(255, 192, 203), Color.rgb(128, 0, 128), Color.GREEN, Color.rgb(255, 165, 0)};
        
        for (int i = 0; i < cores.length; i++) {
            TextView quadrado = new TextView(this);
            quadrado.setBackgroundColor(cores[i]);
            quadrado.setText(" ");
            int tamanho = 80;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tamanho, tamanho);
            
            if (orientation == LinearLayout.HORIZONTAL) {
                params.rightMargin = 10;
            } 
            
            else {
                params.bottomMargin = 10;
            }
            quadrado.setLayoutParams(params);
            layout.addView(quadrado);
        }

        container.addView(layout);
        return container;
    }

    private LinearLayout criarExemploStretch(String titulo, int orientation) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        container.setPadding(0, 20, 0, 20);

        TextView tvTitulo = new TextView(this);
        tvTitulo.setText(titulo);
        tvTitulo.setTextSize(16);
        tvTitulo.setTextColor(Color.BLACK);
        container.addView(tvTitulo);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(orientation);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150)); 
        layout.setBackgroundColor(Color.LTGRAY);

        layout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.FILL_VERTICAL);

        int[] cores = {Color.BLUE, Color.rgb(255, 192, 203), Color.rgb(128, 0, 128), Color.GREEN, Color.rgb(255, 165, 0)};
        
        for (int i = 0; i < cores.length; i++) {
            TextView quadrado = new TextView(this);
            quadrado.setBackgroundColor(cores[i]);
            quadrado.setText(" ");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
            params.rightMargin = 10;
            quadrado.setLayoutParams(params);
            layout.addView(quadrado);
        }

        container.addView(layout);
        return container;
    }

    private LinearLayout criarExemploSpaceBetween(String titulo, int orientation) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        container.setPadding(0, 20, 0, 20);

        TextView tvTitulo = new TextView(this);
        tvTitulo.setText(titulo);
        tvTitulo.setTextSize(16);
        tvTitulo.setTextColor(Color.BLACK);
        container.addView(tvTitulo);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(orientation);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 120));
        layout.setBackgroundColor(Color.LTGRAY);

        int[] cores = {Color.BLUE, Color.rgb(255, 192, 203), Color.rgb(128, 0, 128), Color.GREEN, Color.rgb(255, 165, 0)};
        
        for (int i = 0; i < cores.length; i++) {
            TextView quadrado = new TextView(this);
            quadrado.setBackgroundColor(cores[i]);
            quadrado.setText(" ");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            params.weight = 1.0f; 
            
            if (orientation == LinearLayout.HORIZONTAL) {
                params.rightMargin = 5;
                params.leftMargin = 5;
            } 
            
            else {
                params.bottomMargin = 5;
                params.topMargin = 5;
            }

            quadrado.setLayoutParams(params);
            layout.addView(quadrado);
        }

        container.addView(layout);
        return container;
    }
}