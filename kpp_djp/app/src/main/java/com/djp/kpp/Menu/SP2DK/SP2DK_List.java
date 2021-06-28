package com.djp.kpp.Menu.SP2DK;

import android.content.Intent;
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
import com.djp.kpp.Menu.CaptureData.ListCaptureDataAdapter;
import com.djp.kpp.Menu.PenjaminKualitasData.PenjaminKualitasDataDetail;
import com.djp.kpp.Menu.PenjaminKualitasData.PenjaminKualitasDataList;
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

public class SP2DK_List extends AppCompatActivity {

    ListView list;
    ProgressBar progressBar;
    TextView kosong;
    List<CaptureDataModel> listdata = new ArrayList<>();
    SP2DK_Adapter adapter;
    String getToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp2dk_list);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("SP2DK-DSE");
        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);


        list = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);
        kosong = findViewById(R.id.kosong);

        getToken = Sumber.getData("akun", "token", getApplicationContext());

        getData();



    }


    private void getData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Koneksi.sp2dk, null,
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
                                String ar_new = jsonObject.getString("ar");
                                if(ar_new.equals("null") || ar_new.isEmpty())
                                {
                                    ar_new = "-";
                                } else {
                                    ar_new = jsonObject.getString("ar");
                                }

                                //creating a hero object and giving them the values from json object
                                CaptureDataModel item = new CaptureDataModel();

                                item.setId(jsonObject.getString("id"));
                                item.setNpwp(jsonObject.getString("npwp"));
                                item.setAr(ar_new);
                                item.setSp2dk_nomor(jsonObject.getString("sp2dk_nomor"));
                                item.setTanggal(jsonObject.getString("tanggal"));
                                item.setSp2dk_tgl(jsonObject.getString("sp2dk_tgl"));
                                item.setSp2dk_potensi(jsonObject.getString("sp2dk_potensi"));
                                item.setSp2dk_pembayaran(jsonObject.getString("sp2dk_pembayaran"));
                                item.setSp2dk_tahun_pajak(jsonObject.getString("sp2dk_tahun_pajak"));
                                item.setSp2dk_jenis_pajak(jsonObject.getString("sp2dk_jenis_pajak"));
                                item.setSp2dk_masa_pajak(jsonObject.getString("sp2dk_masa_pajak"));

                                listdata.add(item);

                            }

                            progressBar.setVisibility(View.GONE);

                            if (listdata.isEmpty()) {
                                kosong.setVisibility(View.VISIBLE);
                                kosong.setText("Data kosong");
                            }

                            adapter = new SP2DK_Adapter(getApplicationContext(), listdata);

                            list.setAdapter(adapter);

                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Intent in = new Intent(SP2DK_List.this, SP2DK_Detail.class);

                                    CaptureDataModel data = listdata.get(position);

                                    in.putExtra("data", "update");
                                    in.putExtra("id", String.valueOf(data.getId()));
                                    in.putExtra("tanggal", String.valueOf(data.getTanggal()));
                                    in.putExtra("npwp", String.valueOf(data.getNpwp()));
                                    in.putExtra("ar", String.valueOf(data.getAr()));
                                    in.putExtra("sp2dk_nomor", String.valueOf(data.getSp2dk_nomor()));
                                    in.putExtra("sp2dk_tgl", String.valueOf(data.getSp2dk_tgl()));
                                    in.putExtra("sp2dk_potensi", String.valueOf(data.getSp2dk_potensi()));
                                    in.putExtra("sp2dk_pembayaran", String.valueOf(data.getSp2dk_pembayaran()));
                                    in.putExtra("sp2dk_tahun_pajak", String.valueOf(data.getSp2dk_tahun_pajak()));
                                    in.putExtra("sp2dk_jenis_pajak", String.valueOf(data.getSp2dk_jenis_pajak()));
                                    in.putExtra("sp2dk_masa_pajak", String.valueOf(data.getSp2dk_masa_pajak()));

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