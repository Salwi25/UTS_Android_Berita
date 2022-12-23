package com.example.uts_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBeritaForm extends AppCompatActivity {
    EditText mJudul, mKonten, mUmur, mTanggal, mKategori;
    private NotificationManager mNotificationManager;
    DatabaseReference databaseReference;
    Content mContent;
    String key;

    private final static String CHANNEL_ID = "primary-channel";
    private int NOTIFICATION_ID = 0;
    private final static String ACTION_UPDATE_NOTIF = "action-update-notif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_berita_form);
        databaseReference = FirebaseDatabase.getInstance().getReference(Content.class.getSimpleName());
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mJudul = findViewById(R.id.inputJudul);
        mKonten = findViewById(R.id.inputKonten);
        mUmur = findViewById(R.id.inputMinUmur);
        mTanggal = findViewById(R.id.inputTanggalRilis);
        mKategori = findViewById(R.id.inputKategori);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "app notification",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        findViewById(R.id.tambah_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahBerita();
            }
        });
    }

    private void tambahBerita(){
        Content newContent = new Content();
        String judul = mJudul.getText().toString();
        String konten = mKonten.getText().toString();
        String umur = mUmur.getText().toString();
        String tanggal = mTanggal.getText().toString();
        String kategori = mKategori.getText().toString();
        if (judul !="" && konten !="" && umur!="" && tanggal !="" && kategori !=""){
            newContent.setJudul(judul);
            newContent.setKonten(konten);
            newContent.setMinUmur(umur);
            newContent.setTanggalRilis(tanggal);
            newContent.setKategori(kategori);

            databaseReference.push().setValue(newContent);
            Toast.makeText(this, "Data tambah berita berhasil!", Toast.LENGTH_SHORT).show();
        }
    }
}