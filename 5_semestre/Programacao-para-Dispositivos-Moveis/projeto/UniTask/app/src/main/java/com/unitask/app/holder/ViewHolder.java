package com.unitask.app.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitask.app.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public View        viewCor;
    public View        btnEditar;
    public View        btnExcluir;
    public TextView    tvIniciais;
    public TextView    tvNome;
    public TextView    tvProfessor;
    public TextView    tvPeriodo;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        viewCor = itemView.findViewById(R.id.viewCor);
        tvIniciais = itemView.findViewById(R.id.tvIniciais);
        tvNome = itemView.findViewById(R.id.tvNome);
        tvProfessor = itemView.findViewById(R.id.tvProfessor);
        tvPeriodo = itemView.findViewById(R.id.tvPeriodo);
        btnEditar = itemView.findViewById(R.id.btnEditar);
        btnExcluir = itemView.findViewById(R.id.btnExcluir);
    }
}