package com.untad.ferin.spkwpprodukjual.data;

public class Alternative {
    public String nama;
    public String modal;
    public int etalase;
    public String umur_simpan;
    public int laba;
    public int tren;

    public Alternative() {
    }

    public Alternative(String nama, String modal, int etalase, String umur_simpan, int laba, int tren) {
        this.nama = nama;
        this.modal = modal;
        this.etalase = etalase;
        this.umur_simpan = umur_simpan;
        this.laba = laba;
        this.tren = tren;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public int getEtalase() {
        return etalase;
    }

    public void setEtalase(int etalase) {
        this.etalase = etalase;
    }

    public String getUmur_simpan() {
        return umur_simpan;
    }

    public void setUmur_simpan(String umur_simpan) {
        this.umur_simpan = umur_simpan;
    }

    public int getLaba() {
        return laba;
    }

    public void setLaba(int laba) {
        this.laba = laba;
    }

    public int getTren() {
        return tren;
    }

    public void setTren(int tren) {
        this.tren = tren;
    }
}
