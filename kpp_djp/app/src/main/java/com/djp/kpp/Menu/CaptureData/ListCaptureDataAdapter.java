package com.djp.kpp.Menu.CaptureData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.djp.kpp.Models.CaptureDataModel;
import com.djp.kpp.R;

import java.util.List;

public class ListCaptureDataAdapter extends ArrayAdapter<CaptureDataModel> {

    Context context;
    List<CaptureDataModel> list;


    public ListCaptureDataAdapter(Context context, List<CaptureDataModel> objects) {

        super(context, R.layout.row_capture_data, objects);
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
            convertView = layoutInflater.inflate(R.layout.row_capture_data, null);

        }

        View v = convertView;

        final CaptureDataModel data = list.get(position);

        if (data != null) {

            TextView merk = (TextView) v.findViewById(R.id.merk);
            TextView nomor = (TextView) v.findViewById(R.id.nomor);
            TextView tanggal = (TextView) v.findViewById(R.id.tanggal);

            tanggal.setText(String.valueOf(data.getTanggal()));
            nomor.setText(String.valueOf(getnomor) +".");
            merk.setText(data.getMerk());

        }

        return v;

    }
}