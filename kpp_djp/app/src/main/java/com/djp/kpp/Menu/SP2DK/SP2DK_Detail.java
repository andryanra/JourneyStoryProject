package com.djp.kpp.Menu.SP2DK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.djp.kpp.R;

public class SP2DK_Detail extends AppCompatActivity {

    TextView tanggal, npwp, ar, sp2dk_nomor, sp2dk_potensi, sp2dk_pembayaran, sp2dk_tahun_pajak, sp2dk_jenis_pajak, sp2dk_masa_pajak;

    String gettanggal, getnpwp, getar, getsp2dk_nomor, getsp2dk_potensi, getsp2dk_pembayaran, getsp2dk_tahun_pajak, getsp2dk_jenis_pajak, getsp2dk_masa_pajak;
    Button update;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp2dk_detail);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView textToolbar = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        Button kembali = (Button) getSupportActionBar().getCustomView().findViewById(R.id.kembali);

        textToolbar.setText("Detail SP2DK");

        tanggal = findViewById(R.id.sp2dk_tgl);
        npwp = findViewById(R.id.npwp);
        ar = findViewById(R.id.ar);
        sp2dk_nomor= findViewById(R.id.sp2dk_nomor);
        sp2dk_potensi = findViewById(R.id.sp2dk_potensi);
        sp2dk_pembayaran = findViewById(R.id.sp2dk_pembayaran);
        sp2dk_tahun_pajak = findViewById(R.id.sp2dk_tahun_pajak);
        sp2dk_jenis_pajak = findViewById(R.id.sp2dk_jenis_pajak);
        sp2dk_masa_pajak = findViewById(R.id.sp2dk_masa_pajak);

        Intent data = getIntent();

        gettanggal = data.getStringExtra("tanggal");
        getnpwp = data.getStringExtra("npwp");
        getar   = data.getStringExtra("ar");
        getsp2dk_nomor = data.getStringExtra("sp2dk_nomor");
        getsp2dk_potensi = data.getStringExtra("sp2dk_potensi");
        getsp2dk_pembayaran= data.getStringExtra("sp2dk_pembayaran");
        getsp2dk_tahun_pajak= data.getStringExtra("sp2dk_tahun_pajak");
        getsp2dk_jenis_pajak= data.getStringExtra("sp2dk_jenis_pajak");
        getsp2dk_masa_pajak= data.getStringExtra("sp2dk_masa_pajak");

        tanggal.setText(gettanggal);
        npwp.setText(getnpwp);
        ar.setText(getar);
        sp2dk_nomor.setText(getsp2dk_nomor);
        sp2dk_potensi.setText(getsp2dk_potensi);
        sp2dk_pembayaran.setText(getsp2dk_pembayaran);
        sp2dk_tahun_pajak.setText(getsp2dk_tahun_pajak);
        sp2dk_jenis_pajak.setText(getsp2dk_jenis_pajak);
        sp2dk_masa_pajak.setText(getsp2dk_masa_pajak);
    }
}