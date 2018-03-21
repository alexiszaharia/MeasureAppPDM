package com.example.stud.measureapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stud on 2/28/2018.
 */

public class AdaptorLista extends RecyclerView.Adapter<AdaptorLista.ViewHolder>{

    private List<Masuratoare> listaMasuratori;

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView denumire, data, suprafata;
        public ViewHolder(View itemView) {
            super(itemView);
            denumire = itemView.findViewById(R.id.tv_denumire);
            data = itemView.findViewById(R.id.tv_data);
            suprafata = itemView.findViewById(R.id.tv_suprafata);
        }
    }

    public AdaptorLista(List<Masuratoare> masuratori) {
        this.listaMasuratori = masuratori;
    }

    @Override
    public AdaptorLista.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_lista, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptorLista.ViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        holder.denumire.setText(listaMasuratori.get(position).getDenumire() + " | ");
        holder.data.setText(listaMasuratori.get(position).getData() + " | ");
        holder.suprafata.setText("" + listaMasuratori.get(position).getSuprafata());
    }

    @Override
    public int getItemCount() {
        return listaMasuratori.size();
    }
}
