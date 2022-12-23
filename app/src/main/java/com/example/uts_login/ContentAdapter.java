package com.example.uts_login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.edit.getContext())
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1300)
                        .create();

//                dialogPlus.show();

                View view1 = dialogPlus.getHolderView();

                EditText judul = view1.findViewById(R.id.edit_judul);
                EditText kategori = view1.findViewById(R.id.edit_kategori);
                EditText konten = view1.findViewById(R.id.edit_kategori);


                Button updateBtn = view1.findViewById(R.id.update_btn);

                judul.setText(dataContent.getJudul());
                kategori.setText(dataContent.getKategori());
                konten.setText(dataContent.getKonten());

                dialogPlus.show();

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("judul",judul.getText().toString());
                        map.put("kategori",kategori.getText().toString());
                        map.put("konten",konten.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child(holder.key).setValue(map);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, kategori, konten;
        Button edit, delete;
        String key;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            kategori = itemView.findViewById(R.id.kategori);
            konten = itemView.findViewById(R.id.konten);

            edit = itemView.findViewById(R.id.edit_berita_btn);
            delete = itemView.findViewById(R.id.delete_berita_btn);
        }
    }
}
