package com.example.user.project_trace;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLng point1=new LatLng(23.479681, 120.449826);
    private static final LatLng point2=new LatLng(23.481347, 120.447699);
    private Marker marker1;
    private Marker marker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btn1=findViewById(R.id.button1);
        Button btn2=findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point1,15),
                        new GoogleMap.CancelableCallback(){
                            @Override
                            public void onFinish() {
                                marker1= mMap.addMarker(new MarkerOptions().position(point1)
                                .title("文化"));
                                marker1.setAlpha(2f);
                                marker1.setTag(20);
                                PolylineOptions optionLine=new PolylineOptions();
                                optionLine.add(new LatLng( 23.479292, 120.442992));
                                optionLine.add(new LatLng( 23.479418, 120.444140));
                                optionLine.add(new LatLng(  23.479894, 120.449025));
                                optionLine.add(new LatLng( 23.479889, 120.449465));
                                optionLine.add(new LatLng(   23.479695, 120.449661));
                                optionLine.color(Color.RED);
                                Polyline polyLine =  mMap.addPolyline(optionLine);

                            }

                            @Override
                            public void onCancel() {

                            }
                        });

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point2,15));
                marker2=mMap.addMarker(new MarkerOptions().position(point2).
                        icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title("中正"));
                marker2.setTag(40);
                marker2.setAlpha(20f);
                CircleOptions circleOptions=new
                        CircleOptions().center(point2).radius(100);
                circleOptions.fillColor(getColor(R.color.yellow));
                circleOptions.strokeColor(R.color.red);

                mMap.addCircle(circleOptions);
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Integer tag=(Integer)marker.getTag();
                if(tag==20){
                    Log.d("XXXXXXXXXXXX","marker:Title "+marker.getTitle());
                }else if(tag==40){
                    Log.d("XXXXXXXXXXXX","marker:Title "+marker.getTitle());
                }
                return false;
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(23.479269, 120.442817);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
    }
}
