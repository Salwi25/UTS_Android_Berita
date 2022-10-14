package com.example.uts_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContentRV extends AppCompatActivity {

    private String basedTanggalLahir;
    public  String basedKategori;
    private TextView tvTanggal, tvKategori;
    private ContentAdapter contentAdapter;
    private RecyclerView recyclerView;

    public ArrayList<Content> contentList = new ArrayList<>();
    public ArrayList<Content> filterContentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_rv);

        tvTanggal = findViewById(R.id.tanggal_view);
        tvKategori = findViewById(R.id.kategori_view);
        recyclerView = findViewById(R.id.rv_content);

        Intent intent = getIntent();
        basedTanggalLahir = intent.getStringExtra("basedTanggalLahir");
        basedKategori = intent.getStringExtra("basedKategori");

        addContent();

        String tanggal_lahir_user[] = basedTanggalLahir.split("-");
        int tahun_lahir_user = 2022-Integer.parseInt(tanggal_lahir_user[2]);
        Toast.makeText(this, String.valueOf(tahun_lahir_user), Toast.LENGTH_SHORT).show();

        for (Content dataContent : contentList){
            Integer minUmur = Integer.valueOf(dataContent.getMinUmur());
            if (minUmur <= tahun_lahir_user && dataContent.getKategori().toLowerCase().equals(basedKategori.toLowerCase())){
                filterContentList.add(dataContent);
            }
        }

        tvTanggal.setText(basedTanggalLahir);
        tvKategori.setText(basedKategori);

        contentAdapter = new ContentAdapter(this,filterContentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contentAdapter);
    }

    private void addContent() {
        contentList.add(new Content("Puan Maharani dan Gambar Sara Lainnya Dijadikan Wallpaper Oleh Mahasiswa","Salah satu mahasiswa di suatu kampus di Yogyakarta memberi wallpaper sara pada semua komputer di suatu lab. Hal ini membuat pihak kampus meluncurkan peraturan baru kepada mahasiswa ketika menggunakan lab komputer.","17","12/12/12","Sports"));
        contentList.add(new Content("Kreator","Kreator game Yu Gi Oh! meninggal dunia dikarenakan menyelamatkan 3 nyawa orang","17","12/12/12","Politics"));
        contentList.add(new Content("Artis Rex Orange County Tergugat Pelecehan Seksual","Rex Orange County menggagalkan konsernya dikarenakan tergugat masalah pelecehan seksual 2 kali pada 1 juni 2022 di End West. Pihak yang berwajib terpaksa menghentikan konser.","5","10/10/22","Sports"));
        contentList.add(new Content("Seorang Mahasiswa Tewas Jatuh dari Lantai 11 Hotel","Pihak kepolisian mengungkap, korban memiliki identitas inisial TSR. Korban berjenis kelamin laki-laki ini bukanlah warga setempat atau warga Yogyakarta. Hasil identifikasi yang dilakukan oleh pihak kepolisian korban diketahui adalah seorang mahasiswa UGM Yogyakarta. Polisi juga menemukan sebuah surat keterangan dari psikolog di dalam tas yang dibawa oleh korban.","17","11/10/22","Politics"));
        contentList.add(new Content("Nintendo Umumkan Game Zelda Terbaru!","Link, adalah karakter utama dari game legendaris The Legend of Zelda. Video trailer tersebut dirilis untuk mengumumkan kehadiran game The Legend of Zelda: Tears of The Kingdom yang akan meluncur pada 12 Mei 2023.","10","14/09/22","Sports"));
        contentList.add(new Content("Microsoft Designer Resmi Meluncur, Aplikasi Desain Pesaing Canva","Seperti rumor yang beredar sebelumnya, Microsoft Designer bisa membantu pengguna membuat desain postingan di media sosial, membuat desain undangan, brosur dan desain lainnya. Pengguna bisa membuatnya secara manual atau memanfaatkan template yang disediakan Designer.","14","13/10/22","Sports"));
        contentList.add(new Content("Windah Basudara Dipuji Netizen Kumpulkan Rp 300 Juta untuk Siswa SLB","Windah Basudara dihujani pujian netizen usai menggelar live streaming kumpulkan donasi bantu bocah viral di TikTok bernama Rahmat atau lebih dikenal Okky Boy. Dana Rp 300 jutaan berhasil dikumpulkan sang YouTuber.","14","12/10/22","Entertainment"));
        contentList.add(new Content("Meta Quest Pro Resmi Diumumkan, Fitur Lengkap Harga Spektakuler"," Meta Quest Pro dijual dengan harga USD 1.499 atau sekitar Rp 22 juta. Harga tersebut bahkan hampir empat kali lipat lebih mahal dari Meta Quest 2 yang sudah mengalami kenaikan harga menjadi USD 499 atau sekitar Rp 7,6 juta beberapa waktu lalu.","12","13/10/22","Entertainment"));
    }
}