package com.example.uts_login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private final ArrayList<Content> values;
    private final LayoutInflater inflater;

    public ContentAdapter(Context context, ArrayList<Content> values) {
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Content dataContent = values.get(position);
        holder.judul.setText(dataContent.getJudul());
        holder.kategori.setText("Kategori: "+dataContent.getKategori());
        holder.konten.setText(dataContent.getKonten());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ContentDetails.class);
                intent.putExtra("judul", dataContent.getJudul());
                intent.putExtra("kategori", dataContent.getKategori());
                intent.putExtra("konten", dataContent.getKonten());
                intent.putExtra("tanggalRilis", dataContent.getTanggalRilis());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, kategori, konten;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            kategori = itemView.findViewById(R.id.kategori);
            konten = itemView.findViewById(R.id.konten);
        }
    }
}
