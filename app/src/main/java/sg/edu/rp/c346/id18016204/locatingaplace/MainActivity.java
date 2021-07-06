package sg.edu.rp.c346.id18016204.locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
Button north , central , east;
private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                LatLng singapore = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,10));

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                LatLng poi_north = new LatLng(1.461708, 103.813500);
                LatLng poi_central = new LatLng(1.300542, 103.841226);
                LatLng poi_east = new LatLng(1.350057, 103.934452);

                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("HQ-North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central:")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                Marker East = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East:")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if(permissionCheck == PermissionChecker.PERMISSION_GRANTED){
                    map.setMyLocationEnabled(true);
                }else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

            }
        });

        north = findViewById(R.id.north);
        central = findViewById(R.id.Central);
        east = findViewById(R.id.east);


         LatLng poi_north = new LatLng(1.461708, 103.813500);
         LatLng poi_central = new LatLng(1.300542, 103.841226);
         LatLng poi_east = new LatLng(1.350057, 103.934452);

         north.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,15));
             }
         });
         central.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,15));
             }
         });
         east.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,15));
             }
         });
    }
}