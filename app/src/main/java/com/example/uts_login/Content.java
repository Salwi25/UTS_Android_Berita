package com.example.uts_login;

public class Content {
    private String judul;
    private String konten;
    private String minUmur;
    private String tanggalRilis;
    private String kategori;

    public Content(String judul, String konten, String minUmur, String tanggalRilis, String kategori) {
        this.judul = judul;
        this.konten = konten;
        this.minUmur = minUmur;
        this.tanggalRilis = tanggalRilis;
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getMinUmur() {
        return minUmur;
    }

    public void setMinUmur(String minUmur) {
        this.minUmur = minUmur;
    }

    public String getTanggalRilis() {
        return tanggalRilis;
    }

    public void setTanggalRilis(String tanggalRilis) {
        this.tanggalRilis = tanggalRilis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

}
