package com.djp.kpp.Menu.SP2DK;

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

public class SP2DK_Adapter extends ArrayAdapter<CaptureDataModel> {

    

    Context context;
    List<CaptureDataModel> list;


    public SP2DK_Adapter(Context context, List<CaptureDataModel> objects) {

        super(context, R.layout.row_sp2dk_adapter, objects);
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
            convertView = layoutInflater.inflate(R.layout.row_sp2dk_adapter, null);

        }

        View v = convertView;

        final CaptureDataModel data = list.get(position);

        if (data != null) {

            TextView nomor = (TextView) v.findViewById(R.id.nomor);
            TextView npwp = (TextView) v.findViewById(R.id.npwp);
            TextView tanggal = (TextView) v.findViewById(R.id.tanggal);

            nomor.setText(String.valueOf(getnomor) +".");
            tanggal.setText(String.valueOf(data.getTanggal()));
            npwp.setText(data.getNpwp());

        }

        return v;

    }
}