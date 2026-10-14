package com.unitask.app.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitask.app.R;

public class DisciplinaViewHolder extends RecyclerView.ViewHolder {

    public final View       viewCor;
    public final View       btnEditar;
    public final View       btnExcluir;
    public final TextView   txtIniciais;
    public final TextView   txtNome;
    public final TextView   txtProfessor;
    public final TextView   txtPeriodo;

    public DisciplinaViewHolder(@NonNull View itemView) {
        super(itemView);

        viewCor = itemView.findViewById(R.id.viewCor);
        btnEditar = itemView.findViewById(R.id.btnEditar);
        btnExcluir = itemView.findViewById(R.id.btnExcluir);
        txtIniciais = itemView.findViewById(R.id.tvIniciais);
        txtNome = itemView.findViewById(R.id.tvNome);
        txtProfessor = itemView.findViewById(R.id.tvProfessor);
        txtPeriodo = itemView.findViewById(R.id.tvPeriodo);
    }
}

