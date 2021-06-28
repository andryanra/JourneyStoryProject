package com.djp.kpp.Menu.CaptureData;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.djp.kpp.Menu.Dashboard;
import com.djp.kpp.R;
import com.djp.kpp.Sumber;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.djp.kpp.Koneksi.capture_data;
import static com.djp.kpp.Koneksi.gambar;
import static com.djp.kpp.Koneksi.tambah_komentar;

public class TambahFotoKomen extends AppCompatActivity {

    int PICK_IMAGE = 100;
    int TOTAL_IMAGE = 5;
    Uri imageUri;
    Button btnGaleri, btnSumbit;
    GridView gvImagenes;

    List<Uri> listaImagenes = new ArrayList<>();
    List<String> listaBase64Imagenes = new ArrayList<>();

    GridViewAdapter baseAdapter;

    Double lokasi_lat, lokasi_long;
    String nama_jalan, rt, rw, kel, kec;
    String klu, kegiatan, merk, situasi, karyawan, omzet;
    String npwp, nama_usaha, tgl_terdaftar_npwp, status_wp, status_pkp, tgl_terdaftar_pkp, tingkat_potensial;
    String sp2dk_nomor, sp2dk_tgl, sp2dk_respon;
    String komentar, getToken,get_userid, tanggal, tanggal_view;
    String getuuid, getjumlah_foto;

    EditText komentar_et;

    String random_id = UUID.randomUUID().toString();
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komen_foto);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbarclose);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Tambah Komentar dan Foto Objek");
        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.INVISIBLE);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat view = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        tanggal = tgl.format(c);
        tanggal_view = view.format(c);

        getToken = Sumber.getData("akun", "token", getApplicationContext());
        get_userid = Sumber.getData("akun", "id", getApplicationContext());

        pDialog = new ProgressDialog(TambahFotoKomen.this);
        pDialog.setCancelable(false);

        Intent bundle = getIntent();
        getuuid = bundle.getStringExtra("UUID");
        getjumlah_foto = bundle.getStringExtra("jumlah_foto");

        komentar_et = findViewById(R.id.komentar);

        gvImagenes = findViewById(R.id.gvImagenes);
        btnGaleri = findViewById(R.id.btnGaleria);
        btnSumbit = findViewById(R.id.btnSubirImagenes);



        btnGaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int jml_foto = Integer.parseInt(getjumlah_foto);
                if(listaImagenes.size() >= TOTAL_IMAGE - jml_foto) {
                    Sumber.toastShow(getApplicationContext(), "Gambar Sudah Maksimal");
                } else {
                    openGallery();
                }

            }
        });

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload();
                capture_data_save();
            }
        });

    }

    private void capture_data_save() {
        komentar = komentar_et.getText().toString();
        pDialog.setMessage("Uploading ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tambah_komentar,
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
                                Intent intent = new Intent(TambahFotoKomen.this, Dashboard.class);
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
                String lat_s = String.valueOf(lokasi_lat);
                String long_s = String.valueOf(lokasi_long);

                Map<String, String> params = new HashMap<>();
                params.put("user_id", get_userid);
                params.put("komentar", komentar);

                params.put("UUID", getuuid);

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+ getToken);
                return params;
            }
        };

        com.djp.kpp.Volley.getInstance().addToRequestQueue(stringRequest);
    }


    public void upload() {

        listaBase64Imagenes.clear();

        for(int i = 0 ; i < listaImagenes.size() ; i++) {
            try {
                InputStream is = getContentResolver().openInputStream(listaImagenes.get(i));
                Bitmap bitmap = BitmapFactory.decodeStream(is);

                String cadena = convertirUriToBase64(bitmap);

                prepare_upload("nomIma"+i, cadena);
                Log.e("RANDOM_ID", "" +random_id);

                bitmap.recycle();

            } catch (IOException e) { }

        }
    }

    public void prepare_upload(final String nombre, final String cadena) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, gambar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(TambahFotoKomen.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();
                params.put("UUID", getuuid);
                params.put("images", cadena);
                params.put("user_id", get_userid);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public String convertirUriToBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        String encode = Base64.encodeToString(bytes, Base64.DEFAULT);

        return encode;
    }

    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ClipData clipData = data.getClipData();

        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if(clipData == null) {
                imageUri = data.getData();
                listaImagenes.add(imageUri);
            } else {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    listaImagenes.add(clipData.getItemAt(i).getUri());
                }
            }
        }

        baseAdapter = new GridViewAdapter(TambahFotoKomen.this, listaImagenes);
        gvImagenes.setAdapter(baseAdapter);

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