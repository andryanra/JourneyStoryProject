package com.djp.kpp.Menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.djp.kpp.Menu.RekamPenugasan.MenuRekamPenugasan;
import com.djp.kpp.R;
import com.djp.kpp.Sumber;
import com.djp.kpp.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UbahPassword extends AppCompatActivity {

    String get_password, get_userid, tanggal, tanggal_view, getToken;
    Button simpan;
    EditText password;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Ubah Password");

        pDialog = new ProgressDialog(UbahPassword.this);
        pDialog.setCancelable(false);

        get_userid = Sumber.getData("akun", "id", getApplicationContext());
        getToken = Sumber.getData("akun", "token", getApplicationContext());
        String ip = Sumber.getData("akun", "ip", getApplicationContext());
        String nama = Sumber.getData("akun", "nama", getApplicationContext());


        TextView ip_v = findViewById(R.id.ip);
        TextView nama_v = findViewById(R.id.nama);


        ip_v.setText(ip);
        nama_v.setText(nama);

        password = findViewById(R.id.password);

        simpan = findViewById(R.id.simpan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_password = password.getText().toString();
                if (get_password.equals("") || get_password.equals("null")) {
                    Sumber.toastShow(getApplicationContext(), "Harap isi password baru");
                } else {
                    save();
                }
            }
        });
    }


    private void save() {
        get_password = password.getText().toString();
        pDialog.setMessage("Uploading ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Koneksi.update_profil,
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
                                Sumber.deleteData("akun", getApplicationContext());
                                Intent intent = new Intent(UbahPassword.this, Login.class);
                                startActivity(intent);
                            }
                            Sumber.toastShow(getApplicationContext(), "Silahkan Login Kembali");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("error", "" + e);
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
                params.put("password", get_password);
                params.put("user_id", get_userid);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + getToken);
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