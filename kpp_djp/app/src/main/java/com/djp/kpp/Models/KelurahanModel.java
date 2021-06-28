package com.djp.kpp.Models;

public class KelurahanModel {
    private String id;
    private String nama;

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

    private String ar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKecamatan_id() {
        return kecamatan_id;
    }

    public void setKecamatan_id(String kecamatan_id) {
        this.kecamatan_id = kecamatan_id;
    }

    private String kecamatan_id;
}
