package com.unitask.app.adapters;

import com.unitask.app.R;
import com.unitask.app.holder.ViewHolder;
import com.unitask.app.interfaces.OnDisciplinaClickListener;
import com.unitask.app.models.Disciplina;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final Context context;
    private List<Disciplina> disciplinas;
    private final OnDisciplinaClickListener disciplinaEditarListener;
    private final OnDisciplinaClickListener disciplinaExcluirListener;

    public DisciplinaAdapter(Context context, List<Disciplina> disciplinas,
              OnDisciplinaClickListener disciplinaEditarListener, OnDisciplinaClickListener disciplinaExcluirListener) {
        this.context = context;
        this.disciplinas = disciplinas;
        this.disciplinaEditarListener = disciplinaEditarListener;
        this.disciplinaExcluirListener = disciplinaExcluirListener;
    }

    public void atualizarLista(List<Disciplina> novaLista) {
        this.disciplinas = novaLista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_disciplina, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Disciplina disciplina = disciplinas.get(position);

        holder.tvNome.setText(disciplina.getNome());
        holder.tvProfessor.setText(disciplina.getProfessor() != null 
                ? disciplina.getProfessor() 
                : context.getString(R.string.sem_professor));
                
        holder.tvPeriodo.setText(disciplina.getPeriodo() > 0
                ? context.getString(R.string.periodo_numero, disciplina.getPeriodo())
                : context.getString(R.string.sem_periodo));

        if (disciplina.getCor() != null && !disciplina.getCor().isEmpty()) {
            try {
                int cor = Color.parseColor(disciplina.getCor());
                holder.viewCor.setBackgroundColor(cor);
                holder.tvIniciais.setBackgroundColor(cor);
            } catch (IllegalArgumentException e) {
                holder.viewCor.setBackgroundColor(Color.parseColor("#6C5CE7"));
                holder.tvIniciais.setBackgroundColor(Color.parseColor("#6C5CE7"));
            }
        }

        // Iniciais da disciplina
        String iniciais = disciplina.getNome().length() >= 2
                ? disciplina.getNome().substring(0, 2).toUpperCase()
                : disciplina.getNome().toUpperCase();
        holder.tvIniciais.setText(iniciais);

        holder.itemView.setOnClickListener(v -> disciplinaEditarListener.onClick(disciplina));
        holder.btnEditar.setOnClickListener(v -> disciplinaEditarListener.onClick(disciplina));
        holder.btnExcluir.setOnClickListener(v -> disciplinaExcluirListener.onClick(disciplina));
    }

    @Override
    public int getItemCount() {
        return disciplinas != null ? disciplinas.size() : 0;
    }
}
