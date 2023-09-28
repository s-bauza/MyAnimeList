package com.example.santiagobauzahirschlermyanimelist.rest.adapters;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.santiagobauzahirschlermyanimelist.R;
import com.example.santiagobauzahirschlermyanimelist.rest.models.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterAnimes extends RecyclerView.Adapter<AdapterAnimes.ViewHolder> {

    private List<Datum> datos;

    public AdapterAnimes(List<Datum> datos) {
        this.datos = datos;
    }


    @NonNull
    @Override
    public AdapterAnimes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_anime, parent, false);
        ViewHolder mvh = new ViewHolder(itemView);return mvh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAnimes.ViewHolder holder, int position) {
        holder.nombre.setText(datos.get(position).getTitle());
        holder.url.setText("MyAnimeList: "+datos.get(position).getUrl());
        holder.duracion.setText("Duracion: " +datos.get(position).getDuration());
        holder.estado.setText("Estado: "+datos.get(position).getStatus());
        holder.puntuacion.setText("Puntuacion "+datos.get(position).getScore());
        holder.rank.setText("Rank: "+datos.get(position).getRank().toString());
        holder.sinopsis.setText("Sinopsis:\n"+datos.get(position).getSynopsis());
        if (datos.get(position).getImages().getJpg().getImageUrl()!="")
        Picasso.get()
                .load(datos.get(position).getImages().getJpg().getImageUrl())
                .error(R.mipmap.ic_launcher)
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre,duracion,estado,puntuacion,rank,sinopsis,url;
        private ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.text_nombre);
            foto = itemView.findViewById(R.id.imag_foto);
            duracion = itemView.findViewById(R.id.text_duracion);
            estado = itemView.findViewById(R.id.text_estado);
            puntuacion = itemView.findViewById(R.id.text_puntuacion);
            rank = itemView.findViewById(R.id.text_rank);
            url = itemView.findViewById(R.id.text_url);
            sinopsis = itemView.findViewById(R.id.text_sinopsis);

        }
    }
}
