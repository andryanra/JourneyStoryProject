package com.djp.kpp.Menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.djp.kpp.Login;
import com.djp.kpp.R;
import com.djp.kpp.Sumber;

public class Profil extends AppCompatActivity {

    String get_nost, get_userid, tanggal,tanggal_view, getToken;
    Button logout, update;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Profil");


        get_userid = Sumber.getData("akun", "id", getApplicationContext());
        getToken = Sumber.getData("akun", "token", getApplicationContext());
        String ip = Sumber.getData("akun", "ip", getApplicationContext());
        String nip = Sumber.getData("akun", "nip", getApplicationContext());
        String nama = Sumber.getData("akun", "nama", getApplicationContext());
        String jabatan = Sumber.getData("akun", "jabatan", getApplicationContext());
        String seksi = Sumber.getData("akun", "nama_seksi", getApplicationContext());
        String kec = Sumber.getData("akun", "kec", getApplicationContext());
        String kel = Sumber.getData("akun", "kel", getApplicationContext());

        TextView ip_v = findViewById(R.id.ip);
        TextView nip_v = findViewById(R.id.nip);
        TextView nama_v = findViewById(R.id.nama);
        TextView jabatan_v = findViewById(R.id.jabatan);
        TextView seksi_v = findViewById(R.id.seksi);
        TextView kec_v = findViewById(R.id.kecamatan);
        TextView kel_v = findViewById(R.id.kelurahan);

        ip_v.setText(ip);
        nip_v.setText(nip);
        nama_v.setText(nama);
        jabatan_v.setText(jabatan);
        seksi_v.setText(seksi);
        kec_v.setText(kec);
        kel_v.setText(kel);

        logout = findViewById(R.id.logout);
        update  = findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Profil.this, UbahPassword.class);
                startActivity(in);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sumber.deleteData("akun", getApplicationContext());
                Intent in = new Intent(Profil.this, Login.class);
                startActivity(in);
            }
        });
    }
}