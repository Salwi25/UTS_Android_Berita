package com.example.uts_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ContentDetails extends AppCompatActivity {
    TextView judul, kategori, konten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details);

        judul = findViewById(R.id.judul);
        kategori = findViewById(R.id.kategori);
        konten = findViewById(R.id.konten);

        Intent intent = getIntent();
        String txt_judul = intent.getStringExtra("judul");
        String txt_kategori = intent.getStringExtra("kategori");
        String txt_konten = intent.getStringExtra("konten");

        judul.setText(txt_judul);
        kategori.setText(txt_kategori);
        konten.setText(txt_konten);
    }
}