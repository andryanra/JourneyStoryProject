package com.djp.kpp.Menu.DataMonitoring;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.djp.kpp.Models.MonitoringModel;
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

import static java.sql.Types.NULL;

public class MTL_SP2DK extends AppCompatActivity {

    ListView list;
    ProgressBar progressBar;
    TextView kosong;
    List<MonitoringModel> listdata = new ArrayList<>();
    MTL_SP2DK_Adapter adapter;
    String getToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_tl_sp2dk);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbarclose);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Tindak Lanjut SP2DK");
        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.INVISIBLE);


        list = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);
        kosong = findViewById(R.id.kosong);


        getToken = Sumber.getData("akun", "token", getApplicationContext());

        getData();

    }

    private void getData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Koneksi.monitoring_tindak_lanjut_sp2dk, null,
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
                                String pembayaran_new = jsonObject.getString("pembayaran");
                                String sisa_potensi_new = jsonObject.getString("sisa_potensi");
                                if(pembayaran_new.equals("null") || pembayaran_new.isEmpty())
                                {
                                    pembayaran_new = "0";
                                } else {
                                    pembayaran_new = jsonObject.getString("pembayaran");
                                }

                                if(sisa_potensi_new.equals("null") || sisa_potensi_new.isEmpty())
                                {
                                    sisa_potensi_new = "0";
                                } else {
                                    sisa_potensi_new = jsonObject.getString("pembayaran");
                                }
                                //creating a hero object and giving them the values from json object
                                MonitoringModel item = new MonitoringModel();

                                item.setKecamatan(jsonObject.getString("kecamatan"));
                                item.setKelurahan(jsonObject.getString("kelurahan"));
                                item.setJumlah_tagging(jsonObject.getString("jumlah_tagging"));
                                item.setSudah(jsonObject.getString("sudah"));
                                item.setBelum(jsonObject.getString("belum"));
                                item.setPembayaran(pembayaran_new);
                                item.setSisa_potensi(sisa_potensi_new);
                                listdata.add(item);

                            }

                            progressBar.setVisibility(View.GONE);

                            if (listdata.isEmpty()) {
                                kosong.setVisibility(View.VISIBLE);
                                kosong.setText("Data kosong");
                            }

                            adapter = new MTL_SP2DK_Adapter(getApplicationContext(), listdata);
                            list.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();

                            Log.e("error", "" + e);

                            progressBar.setVisibility(View.GONE);
                            kosong.setVisibility(View.VISIBLE);
                            kosong.setText("Data kosong");
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