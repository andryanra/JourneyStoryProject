package com.djp.kpp.Menu.RekamPenugasan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.djp.kpp.Koneksi;
import com.djp.kpp.Login;
import com.djp.kpp.Menu.Dashboard;
import com.djp.kpp.R;
import com.djp.kpp.Sumber;
import com.djp.kpp.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.djp.kpp.Koneksi.login;

public class MenuRekamPenugasan extends AppCompatActivity {

    EditText nost;
    String get_nost, get_userid, tanggal,tanggal_view, getToken;
    Button simpan;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rekam_penugasan);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Rekam Penugasan");
        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);

        pDialog = new ProgressDialog(MenuRekamPenugasan.this);
        pDialog.setCancelable(false);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat view = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        tanggal = tgl.format(c);
        tanggal_view = view.format(c);
        get_userid = Sumber.getData("akun", "id", getApplicationContext());
        getToken = Sumber.getData("akun", "token", getApplicationContext());
        String ip = Sumber.getData("akun", "ip", getApplicationContext());
        String nama = Sumber.getData("akun", "nama", getApplicationContext());
        String seksi = Sumber.getData("akun", "nama_seksi", getApplicationContext());
        String kec = Sumber.getData("akun", "kec", getApplicationContext());
        String kel = Sumber.getData("akun", "kel", getApplicationContext());

        TextView tanggal_v = findViewById(R.id.tanggal);
        TextView ip_v = findViewById(R.id.ip);
        TextView nama_v = findViewById(R.id.nama);
        TextView seksi_v = findViewById(R.id.seksi);
        TextView kec_v = findViewById(R.id.kecamatan);
        TextView kel_v = findViewById(R.id.kelurahan);
        tanggal_v.setText(tanggal_view);
        ip_v.setText(ip);
        nama_v.setText(nama);
        seksi_v.setText(seksi);
        kec_v.setText(kec);
        kel_v.setText(kel);

        nost = findViewById(R.id.no_st);

        simpan = findViewById(R.id.simpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_nost = nost.getText().toString();
                if (get_nost.equals("") || get_nost.equals("null")) {
                    Sumber.toastShow(getApplicationContext(), "Harap isi secara lengkap");
                } else {
                    rekampenugasan_sava();
                }
            }
        });
    }

    private void rekampenugasan_sava() {
        get_nost = nost.getText().toString();
        pDialog.setMessage("Uploading ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Koneksi.rekam_penugasan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server

                        Log.e("response", " " + response);

                        try {

                            JSONObject data = new JSONObject(response);
                            String status = data.getString("status");
                            Log.e("status", " " + status);

                            if (status.equals("success")) { //Sukses
                                Intent intent = new Intent(MenuRekamPenugasan.this, Dashboard.class);
                                startActivity(intent);
                            }
                            Sumber.toastShow(getApplicationContext(), "Berhasil Simpan Data");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("error", "" +e );
                            Sumber.toastShow(getApplicationContext(), "Gagal Simpan");

                        }

                        hideDialog();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        Log.e("error", "" + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tanggal", tanggal);
                params.put("no_st", get_nost);
                params.put("user_id", get_userid);

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+ getToken);
                return params;
            }
        };

        Volley.getInstance().addToRequestQueue(stringRequest);
    }

    private void showDialog() {

        if (!pDialog.isShowing())
            pDialog.show();

    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}