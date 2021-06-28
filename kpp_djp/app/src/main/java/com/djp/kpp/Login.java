package com.djp.kpp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
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

import com.djp.kpp.Menu.Dashboard;
import com.djp.kpp.Menu.UbahPassword;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView Link;
    Spanned Text;
    String getemail, getpass;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

//      Progress dialog
        pDialog = new ProgressDialog(Login.this);
        pDialog.setCancelable(false);

        Link = (TextView)findViewById(R.id.link);

        Text = Html.fromHtml("Lupa Password? <br />" +
                "<a href='https://wa.me/6281286168487'>Hubungi Admin</a>");

        Link.setMovementMethod(LinkMovementMethod.getInstance());
        Link.setText(Text);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getemail = email.getText().toString();
                getpass = password.getText().toString();

                if (getemail.equals("") || getemail.equals("null") || getpass.equals("") || getpass.equals("null")) {

                    Sumber.toastShow(getApplicationContext(), "Harap isi secara lengkap");

                } else {

                    login();

                }

            }
        });

    }

    private void login() {

        pDialog.setMessage("Login ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Koneksi.login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server

                        Log.e("response", " " + response);

                        try {

                            JSONObject data = new JSONObject(response);
                            String error = data.getString("error");
                            Log.e("error", " " + error);

                            if (error.equals("false")) {


                                String id = data.getString("id");
                                String ip = data.getString("ip");
                                String nip = data.getString("nip");
                                String nama = data.getString("nama");
                                String jabatan = data.getString("jabatan");
                                String nama_seksi = data.getString("nama_seksi");
                                String tipe_seksi = data.getString("tipe_seksi");
                                String kec = data.getString("kec");
                                String kel = data.getString("kel");
                                String email = data.getString("email");
                                String user = data.getString("user");
                                String token = data.getString("token");
                                String password_default = data.getString("password_default");

                                Log.e("id", "" +id);
                                Log.e("akun", "" + nama);
                                Log.e("akun", "" + email);
                                Log.e("akun", "" + user);
                                Log.e("akun", "" + token);



                                if (user.equals("Admin")) {
                                    if(password_default.equals("1")) {
                                        Intent intent = new Intent(Login.this, UbahPassword.class);
                                        startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(Login.this, Dashboard.class);
                                        startActivity(intent);
                                    }

                                } else  if (user.equals("Staff")) {

                                    if(password_default.equals("1")) {
                                        Intent intent = new Intent(Login.this, UbahPassword.class);
                                        startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(Login.this, Dashboard.class);
                                        startActivity(intent);
                                    }

                                }

                                Sumber.saveData("akun", "id", id, getApplicationContext());
                                Sumber.saveData("akun", "ip", ip, getApplicationContext());
                                Sumber.saveData("akun", "nip", nip, getApplicationContext());
                                Sumber.saveData("akun", "nama", nama, getApplicationContext());
                                Sumber.saveData("akun", "jabatan", jabatan, getApplicationContext());
                                Sumber.saveData("akun", "nama_seksi", nama_seksi, getApplicationContext());
                                Sumber.saveData("akun", "tipe_seksi", tipe_seksi, getApplicationContext());
                                Sumber.saveData("akun", "kec", kec, getApplicationContext());
                                Sumber.saveData("akun", "kel", kel, getApplicationContext());
                                Sumber.saveData("akun", "email", getemail, getApplicationContext());
                                Sumber.saveData("akun", "password", getpass, getApplicationContext());
                                Sumber.saveData("akun", "user", user, getApplicationContext());
                                Sumber.saveData("akun", "token", token, getApplicationContext());

                            }

                            Sumber.toastShow(getApplicationContext(), "Login Sukses");


                        } catch (JSONException e) {
                            e.printStackTrace();

                            Log.e("error", "" +e );

                            Sumber.toastShow(getApplicationContext(), "NIP atau Password Salah");

                        }

                        hideDialog();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want

//                        Sumber.dialogHide(getApplicationContext());
                        hideDialog();

                        Log.e("error", "" + error);


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request

                // mengirim data json ke server
                params.put("email", getemail);
                params.put("password", getpass);

                //returning parameter
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

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        finishAffinity();

        this.doubleBackToExitPressedOnce = true;

    }

}
