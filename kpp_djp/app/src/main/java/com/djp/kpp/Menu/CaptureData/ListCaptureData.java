package com.djp.kpp.Menu.CaptureData;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.djp.kpp.Koneksi;
import com.djp.kpp.Login;
import com.djp.kpp.Menu.Dashboard;
import com.djp.kpp.Menu.RekamPenugasan.MenuRekamPenugasan;
import com.djp.kpp.Models.CaptureDataModel;
import com.djp.kpp.R;
import com.djp.kpp.Sumber;
import com.djp.kpp.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.security.AccessController.getContext;

public class ListCaptureData extends AppCompatActivity {

    ListView list;
    ProgressBar progressBar;
    TextView kosong;
    List<CaptureDataModel> listdata = new ArrayList<>();
    ListCaptureDataAdapter adapter;
    String getToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_capture_data);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Capture Data");


        list = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);
        kosong = findViewById(R.id.kosong);

        getToken = Sumber.getData("akun", "token", getApplicationContext());

        getData();

    }


    private void getData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Koneksi.capture_data, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        progressBar.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);

                        try {

                            JSONArray jsonArray = response.getJSONArray("data");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < jsonArray.length(); i++) {

                                //getting the json object of the particular index inside the array
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                CaptureDataModel item = new CaptureDataModel();

                                item.setId(jsonObject.getString("id"));
                                item.setTanggal(jsonObject.getString("tanggal"));
                                item.setMerk(jsonObject.getString("merk"));
                                item.setNama_jalan(jsonObject.getString("nama_jalan"));
                                item.setKlu(jsonObject.getString("klu"));
                                item.setKegiatan(jsonObject.getString("kegiatan"));
                                item.setSituasi(jsonObject.getString("situasi"));
                                item.setKaryawan(jsonObject.getString("karyawan"));
                                item.setOmzet(jsonObject.getString("omzet"));
                                item.setVerifikasi(jsonObject.getString("verifikasi"));
                                item.setUUID(jsonObject.getString("UUID"));
                                item.setJumlah_foto(jsonObject.getString("jumlah_foto"));


                                listdata.add(item);

                            }

                            progressBar.setVisibility(View.GONE);

                            if (listdata.isEmpty()) {
                                kosong.setVisibility(View.VISIBLE);
                                kosong.setText("Data kosong");
                            }

                            adapter = new ListCaptureDataAdapter(getApplicationContext(), listdata);

                            list.setAdapter(adapter);

                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Intent in = new Intent(ListCaptureData.this, CaptureDataDetail.class);

                                    CaptureDataModel data = listdata.get(position);

                                    in.putExtra("data", "update");
                                    in.putExtra("id", String.valueOf(data.getId()));
                                    in.putExtra("tanggal", String.valueOf(data.getTanggal()));
                                    in.putExtra("merk", String.valueOf(data.getMerk()));
                                    in.putExtra("nama_jalan", String.valueOf(data.getNama_jalan()));
                                    in.putExtra("klu", String.valueOf(data.getKlu()));
                                    in.putExtra("kegiatan", String.valueOf(data.getKegiatan()));
                                    in.putExtra("situasi", String.valueOf(data.getSituasi()));
                                    in.putExtra("karyawan", String.valueOf(data.getKaryawan()));
                                    in.putExtra("omzet", String.valueOf(data.getOmzet()));
                                    in.putExtra("verifikasi", String.valueOf(data.getVerifikasi()));
                                    in.putExtra("UUID", String.valueOf(data.getUUID()));
                                    in.putExtra("jumlah_foto", String.valueOf(data.getJumlah_foto()));

                                    startActivity(in);

                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();

                            Log.e("error", "" + e);

                            progressBar.setVisibility(View.GONE);
                            kosong.setVisibility(View.VISIBLE);
                            kosong.setText("Data kosong");
//                            checkout.setVisibility(View.INVISIBLE);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("error", "" +error);

                        progressBar.setVisibility(View.GONE);

                        kosong.setVisibility(View.VISIBLE);
                        kosong.setText("Data Kosong");
//                        checkout.setVisibility(View.INVISIBLE);
//

                    }
                })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+ getToken);
                return params;
            }
        };

        //adding the string request to request queue
        Volley.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        //getData();
    }


}