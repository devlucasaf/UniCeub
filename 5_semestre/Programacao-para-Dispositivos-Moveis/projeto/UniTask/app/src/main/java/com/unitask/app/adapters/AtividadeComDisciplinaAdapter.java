package com.unitask.app.adapters;

import com.unitask.app.R;
import com.unitask.app.interfaces.OnAtividadeClickListener;
import com.unitask.app.models.AtividadeComDisciplina;
import com.unitask.app.utils.DateUtils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AtividadeComDisciplinaAdapter extends RecyclerView.Adapter<AtividadeComDisciplinaAdapter.ViewHolder> {

    private final Context context;
    private List<AtividadeComDisciplina> atividadesComDisciplina;
    private final OnAtividadeClickListener atividadeClickListener;
    private OnAtividadeClickListener atividadeLongClickListener;

    public AtividadeComDisciplinaAdapter(Context context, List<AtividadeComDisciplina> atividadesComDisciplina,
                                          OnAtividadeClickListener atividadeClickListener) {
        this.context = context;
        this.atividadesComDisciplina = atividadesComDisciplina;
        this.atividadeClickListener = atividadeClickListener;
    }

    public void setOnLongClickListener(OnAtividadeClickListener listener) {
        this.atividadeLongClickListener = listener;
    }

    public void atualizarLista(List<AtividadeComDisciplina> novaLista) {
        this.atividadesComDisciplina = novaLista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_atividade, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AtividadeComDisciplina atividade = atividadesComDisciplina.get(position);

        holder.tvTitulo.setText(atividade.getTitulo());
        holder.tvDisciplina.setText(atividade.getDisciplinaNome());
        holder.tvTipo.setText(formatarTipo(atividade.getTipo()));
        holder.tvDataEntrega.setText(DateUtils.formatarDataHora(atividade.getDataEntrega()));
        holder.tvPrazo.setText(DateUtils.diasRestantes(atividade.getDataEntrega()));

        holder.tvPrazo.setTextColor(DateUtils.corPrazo(atividade.getDataEntrega(), atividade.getSituacao()));

        if (atividade.getDisciplinaCor() != null) {
            try {
                int cor = Color.parseColor(atividade.getDisciplinaCor());
                holder.viewCorDisciplina.setBackgroundColor(cor);
                holder.tvDisciplina.setTextColor(cor);
            } catch (IllegalArgumentException e) {
                holder.viewCorDisciplina.setBackgroundColor(Color.parseColor("#6C5CE7"));
            }
        }

        holder.tvPrioridade.setText(formatarPrioridade(atividade.getPrioridade()));
        int corPrioridade = corPrioridade(atividade.getPrioridade());
        holder.tvPrioridade.setBackgroundColor(corPrioridade);

        holder.tvSituacao.setText(formatarSituacao(atividade.getSituacao()));
        int corSituacao = corSituacao(atividade.getSituacao(), atividade);
        holder.tvSituacao.setBackgroundColor(corSituacao);

        if (atividade.isConcluida()) {
            holder.tvTitulo.setPaintFlags(holder.tvTitulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemView.setAlpha(0.6f);
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_concluida));
        } else if (atividade.isAtrasada()) {
            holder.tvTitulo.setPaintFlags(holder.tvTitulo.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemView.setAlpha(1.0f);
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_atrasada));
        } else {
            holder.tvTitulo.setPaintFlags(holder.tvTitulo.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemView.setAlpha(1.0f);
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_normal));
        }

        holder.itemView.setOnClickListener(v -> atividadeClickListener.onClick(atividade));
        if (atividadeLongClickListener != null) {
            holder.itemView.setOnLongClickListener(v -> {
                atividadeLongClickListener.onClick(atividade);
                return true;
            });
        }
    }

    private String formatarTipo(String tipo) {
        switch (tipo) {
            case "TRABALHO":
                return context.getString(R.string.tipo_trabalho);
            case "PROVA":
                return context.getString(R.string.tipo_prova);
            case "EXERCICIO":
                return context.getString(R.string.tipo_exercicio);
            case "SEMINARIO":
                return context.getString(R.string.tipo_seminario);
            case "PROJETO":
                return context.getString(R.string.tipo_projeto);
            default:
                return tipo;
        }
    }

    private String formatarPrioridade(String prioridade) {
        switch (prioridade) {
            case "BAIXA":
                return context.getString(R.string.prioridade_baixa);
            case "MEDIA":
                return context.getString(R.string.prioridade_media);
            case "ALTA":
                return context.getString(R.string.prioridade_alta);
            default:
                return prioridade;
        }
    }

    private String formatarSituacao(String situacao) {
        switch (situacao) {
            case "PENDENTE":
                return context.getString(R.string.situacao_pendente);
            case "CONCLUIDA":
                return context.getString(R.string.situacao_concluida);
            default:
                return situacao;
        }
    }

    private int corPrioridade(String prioridade) {
        switch (prioridade) {
            case "ALTA":
                return Color.parseColor("#F44336");
            case "MEDIA":
                return Color.parseColor("#FF9800");
            case "BAIXA":
                return Color.parseColor("#4CAF50");
            default:
                return Color.GRAY;
        }
    }

    private int corSituacao(String situacao, AtividadeComDisciplina a) {
        if ("CONCLUIDA".equals(situacao)) {
            return Color.parseColor("#4CAF50");
        }

        if (a.isAtrasada()) {
            return Color.parseColor("#F44336");
        }
        return Color.parseColor("#2196F3");
    }

    @Override
    public int getItemCount() {
        return atividadesComDisciplina != null ? atividadesComDisciplina.size() : 0;
    }
}
