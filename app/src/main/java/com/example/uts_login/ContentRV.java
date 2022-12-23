package com.example.uts_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContentRV extends AppCompatActivity {

    private String basedTanggalLahir;
    public  String basedKategori;
    private TextView tvTanggal, tvKategori;
    private ContentAdapter contentAdapter;
    private RecyclerView recyclerView;
    DatabaseReference databaseReference;
    String key;

    public ArrayList<Content> contentList = new ArrayList<>();
    public ArrayList<Content> filterContentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_rv);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        tvTanggal = findViewById(R.id.tanggal_view);
        tvKategori = findViewById(R.id.kategori_view);
        recyclerView = findViewById(R.id.rv_content);


        Intent intent = getIntent();
        basedTanggalLahir = intent.getStringExtra("basedTanggalLahir");
        basedKategori = intent.getStringExtra("basedKategori");

        liatBerita();
        recyclerView = findViewById(R.id.rv_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.tambah_berita_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentRV.this, AddBeritaForm.class);
                startActivity(intent);
            }
        });

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

//        contentAdapter = new ContentAdapter(this,filterContentList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(contentAdapter);
    }

    private void liatBerita(){
        databaseReference.child("Content").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    key = item.getKey();
                    Content itemContent = item.getValue(Content.class);
                    contentList.add(itemContent);
                }
                for (Content dataContent : contentList){
                    Integer minUmur = Integer.valueOf(dataContent.getMinUmur());
                    String tanggal_lahir_user[] = basedTanggalLahir.split("-");
                    int tahun_lahir_user = 2022-Integer.parseInt(tanggal_lahir_user[2]);
                    if (minUmur <= tahun_lahir_user && dataContent.getKategori().toLowerCase().equals(basedKategori.toLowerCase())){
                        filterContentList.add(dataContent);
                    }
                }
                contentAdapter = new ContentAdapter(ContentRV.this, filterContentList);
                recyclerView.setAdapter(contentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    private void addContent() {
//        contentList.add(new Content("Bagnaia: Quartararo Harus Dikalahkan!","Persaingan juara MotoGP musim ini tinggal menyisakan tiga race lagi. Quartararo dan Bagnaia bersaing ketat di papan klasemen sementara.\n" +
//                "Quartararo masih memuncaki klasemen MotoGP 2022 dengan 219 poin. Bagnaia tepat di belakangnya dengan margin dua angka saja. Francesco Bagnaia sejauh ini unggul dalam jumlah kemenangan ketimbang Fabio Quartararo. Rider Italia itu sudah enam kali naik podium pertama, berbanding El Diablo yang memenangi balapan tiga kali.\n" +
//                "Quartararo belum lagi menjadi pemenang dalam tujuh balapan terakhir. Bagnaia pun optimis dirinya bisa menaklukkan juara bertahan MotoGP itu dalam tiga race terakhir.\n" +
//                "\"Fabio jelas adalah orang yang harus dikalahkan. Dia adalah salah satu pebalap terkuat dan juara dunia tahun lalu,\" kata Bagnaia jelang MotoGP Australia, dikutip dari Crash.\n" +
//                "\"Namun saya dalam situasi yang lebih baik daripada Fabio. Saya merasa hebat dengan motor saya, saya bisa mendorong, saya bisa menyerang,\" dia menambahkan.","15","11 Oktober 2022","Sports"));
//        contentList.add(new Content("Luhut Putar Otak, Ada Kepala Negara Bawa 3 Pesawat Hadiri G20 Bali","Jakarta - Menteri Koordinator Bidang Kemaritiman dan Investasi Luhut Binsar Pandjaitan menyebut ada kepala negara yang membawa 3 pesawat ketika menghadiri acara G20 di Bali. Ia mengatakan, masalah parkir pesawat untuk gelaran G20 akan dirapatkan.\n" +
//                "\"Ini saya malah mau rapat. Jadi kita mau rapat mengenai parkir pesawatnya ini bagaimana, karena ada kepala negara yang bawa 3 pesawat,\" kata Luhut di Jakarta, Rabu (12/10/2022).\n" +
//                "Sementara, parkir pesawat di bandara Bali terbatas. Hal itu ditambah dengan meningkatnya pesawat yang masuk ke Bali.\n" +
//                "\"Padahal kan kemampuan parking space di sana kan terbatas juga. Dan sekarang pesawat yang masuk ke Bali sudah naik 132%, 2 minggu lalu, sekarang saya nggak tahu berapa,\" tambahnya.\n" +
//                "Luhut menuturkan, parkir pesawat ini akan diatur. Rencananya, sebagian pesawat tersebut akan diparkirkan di sejumlah bandara selain Bali.\n" +
//                "\"Jadi kita lagi mau atur. Jadi nanti sebagian setelah dia drop passenggers-nya, mungkin dia akan kita taruh di Lombok, di Surabaya, atau di Banyuwangi,\" ujarnya.\n" +
//                "Ditanya mengenai pesawat dari negara mana saja yang akan parkir di Bali untuk G20, Luhut mengatakan akan dirapatkan. \"Ya kita lihat nanti, lagi kita rapatin,\" katanya.","17","12 Oktober 2022","Politics"));
//        contentList.add(new Content("Rencana Reshuffle Kabinet di Kantong Jokowi","Jakarta - Kabar perombakan atau reshuffle kabinet Presiden Joko Widodo (Jokowi) mencuat usai Partai NasDem mengusung Anies Baswedan sebagai bakal calon presiden (capres) 2024. Rencana me-reshuffle Kabinet Indonesia Maju sudah di kantong Jokowi.\n" +
//                "Langkah Partai NasDem mendeklarasikan Anies Baswedan sebagai capres dinilai berpotensi kadernya kena reshuffle dari jajaran Kabinet Indonesia Maju. Jokowi mengatakan rencana reshuffle kabinet selalu ada. Namun, belum diputuskan soal pelaksanaannya.Jokowi mengatakan hal tersebut saat wartawan bertanya soal apakah ada rencana reshuffle setelah NasDem mengumumkan Anies Baswedan sebagai capres. \"Apa yang disampaikan Pak Jokowi sangat bagus, karena itu adalah hak prerogatif dari presiden. Karena Pak Jokowi perlu menteri yang loyal dan solid untuk bekerja bersama demi menyelesaikan masalah rakyat,\" ujar Hasto di Sekolah PDIP, Lenteng Agung, Jakarta Selatan, Kamis (13/10).\n" +
//                "\n" +
//                "Jokowi mengharapkan dalam Pemilu 2024, masyarakat berada dalam kondisi yang baik. Menurutnya, kabinet saat ini telah mencapai sejumlah prestasi yang tinggi. Meski demikian, Hasto menyebut Jokowi punya hak penuh untuk melakukan reshuffle. Dia mendukung Jokowi mengevaluasi menteri yang tidak patuh sembari menyinggung 'menteri antitesa'.\n" +
//                "\n" +
//                "\"Terutama mengenai masalah ekonomi dan berfokus dalam upaya membuat legacy yang dipimpinnya untuk rakyat. Sehingga Pak Jokowi akan menggunakan kewenangan penuh yang dimilikinya untuk melakukan evaluasi kepada menterinya yang tidak menjalankan perintah presiden,\" kata Hasto.\n" +
//                "\n" +
//                "\"Terutama menteri yang melakukan antitesa dari visi dan misi presiden,\" lanjutnya. Koalisi Solid, Kecuali NasDem Tarik Menteri\n" +
//                "Waektum PKB Jazilul Fawaid mengatakan partai koalisi Jokowi masih solid usai Partai NasDem deklarasikan Anies Baswedan sebagai bakal capres. PKB menghormati sikap NasDem yang masih bertahan di kabinet.\n" +
//                "\n" +
//                "\"Kita hormati hak Nasdem untuk tetap dalam koalisi pemerintahan atau keluar menjadi oposisi,\" kata Jazilul kepada wartawan, Kamis (13/10). Jazilul mengatakan kondisi partai koalisi tidak ada yang berbeda usai NasDem deklarasikan Anies. Kecuali, kata Jazilul, jika NasDem menarik menterinya dari kabinet.","15","14 Oktober 2022","Politics"));
//        contentList.add(new Content("Hasil MotoGP Australia 2022: Zarco Tercepat di FP1","Hasil FP1 MotoGP Australia 2022:\n" +
//                "\n" +
//                "Urutan-Nama-Negara-Tim-Catatan/selisih waktu\n" +
//                "\n" +
//                "1 Johann Zarco FRA Pramac Ducati (GP22) 1'30.368s\n" +
//                "2 Jack Miller AUS Ducati Lenovo (GP22) +0.091s\n" +
//                "3 Alex Marquez SPA LCR Honda (RC213V) +0.125s\n" +
//                "4 Alex Rins SPA Suzuki Ecstar (GSX-RR) +0.143s\n" +
//                "5 Aleix Espargaro SPA Aprilia Racing (RS-GP) +0.319s\n" +
//                "6 Enea Bastianini ITA Gresini Ducati (GP21) +0.422s\n" +
//                "7 Marc Marquez SPA Repsol Honda (RC213V) +0.529s\n" +
//                "8 Francesco Bagnaia ITA Ducati Lenovo (GP22) +0.709s\n" +
//                "9 Fabio Di Giannantonio ITA Gresini Ducati (GP21) +0.719s\n" +
//                "10 Marco Bezzecchi ITA Mooney VR46 Ducati (GP21) +0.798s\n" +
//                "11 Pol Espargaro SPA Repsol Honda (RC213V) +0.826s\n" +
//                "12 Fabio Quartararo FRA Monster Yamaha (YZR-M1) +0.827s\n" +
//                "13 Jorge Martin SPA Pramac Ducati (GP22) +1.051s\n" +
//                "14 Maverick Viñales SPA Aprilia Racing (RS-GP) +1.068s\n" +
//                "15 Joan Mir SPA Suzuki Ecstar (GSX-RR) +1.163s\n" +
//                "16 Darryn Binder RSA WithU Yamaha RNF (YZR-M1) +1.332s\n" +
//                "17 Cal Crutchlow GBR WithU Yamaha RNF (YZR-M1) +1.500s\n" +
//                "18 Miguel Oliveira POR Red Bull KTM (RC16) +1.582s\n" +
//                "19 Franco Morbidelli ITA Monster Yamaha (YZR-M1) +1.810s\n" +
//                "20 Remy Gardner AUS KTM Tech3 (RC16) +1.839s\n" +
//                "21 Brad Binder RSA Red Bull KTM (RC16) +2.198s\n" +
//                "22 Luca Marini ITA Mooney VR46 Ducati (GP22) +2.463s\n" +
//                "23 Raul Fernandez SPA KTM Tech3 (RC16) +2.579s\n" +
//                "24 Tetsuta Nagashima JPN LCR Honda (RC213V) +4.069s\n","17","14 Oktober 2022","Sports"));
//        contentList.add(new Content("Berbaju Tahanan, Billar Sebut Lesti Mau Cabut Laporan dengan Suara Bergetar","Jakarta - Rizky Billar dihadirkan oleh polisi saat jumpa pers mengenai kasus kekerasan dalam rumah tangga (KDRT) yang dilaporkan Lesti Kejora. Ia tampak mengenakan baju tahanan berwarna oranye.\n" +
//                "Momen Rizky Billar dihadirkan di depan awak media tidak lama. Ia juga tidak berbicara apapun ketika berdiri di depan banyak kamera.\n" +
//                "\n" +
//                "Rizky Billar sempat melepas maskernya. Ia menatap ke depan dengan tatapan kosong. Namun saat ingin kembali, sebelum memasuki lift, Rizky Billar sempat mengungkapkan sesuatu.\n" +
//                "\"Istri saya mau cabut laporan,\" ujar Rizky Billar dengan suara bergetar. Kini, polisi melakukan penahanan terhadp Rizky Billar. Hal itu dilakukan agar tersangka tidak mengulangi perbuatannya. Menyoal kabar Lesti Kejora ingin mencabut laporan, polisi tak mau berandai-andai. Kabid Humas Polda Metro Jaya Kombes Pol E Zulpan masih menunggu hasil penyidikan.\n" +
//                "\"Kami tidak bisa berandai-andai. Sampai dengan saat ini kami masih lakukan proses penyidikan,\" kata Kombes Pol E Zulpan.\n","13","14/09/22","Entertainment"));
//        contentList.add(new Content("4 Aktor dengan Peran Bad Boy: Ada Lee Dong Wook Hingga Lee Joon Gi","1. Lee Dong Wook \n"+"\n"+"Pria kelahiran 6 November 1981 ini telah banyak membintangi drama dan film. Berbagai karakter sudah pernah diperankannya termasuk karakter bad boy. Lee Dong Wook memerankan karakter bad boy dalam drama Tale of The Nine Taled yang tayang pada tahun 2020. Disini dia beradu akting dengan Joo Bo Ah dan Kim Bum.\n" +
//                "Drakor Tale of The Nine Taled, bercerita mengenai rubah ekor sembilan yang turun ke dunia manusia untuk memburu monster. Lee Yeon (Lee Dong Wook) yang merupakan rubah ekor sembilan bertemu dengan Nam Ji Ah (Jo Bo Ah). Dia dengan hati-hati mengamati Lee Yeon untuk membuktikan bahwa rubah ekor sembilan benar-benar ada.\n" +
//                "Lee Yeon awalnya memperlakukan Nam Ji Ah dengan kasar untuk menyembunyikan identitas aslinya. Ditambah dengan kepribadian dingin yang ditunjukkan oleh Lee Yeon dalam setiap aksinya membuat dia memenuhi syarat sebagai pesaing teratas dalam kategori bad boy.\n" +
//                "\n" + "\n" + "2. Ong Seong Woo \n" +"\n" + "Mantan member Wanna One ini telah membintangi beberapa drama sejak debut di dunia akting pada tahun 2019. Dia juga pernah memerankan karakter bad boy dalam drama More Than Friends. Drama bergenre komedi romantis ini bercerita mengenai hubungan friendzone yang berlangsung hingga satu dekade.\n" +
//                "Karakter Lee Soo yang diperanakan oleh Ong Seong Woo memiliki kepribadian yang dingin dan pendiam di sekolah. Meskipun begitu sebenarnya dia tampak seperti bad boy bagi orang-orang di sekitarnya. Fakta bahwa dia tidak bisa mengungkapkan perasaannya kepada Kyung Woo Yeon mungkin membuatnya semakin marah di samping permasalahan mengenai orang tuanya yang bercerai. Alih-alih menunjukkan cinta mendalam yang dia miliki untuk orang yang dia sukai dan untuk keluarganya, dia menutupinya dengan tampilan luar yang nakal. \n" + "\n" + "3. Woo Do Hwan \n" + "\n" + "Pria kelahiran tahun 1992 ini pernah membintangi drama Tempted bersama dengan Joy Red Velvet pada tahun 2018. Dalam drama tersebut tidak hanya kisah romantis remaja biasa yang akan ditemukan, tetapi juga cinta sejati yang diselingi dengan permainan cinta yang berbahaya.\n" +
//                "Berdasarkan film Hollywood Cruel Intentions, seri ini mengikuti kisah playboy Kwon Shi Hyun (Woo Do Hwan) dan teman-temannya yang kaya. Mereka memainkan rencana trik kejam pada rekannya yaitu Eun Tae Hee (Joy Red Velvet) yang nantinya akan dirayu, diajak berkencan, kemudian dicampakkan oleh Kwon Shi Hyun.\n" +
//                "Memiliki kepribadian yang sombong juga membuatnya menjadi kandidat terbaik dalam kategori bad boy. Tentu saja, dia memiliki latar belakang sedih tentang kematian ibunya yang semakin memperkuat karakternya dan membuat Kwon Shi Hyun menjadi salah satu karakter bad boy yang disalahpahami.\n" + "\n" + "4.Lee Joon Gi \n" + "\n" + "Lee Joon Gi merupakan aktor yang telah memulai kariernya sejak awal tahun 2000-an. Pada tahun 2016, dia membintangi drama kolosal Korea Selatan berjudul Scarlet Heart: Ryeo. Drama tersebut bercerita tentang seorang wanita modern abad ke-21 yang secara misterius kembali ke ribuan tahun silam di masa Dinasti Goryeo. Drama ini dibintangi oleh banyak pemain terkenal seperti IU, Kang Ha Neul, Nam Joo Hyuk, Ji Soo, hingga Baekhyun EXO.\n" +
//                "Lee Joon Gi memerankan karakter sebagai Wang So, dia adalah pangeran yang paling bermasalah. Wang So juga menderita cedera wajah dan menerima perlakuan buruk karena perbedaan fisiknya dari orang lain. Dia memakai topeng untuk menyembunyikan bekas lukanya dan menjadi dingin serta kejam seiring waktu. Meskipun begitu, sebenarnya Wang So memiliki hati yang hangat untuk Hae So (IU) sebagai wanita yang dicintainya.\n" ,"15","14 Oktober 2022","Sports"));
//    }
}