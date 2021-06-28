package com.djp.kpp.Menu.CaptureData;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.djp.kpp.Menu.Profil;
import com.djp.kpp.Menu.UbahPassword;
import com.djp.kpp.R;

public class CaptureDataDetail extends AppCompatActivity {

    TextView tanggal, merk, nama_jalan, klu, kegiatan, situasi, karyawan, omzet, verifikasi;

    String gettanggal, getmerk, getnama_jalan  ,getklu  ,getkegiatan  ,getsituasi  ,getkaryawan  ,getomzet, getverifikasi, getuuid, getjumlah_foto;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_data_detail);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView textToolbar = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        textToolbar.setText("Detail Capture Data");


        tanggal = findViewById(R.id.tanggal);
        merk = findViewById(R.id.merk);
        nama_jalan= findViewById(R.id.nama_jalan);
        klu = findViewById(R.id.klu);
        kegiatan = findViewById(R.id.kegiatan);
        situasi = findViewById(R.id.situasi);
        karyawan = findViewById(R.id.karyawan);
        omzet = findViewById(R.id.omzet);
        verifikasi = findViewById(R.id.verifikasi);

        Intent data = getIntent();

        gettanggal = data.getStringExtra("tanggal");
        getmerk = data.getStringExtra("merk");
        getnama_jalan = data.getStringExtra("nama_jalan");
        getklu= data.getStringExtra("klu");
        getkegiatan= data.getStringExtra("kegiatan");
        getsituasi= data.getStringExtra("situasi");
        getkaryawan= data.getStringExtra("karyawan");
        getomzet= data.getStringExtra("omzet");
        getuuid = data.getStringExtra("UUID");
        getjumlah_foto = data.getStringExtra("jumlah_foto");

        if(data.getStringExtra("verifikasi").equals("1")) {
            getverifikasi = "Sudah Diverifikasi";
        } else {
            getverifikasi = "Belum Diverifikasi";
        }

        tanggal.setText(gettanggal);
        merk.setText(getmerk);
        nama_jalan.setText(getnama_jalan);
        klu.setText(getklu);
        kegiatan.setText(getkegiatan);
        situasi.setText(getsituasi);
        karyawan.setText(getkaryawan);
        omzet.setText(getomzet);
        verifikasi.setText(getverifikasi);


        Button next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("UUID = ", "" + getuuid);
                Log.e("JUMLAH_FOTO = ", "" + getjumlah_foto);
                Intent in = new Intent(CaptureDataDetail.this, TambahFotoKomen.class);
                in.putExtra("UUID", getuuid);
                in.putExtra("jumlah_foto", getjumlah_foto);

                startActivity(in);
            }
        });

    }
}