package com.djp.kpp.Menu.DataMonitoring;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.djp.kpp.Models.MonitoringModel;
import com.djp.kpp.R;

import java.util.List;

public class MKinerjaIndividuAdapter extends ArrayAdapter<MonitoringModel> {

    Context context;
    List<MonitoringModel> list;


    public MKinerjaIndividuAdapter(Context context, List<MonitoringModel> objects) {

        super(context, R.layout.row_m_kinerja_individu, objects);
        this.context = context;
        list = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int getnomor = position + 1;

        if (convertView == null) {

            LayoutInflater layoutInflater;

            layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.row_m_kinerja_individu, null);

        }

        View v = convertView;

        final MonitoringModel data = list.get(position);

        if (data != null) {

            TextView nama = (TextView) v.findViewById(R.id.nama);
            TextView jumlah_tagging = (TextView) v.findViewById(R.id.jumlah_tagging);
            TextView sudah = (TextView) v.findViewById(R.id.sudah);
            TextView belum = (TextView) v.findViewById(R.id.belum);
            TextView pembayaran = (TextView) v.findViewById(R.id.pembayaran);
            TextView sisa_potensi = (TextView) v.findViewById(R.id.sisa_potensi);

            nama.setText(String.valueOf(data.getNama()));
            jumlah_tagging.setText(String.valueOf(data.getJumlah_tagging()));
            sudah.setText(String.valueOf(data.getSudah()));
            belum.setText(String.valueOf(data.getBelum()));
            pembayaran.setText(String.valueOf(data.getPembayaran()));
            sisa_potensi.setText(String.valueOf(data.getSisa_potensi()));

        }

        return v;

    }
}