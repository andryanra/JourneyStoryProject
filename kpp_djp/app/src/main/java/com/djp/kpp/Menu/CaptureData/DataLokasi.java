package com.djp.kpp.Menu.CaptureData;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.djp.kpp.Koneksi;
import com.djp.kpp.Login;
import com.djp.kpp.Menu.Dashboard;
import com.djp.kpp.Models.KecamatanModel;
import com.djp.kpp.Models.KelurahanModel;
import com.djp.kpp.R;
import com.djp.kpp.Sumber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.layout.simple_spinner_item;
import static java.security.AccessController.getContext;

public class DataLokasi extends AppCompatActivity {

    String getToken, sp_select_kec,sp_select_kel,nm_ar;
    EditText nama_jalan,rt,rw;

    Double lokasi_lat, lokasi_long;
    private Spinner spinner_kelurahan, spinner_kecamatan;
    ProgressBar progressBar;
    ProgressDialog pDialog;
    List<KecamatanModel> listkecamatan = new ArrayList<>();
    private ArrayList<String> name_kecamatan = new ArrayList<String>();
    List<KelurahanModel> listkelurahan = new ArrayList<>();
    private ArrayList<String> name_kelurahan = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_lokasi);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbarclose);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Input Detail Data Lokasi (1/5)");
        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progress);

        Intent bundle = getIntent();
        lokasi_lat = bundle.getDoubleExtra("lokasi_lat",0);
        lokasi_long = bundle.getDoubleExtra("lokasi_long",0);
        getToken = Sumber.getData("akun", "token", getApplicationContext());
        Log.e("lokasi_lat", "" + lokasi_lat);
        Log.e("lokasi_long", "" + lokasi_long);

        nama_jalan = findViewById(R.id.nama_jalan);
        rt = findViewById(R.id.rt);
        rw = findViewById(R.id.rw);

        spinner_kecamatan = findViewById(R.id.sp_kecamatan);
        get_kecamatan();
        spinner_kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String[] spinnerSplit = spinner_kecamatan.getSelectedItem().toString().split("-");
                sp_select_kec = spinnerSplit[0].trim();
                get_kelurahan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });


        spinner_kelurahan = findViewById(R.id.sp_kelurahan);
        spinner_kelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String[] spinnerSplit = spinner_kelurahan.getSelectedItem().toString().split("-");
                sp_select_kel = spinnerSplit[0].trim();

                for (int i = 0; i < listkelurahan.size(); i++) {
                    if(listkelurahan.get(i).getId().equals(sp_select_kel)) {
                        nm_ar =  listkelurahan.get(i).getAr();
                    }
                }
                TextView nama_ar = (TextView) findViewById(R.id.ar);
                nama_ar.setText(nm_ar);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }

        });




        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm_jln = nama_jalan.getText().toString();
                String getrt = rt.getText().toString();
                String getrw = rw.getText().toString();

                Intent in = new Intent(getApplicationContext(), DataPengamatan.class);

                in.putExtra("lokasi_lat", lokasi_lat);
                in.putExtra("lokasi_long", lokasi_long);
                in.putExtra("nama_jalan", nm_jln);
                in.putExtra("rt", getrt);
                in.putExtra("rw", getrw);
                in.putExtra("kec", sp_select_kec);
                in.putExtra("kel", sp_select_kel);
                startActivity(in);
//                Log.e("lokasi_lat", "" + lokasi_lat);
//                Log.e("lokasi_long", "" + lokasi_long);
//                Log.e("nm_jln", "" + nm_jln);
//                Log.e("getrt", "" + getrt);
//                Log.e("getrw", "" + getrw);
//                Log.e("kec", "" + sp_select_kec);
//                Log.e("kel", "" + sp_select_kel);
            }
        });
    }


    private void get_kelurahan() {
        listkelurahan.clear();
        name_kelurahan.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Koneksi.get_kelurahan_by_kec+"?id="+sp_select_kec, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Showing json data in log monitor
                        Log.e("response", "" + response);
                        Log.e("get", "" + Request.Method.GET);

                        progressBar.setVisibility(View.GONE);

                        try {

                            //we have the array named hero inside the object
                            //so here we are getting that json array

                            JSONArray jsonArray = response.getJSONArray("data");
                            //now looping through all the elements of the json array
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //creating a hero object and giving them the values from json object
                                KelurahanModel item = new KelurahanModel();

                                item.setId(jsonObject.getString("id"));
                                item.setNama(jsonObject.getString("nama"));
                                item.setAr(jsonObject.getString("ar"));
                                item.setKecamatan_id(jsonObject.getString("kecamatan_id"));
                                listkelurahan.add(item);
                            }
                            Log.e("Data", "" + jsonArray);
                            for (int i = 0; i < listkelurahan.size(); i++) {
                                name_kelurahan.add(listkelurahan.get(i).getId()+ " - " + listkelurahan.get(i).getNama());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DataLokasi.this, simple_spinner_item, name_kelurahan);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view\
                            spinnerArrayAdapter.setNotifyOnChange(true);
                            spinnerArrayAdapter.notifyDataSetChanged();
                            spinner_kelurahan.setAdapter(spinnerArrayAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DataLokasi.this, "Error " + e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", "" + error);
                        progressBar.setVisibility(View.GONE);
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
        com.djp.kpp.Volley.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void get_kecamatan() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Koneksi.get_kecamatan, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Showing json data in log monitor
                        Log.e("response", "" + response);
                        Log.e("get", "" + Request.Method.GET);

                        progressBar.setVisibility(View.GONE);

                        try {

                            //we have the array named hero inside the object
                            //so here we are getting that json array

                            JSONArray jsonArray = response.getJSONArray("data");
                            //now looping through all the elements of the json array
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //creating a hero object and giving them the values from json object
                                KecamatanModel item = new KecamatanModel();

                                item.setId(jsonObject.getString("id"));
                                item.setNama(jsonObject.getString("nama"));

                                listkecamatan.add(item);
                            }
                            Log.e("Data", "" + jsonArray);
                            for (int i = 0; i < listkecamatan.size(); i++) {
                                name_kecamatan.add(listkecamatan.get(i).getId()+ " - " + listkecamatan.get(i).getNama());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(DataLokasi.this, simple_spinner_item, name_kecamatan);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinner_kecamatan.setAdapter(spinnerArrayAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DataLokasi.this, "Error " + e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", "" + error);
                        progressBar.setVisibility(View.GONE);
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
        com.djp.kpp.Volley.getInstance().addToRequestQueue(jsonObjectRequest);

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