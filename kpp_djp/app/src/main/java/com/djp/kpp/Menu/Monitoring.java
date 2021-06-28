package com.djp.kpp.Menu;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.djp.kpp.Menu.CaptureData.CaptureData;
import com.djp.kpp.Menu.CaptureData.ListCaptureData;
import com.djp.kpp.Menu.CaptureData.MapLocation;
import com.djp.kpp.Menu.DataMonitoring.MDataPengamatan;
import com.djp.kpp.Menu.DataMonitoring.MKinerjaIndividu;
import com.djp.kpp.Menu.DataMonitoring.MTL_PKD;
import com.djp.kpp.Menu.DataMonitoring.MTL_SP2DK;
import com.djp.kpp.R;

public class Monitoring extends AppCompatActivity {

    private CardView pengamatan_v, pkd_v, sp2dk_v, kinerja_v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbarclose);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Monitoring");
        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.INVISIBLE);

        pengamatan_v = findViewById(R.id.pengamatan);
        pkd_v = findViewById(R.id.pkd);
        sp2dk_v = findViewById(R.id.sp2dk);
        kinerja_v = findViewById(R.id.kinerja);


        pengamatan_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Monitoring.this, MDataPengamatan.class));

            }
        });

        pkd_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Monitoring.this, MTL_PKD.class));

            }
        });

        sp2dk_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Monitoring.this, MTL_SP2DK.class));

            }
        });

        kinerja_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Monitoring.this, MKinerjaIndividu.class));

            }
        });
    }
}