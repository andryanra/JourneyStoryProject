package com.djp.kpp.Menu.CaptureData;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.djp.kpp.R;

public class DataPengamatan extends AppCompatActivity {

    Double lokasi_lat, lokasi_long;
    String nama_jalan, rt, rw, kel, kec;
    String klu, kegiatan, merk, situasi, karyawan, omzet;
    String npwp, nama_usaha, tgl_terdaftar_npwp, status_wp, status_pkp, tgl_terdaftar_pkp, tingkat_potensial;
    String nomor_sp2sk, tgl_sp2dk, respon;
    String komentar;

    EditText kegiatan_usaha,merk_usaha;
    Spinner sp_klu,sp_situasi_objek,sp_jumlah_karyawan,sp_omzet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengamatan);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbarclose);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Input Data Pengamatan (2/5)");
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

        kegiatan_usaha = findViewById(R.id.kegiatan_usaha);
        merk_usaha = findViewById(R.id.merk);

        sp_klu = (Spinner) findViewById(R.id.sp_klu);
        ArrayAdapter<CharSequence> klu_adapter = ArrayAdapter.createFromResource(this,
                R.array.nama_klu, android.R.layout.simple_spinner_item);
        klu_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_klu.setAdapter(klu_adapter);

        sp_situasi_objek = (Spinner) findViewById(R.id.sp_situasi);
        ArrayAdapter<CharSequence> situasi_adapter = ArrayAdapter.createFromResource(this,
                R.array.situasi_objek, android.R.layout.simple_spinner_item);
        situasi_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_situasi_objek.setAdapter(situasi_adapter);

        sp_jumlah_karyawan = (Spinner) findViewById(R.id.sp_jumlah_karyawan);
        ArrayAdapter<CharSequence> karyawan_adapter = ArrayAdapter.createFromResource(this,
                R.array.jml_karyawan, android.R.layout.simple_spinner_item);
        karyawan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_jumlah_karyawan.setAdapter(karyawan_adapter);

        sp_omzet = (Spinner) findViewById(R.id.sp_omzet);
        ArrayAdapter<CharSequence> omzet_adapter = ArrayAdapter.createFromResource(this,
                R.array.omzet, android.R.layout.simple_spinner_item);
        omzet_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_omzet.setAdapter(omzet_adapter);

        Button next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klu = sp_klu.getSelectedItem().toString();
                situasi = sp_situasi_objek.getSelectedItem().toString();
                karyawan = sp_jumlah_karyawan.getSelectedItem().toString();
                omzet = sp_omzet.getSelectedItem().toString();
                kegiatan = kegiatan_usaha.getText().toString();
                merk = merk_usaha.getText().toString();

//                Log.e("lokasi_lat", "" + lokasi_lat);
//                Log.e("lokasi_long", "" + lokasi_long);
//                Log.e("nm_jln", "" + nama_jalan);
//                Log.e("getrt", "" + rt);
//                Log.e("getrw", "" + rw);
//                Log.e("kec", "" + kec);
//                Log.e("kel", "" + kel);
//                Log.e("klu", "" + klu);
//                Log.e("kegiatan", "" + kel);
//                Log.e("merk", "" + kel);
//                Log.e("situasi", "" + kel);
//                Log.e("karyawan", "" + kel);
//                Log.e("omzet", "" + kel);
//                Log.e("=======================", "===" );
//                Log.e("klu", "" + klu);
//                Log.e("situasi", "" + situasi);
//                Log.e("karyawan", "" + karyawan);
//                Log.e("omzet", "" + omzet);
//                Log.e("kegiatan", "" + kegiatan);
//                Log.e("merk", "" + merk);

                Intent in = new Intent(getApplicationContext(), VerifikasiData.class);
                in.putExtra("lokasi_lat", lokasi_lat);
                in.putExtra("lokasi_long", lokasi_long);
                in.putExtra("nama_jalan", nama_jalan);
                in.putExtra("rt", rt);
                in.putExtra("rw", rw);
                in.putExtra("kec", kec);
                in.putExtra("kel", kel);
//                ===============================
                in.putExtra("klu", klu);
                in.putExtra("kegiatan", kegiatan);
                in.putExtra("merk", merk);
                in.putExtra("situasi", situasi);
                in.putExtra("karyawan", karyawan);
                in.putExtra("omzet", omzet);
                startActivity(in);
            }
        });
    }


}