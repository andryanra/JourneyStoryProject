package com.djp.kpp.Menu.CaptureData;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.djp.kpp.Menu.Dashboard;
import com.djp.kpp.R;

public class KomentarFotoObjek extends AppCompatActivity {

    Double lokasi_lat, lokasi_long;
    String nama_jalan, rt, rw, kel, kec;
    String klu, kegiatan, merk, situasi, karyawan, omzet;
    String npwp, nama_usaha, tgl_terdaftar_npwp, status_wp, status_pkp, tgl_terdaftar_pkp, tingkat_potensial;
    String sp2dk_nomor, sp2dk_tgl, sp2dk_respon;
    String komentar;

    EditText komentar_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentar_foto_objek);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbarclose);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Komentar dan Foto Objek (5/5)");
        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.INVISIBLE);

        Intent bundle = getIntent();
        lokasi_lat = bundle.getDoubleExtra("lokasi_lat",0);
        lokasi_long = bundle.getDoubleExtra("lokasi_long",0);
        nama_jalan = bundle.getStringExtra("nama_jalan");
        rt = bundle.getStringExtra("rt");
        rw = bundle.getStringExtra("rw");
        kec = bundle.getStringExtra("kec");
        kel = bundle.getStringExtra("kel");
//        ===============================
        klu = bundle.getStringExtra("klu");
        kegiatan = bundle.getStringExtra("kegiatan");
        merk = bundle.getStringExtra("merk");
        situasi = bundle.getStringExtra("situasi");
        karyawan = bundle.getStringExtra("karyawan");
        omzet = bundle.getStringExtra("omzet");
//        ===============================
        npwp = bundle.getStringExtra("npwp");
        nama_usaha = bundle.getStringExtra("nama_usaha");
        tgl_terdaftar_npwp = bundle.getStringExtra("tgl_terdaftar_npwp");
        status_wp = bundle.getStringExtra("status_wp");
        status_pkp = bundle.getStringExtra("status_pkp");
        tgl_terdaftar_pkp = bundle.getStringExtra("tgl_terdaftar_pkp");
        tingkat_potensial = bundle.getStringExtra("tingkat_potensial");
        //        ===============================
        sp2dk_nomor = bundle.getStringExtra("sp2dk_nomor");
        sp2dk_tgl = bundle.getStringExtra("sp2dk_tgl");
        sp2dk_respon = bundle.getStringExtra("sp2dk_respon");

        komentar_et = findViewById(R.id.komentar);



        Button next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                komentar = komentar_et.getText().toString();

            }
        });
    }
}