package com.djp.kpp.Menu.CaptureData;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.djp.kpp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapLocation extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    Double lokasi_lat,lokasi_long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbarclose);
        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Lokasi");
        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.INVISIBLE);

        FloatingActionButton fab = findViewById(R.id.next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(), DataLokasi.class);
                in.putExtra("lokasi_lat", lokasi_lat);
                in.putExtra("lokasi_long", lokasi_long);
                startActivity(in);
            }
        });


        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MapLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(MapLocation.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null) {
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
//                            Init LatLong
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                            Create marker
                            MarkerOptions options = new MarkerOptions().position(latLng).title("Lokasi Saat Ini");

//                           Zoom Map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
//                            Add Marker
                            googleMap.addMarker(options);
                        }
                    });
                    lokasi_lat = location.getLatitude();
                    lokasi_long = location.getLongitude();
                    TextView locationTv = (TextView) findViewById(R.id.latlongview);
                    locationTv.setText("Latitude:" + lokasi_lat + ", Longitude:" + lokasi_long);

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44 ) {
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }
}