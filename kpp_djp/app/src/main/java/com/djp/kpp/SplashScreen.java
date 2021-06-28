package com.djp.kpp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.djp.kpp.Menu.Dashboard;

public class SplashScreen extends AppCompatActivity {

    String getemail, getpass, getuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                getemail = Sumber.getData("akun", "email", getApplicationContext());
                getpass = Sumber.getData("akun", "password", getApplicationContext());
                getuser = Sumber.getData("akun", "user", getApplicationContext());

                Log.e("akun", ""+getemail);
                Log.e("akun", ""+getpass);
                Log.e("akun", ""+getuser);

                if (getemail == null || getemail.equals("") && getpass == null || getpass.equals("")) {

                    Log.e("data", "null");

                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);

                } else {

                    if (getuser.equals("Staff")) {

                        Intent intent = new Intent(SplashScreen.this, Dashboard.class);
                        startActivity(intent);
                    } else {

//                        Intent intent = new Intent(SplashScreen.this, Kasir.class);
                        Intent intent = new Intent(SplashScreen.this, Dashboard.class);
                        startActivity(intent);

                    }

                }

            }
        }, 2000L);

    }
}
