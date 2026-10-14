package com.unitask.app.holder;

import com.unitask.app.R;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class AtividadeViewHolder extends RecyclerView.ViewHolder {

    public final CardView   cardView;
    public final View       viewCorDisciplina;
    public final TextView   txtTitulo;
    public final TextView   txtDisciplina;
    public final TextView   txtTipo;
    public final TextView   txtDataEntrega;
    public final TextView   txtPrazo;
    public final TextView   txtPrioridade;
    public final TextView   txtSituacao;

    public AtividadeViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardView);
        viewCorDisciplina = itemView.findViewById(R.id.viewCorDisciplina);
        txtTitulo = itemView.findViewById(R.id.txtTitulo);
        txtDisciplina = itemView.findViewById(R.id.txtDisciplina);
        txtTipo = itemView.findViewById(R.id.txtTipo);
        txtDataEntrega = itemView.findViewById(R.id.txtDataEntrega);
        txtPrazo = itemView.findViewById(R.id.txtPrazo);
        txtPrioridade = itemView.findViewById(R.id.txtPrioridade);
        txtSituacao = itemView.findViewById(R.id.txtSituacao);
    }
}

