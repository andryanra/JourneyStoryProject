package com.djp.kpp.Menu.PenjaminKualitasData;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.djp.kpp.R;

public class PenjaminKualitasDataDetail extends AppCompatActivity {

    TextView tanggal, npwp,merk, nama_jalan, klu, kegiatan, situasi, karyawan, omzet;

    String gettanggal, getnpwp,getmerk, getnama_jalan  ,getklu  ,getkegiatan  ,getsituasi  ,getkaryawan  ,getomzet;
    Button update;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjamin_kualitas_data_detail);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView textToolbar = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        Button kembali = (Button) getSupportActionBar().getCustomView().findViewById(R.id.kembali);

        textToolbar.setText("Detail Penjamin Kualitas Data");

        tanggal = findViewById(R.id.tanggal);
        npwp = findViewById(R.id.npwp);
        merk = findViewById(R.id.merk);
        nama_jalan= findViewById(R.id.nama_jalan);
        klu = findViewById(R.id.klu);
        kegiatan = findViewById(R.id.kegiatan);
        situasi = findViewById(R.id.situasi);
        karyawan = findViewById(R.id.karyawan);
        omzet = findViewById(R.id.omzet);

        Intent data = getIntent();

        gettanggal = data.getStringExtra("tanggal");
        getnpwp = data.getStringExtra("npwp");
        getmerk = data.getStringExtra("merk");
        getnama_jalan = data.getStringExtra("nama_jalan");
        getklu= data.getStringExtra("klu");
        getkegiatan= data.getStringExtra("kegiatan");
        getsituasi= data.getStringExtra("situasi");
        getkaryawan= data.getStringExtra("karyawan");
        getomzet= data.getStringExtra("omzet");

        tanggal.setText(gettanggal);
        npwp.setText(getnpwp);
        merk.setText(getmerk);
        nama_jalan.setText(getnama_jalan);
        klu.setText(getklu);
        kegiatan.setText(getkegiatan);
        situasi.setText(getsituasi);
        karyawan.setText(getkaryawan);
        omzet.setText(getomzet);
    }
}