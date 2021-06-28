package com.djp.kpp.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.djp.kpp.Menu.CaptureData.CaptureData;

import com.djp.kpp.Menu.PenjaminKualitasData.PenjaminKualitasDataList;
import com.djp.kpp.Menu.RekamPenugasan.MenuRekamPenugasan;
import com.djp.kpp.Menu.SP2DK.SP2DK_List;
import com.djp.kpp.R;
import com.djp.kpp.Sumber;

public class Dashboard extends AppCompatActivity {
    TextView nama, nip, setting;
    private CardView rp, cd, pkd, sp,mon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String getnama = Sumber.getData("akun", "nama", getApplicationContext());
        String getnip = Sumber.getData("akun", "email", getApplicationContext());

        nama = findViewById(R.id.nama);
        nip = findViewById(R.id.nip);

        rp = findViewById(R.id.btn_rp);
        cd = findViewById(R.id.btn_cd);
        pkd = findViewById(R.id.btn_pkd);
        sp = findViewById(R.id.btn_sp);
        mon = findViewById(R.id.btn_mon);

        setting = findViewById(R.id.btn_setting);

        nama.setText(getnama);
        nip.setText(getnip);

        rp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, MenuRekamPenugasan.class));

            }
        });
        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, CaptureData.class));

            }
        });
        pkd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, PenjaminKualitasDataList.class));

            }
        });
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, SP2DK_List.class));

            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Monitoring.class));

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Profil.class));

            }
        });
    }
}