package com.djp.kpp.Menu.CaptureData;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.djp.kpp.R;

import java.util.Calendar;

public class PenerbitanSP2DK extends AppCompatActivity {

    Double lokasi_lat, lokasi_long;
    String nama_jalan, rt, rw, kel, kec;
    String klu, kegiatan, merk, situasi, karyawan, omzet;
    String npwp, nama_usaha, tgl_terdaftar_npwp, status_wp, status_pkp, tgl_terdaftar_pkp, tingkat_potensial;
    String sp2dk_nomor, sp2dk_tgl, sp2dk_respon;
    String komentar;

    EditText nomor_et, respon_et;
    TextView tanggal_et;
    private DatePickerDialog.OnDateSetListener mtgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penerbitan_sp2dk);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbarclose);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Penerbitan SP2DK (4/5)");
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

        nomor_et = findViewById(R.id.sp2dk_nomor);
        tanggal_et = findViewById(R.id.sp2dk_tanggal);
        respon_et = findViewById(R.id.sp2dk_respon);


        tanggal_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PenerbitanSP2DK.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mtgl,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mtgl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = day + "-" + month + "-" + year;
//                String date = month + "/" + day + "/" + year;
                Log.e("onDateSet: mm/dd/yyy:", "" + date);
                tanggal_et.setText(date);
            }
        };




        Button next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp2dk_nomor = nomor_et.getText().toString();
                sp2dk_tgl = tanggal_et.getText().toString();
                sp2dk_respon = respon_et.getText().toString();

//                Intent in = new Intent(getApplicationContext(), KomentarFotoObjek.class);
                Intent in = new Intent(getApplicationContext(), KomenFoto.class);
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
//                ================================
                in.putExtra("npwp", npwp);
                in.putExtra("nama_usaha", nama_usaha);
                in.putExtra("tgl_terdaftar_npwp", tgl_terdaftar_npwp);
                in.putExtra("tgl_terdaftar_pkp", tgl_terdaftar_pkp);
                in.putExtra("status_wp", status_wp);
                in.putExtra("status_pkp", status_pkp);
                in.putExtra("tingkat_potensial", tingkat_potensial);
                //                ================================
                in.putExtra("sp2dk_nomor", sp2dk_nomor);
                in.putExtra("sp2dk_tgl", sp2dk_tgl);
                in.putExtra("sp2dk_respon", sp2dk_respon);
                startActivity(in);

                Log.e("PenerbitanSP2DK - 1", "====" );
                Log.e("lokasi_lat", "" + lokasi_lat);
                Log.e("lokasi_long", "" + lokasi_long);
                Log.e("nm_jln", "" + nama_jalan);
                Log.e("getrt", "" + rt);
                Log.e("getrw", "" + rw);
                Log.e("kec", "" + kec);
                Log.e("kel", "" + kel);
                Log.e("=================== - 2", "===" );
                Log.e("klu", "" + klu);
                Log.e("situasi", "" + situasi);
                Log.e("karyawan", "" + karyawan);
                Log.e("omzet", "" + omzet);
                Log.e("kegiatan", "" + kegiatan);
                Log.e("merk", "" + merk);
                Log.e("=================== - 3", "===" );
                Log.e("npwp", "" + npwp);
                Log.e("nama_usaha", "" + nama_usaha);
                Log.e("tgl_terdaftar_npwp", "" + tgl_terdaftar_npwp);
                Log.e("tgl_terdaftar_pkp", "" + tgl_terdaftar_pkp);
                Log.e("status_wp", "" + status_wp);
                Log.e("status_pkp", "" + status_pkp);
                Log.e("tingkat_potensial", "" + tingkat_potensial);
                Log.e("=================== - 4", "===" );
                Log.e("sp2dk_nomor", "" + sp2dk_nomor);
                Log.e("sp2dk_tgl", "" + sp2dk_tgl);
                Log.e("sp2dk_respon", "" + sp2dk_respon);

            }
        });


    }
}