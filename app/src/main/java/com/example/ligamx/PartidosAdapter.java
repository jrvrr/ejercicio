package com.example.ligamx;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PartidosAdapter extends RecyclerView.Adapter<PartidosAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Partido> listaPartidos;

    public PartidosAdapter(Context context, ArrayList<Partido> listaPartidos) {
        this.context = context;
        this.listaPartidos = listaPartidos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_partido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Partido partido = listaPartidos.get(position);

        holder.txtEquipoLocal.setText("Equipo Local: " + partido.getEquipoLocal());
        holder.txtEquipoVisitante.setText("Equipo Visitante: " + partido.getEquipoVisitante());
        holder.txtCantidadApuesta.setText("Cantidad de Apuesta: " + partido.getCantidadPartido());
        holder.txtGanador.setText("Ganador: " + partido.getGanador());
    }

    @Override
    public int getItemCount() {
        return listaPartidos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtEquipoLocal, txtEquipoVisitante, txtCantidadApuesta, txtGanador;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtEquipoLocal = itemView.findViewById(R.id.txtEquipoLocal);
            txtEquipoVisitante = itemView.findViewById(R.id.txtEquipoVisitante);
            txtCantidadApuesta = itemView.findViewById(R.id.txtCantidadApuesta);
            txtGanador = itemView.findViewById(R.id.txtGanador);
        }
    }
}

