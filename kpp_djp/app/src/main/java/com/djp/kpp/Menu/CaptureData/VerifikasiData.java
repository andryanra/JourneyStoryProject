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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.djp.kpp.R;

import java.util.Calendar;

public class VerifikasiData extends AppCompatActivity {

    Double lokasi_lat, lokasi_long;
    String nama_jalan, rt, rw, kel, kec;
    String klu, kegiatan, merk, situasi, karyawan, omzet;
    String npwp, nama_usaha, tgl_terdaftar_npwp, status_wp, status_pkp, tgl_terdaftar_pkp, tingkat_potensial;
    String nomor_sp2sk, tgl_sp2dk, respon;
    String komentar;

    EditText npwp_et, nama_usaha_et, tingkat_potensial_et;
    TextView tgl_terdaftar_npwp_et,tgl_terdaftar_pkp_et;
    Spinner sp_klu,sp_situasi_objek,sp_jumlah_karyawan,sp_omzet, sp_potensial;

    private DatePickerDialog.OnDateSetListener mtgl_terdaftar_npwp_et, mtgl_terdaftar_pkp_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_data);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbarclose);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Verifikasi Data (3/5)");
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

        npwp_et = findViewById(R.id.npwp);
        nama_usaha_et =findViewById(R.id.nama_usaha);
        tgl_terdaftar_npwp_et = findViewById(R.id.tgl_terdaftar_npwp);
        tgl_terdaftar_pkp_et = findViewById(R.id.tgl_terdaftar_pkp);


        tgl_terdaftar_npwp_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        VerifikasiData.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mtgl_terdaftar_npwp_et,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mtgl_terdaftar_npwp_et = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = day + "-" + month + "-" + year;
//                String date = month + "/" + day + "/" + year;
                Log.e("onDateSet: mm/dd/yyy:", "" + date);
                tgl_terdaftar_npwp_et.setText(date);
            }
        };

        tgl_terdaftar_pkp_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        VerifikasiData.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mtgl_terdaftar_pkp_et,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mtgl_terdaftar_pkp_et = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = day + "-" + month + "-" + year;
//                String date = month + "/" + day + "/" + year;
                Log.e("onDateSet: mm/dd/yyy:", "" + date);
                tgl_terdaftar_pkp_et.setText(date);
            }
        };

        final Spinner sp_wp = (Spinner) findViewById(R.id.sp_wp);
        ArrayAdapter<CharSequence> wp_adapter = ArrayAdapter.createFromResource(this,
                R.array.wp, android.R.layout.simple_spinner_item);
        wp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_wp.setAdapter(wp_adapter);

        final Spinner sp_pkp = (Spinner) findViewById(R.id.sp_pkp);
        ArrayAdapter<CharSequence> pkp_adapter = ArrayAdapter.createFromResource(this,
                R.array.pkp, android.R.layout.simple_spinner_item);
        pkp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_pkp.setAdapter(pkp_adapter);

        final Spinner sp_potensial = (Spinner) findViewById(R.id.sp_potensial);
        ArrayAdapter<CharSequence> potensial_adapter = ArrayAdapter.createFromResource(this,
                R.array.potensial, android.R.layout.simple_spinner_item);
        potensial_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_potensial.setAdapter(potensial_adapter);


        Button next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npwp = npwp_et.getText().toString();
                nama_usaha = nama_usaha_et.getText().toString();
                tgl_terdaftar_npwp = tgl_terdaftar_npwp_et.getText().toString();
                tgl_terdaftar_pkp = tgl_terdaftar_pkp_et.getText().toString();
                status_wp = sp_wp.getSelectedItem().toString();
                status_pkp = sp_pkp.getSelectedItem().toString();
                tingkat_potensial = sp_pkp.getSelectedItem().toString();

//                Log.e("VERIFIKASI DATA", "====" );
//                Log.e("npwp", "" + npwp);
//                Log.e("nama_usaha", "" + nama_usaha);
//                Log.e("tgl_terdaftar_npwp", "" + tgl_terdaftar_npwp);
//                Log.e("tgl_terdaftar_pkp", "" + tgl_terdaftar_pkp);
//                Log.e("status_wp", "" + status_wp);
//                Log.e("status_pkp", "" + status_pkp);
//                Log.e("tingkat_potensial", "" + tingkat_potensial);

                Intent in = new Intent(getApplicationContext(), PenerbitanSP2DK.class);
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
                startActivity(in);
            }
        });


    }
}