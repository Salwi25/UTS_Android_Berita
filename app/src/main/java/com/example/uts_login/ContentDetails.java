package com.example.uts_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContentDetails extends AppCompatActivity {
    TextView judul, kategori, tanggalRilis, konten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details);

        judul = findViewById(R.id.judul);
        kategori = findViewById(R.id.kategori);
        tanggalRilis = findViewById(R.id.tanggal_rilis);
        konten = findViewById(R.id.konten);

        Intent intent = getIntent();
        String txt_judul = intent.getStringExtra("judul");
        String txt_kategori = intent.getStringExtra("kategori");
        String txt_tanggalRilis = intent.getStringExtra("tanggalRilis");
        String txt_konten = intent.getStringExtra("konten");

        judul.setText(txt_judul);
        kategori.setText(txt_kategori);
        tanggalRilis.setText(txt_tanggalRilis);
        konten.setText(txt_konten);

        findViewById(R.id.edit_berita_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentDetails.this, AddBeritaForm.class);
                startActivity(intent);
            }
        });
    }
}