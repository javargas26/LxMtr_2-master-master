package com.example.lxmtr_2;

import android.Manifest;
import android.app.Application;
import android.bluetooth.BluetoothDevice;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback,
        LocationListener {

    //String informacion
    String clase_de_iluminacion, tramo, direccion_l1, direccion_l2, barrio, referencia_luxometro, condicion_atmosferica, interdistancia, ancho;
    //String L1
    String orientacion_l1, fuente_l1, apoyo_l1, longitud_l1, avance_calzada_l1, distancia_l1_borde,
            altura_montaje_l1, angulo_inclinacion_l1, tension_nominal_l1, tension_medida_l1, polucion_l1;
    //String L2
    String orientacion_l2, fuente_l2, apoyo_l2, longitud_l2, avance_calzada_l2, distancia_l2_borde,
            altura_montaje_l2, angulo_inclinacion_l2, tension_nominal_l2, tension_medida_l2, polucion_l2;

    String clase_elegida;

    String latitud_enviar, longitud_enviar;


    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;
    private static final int Request_User_Location_Code = 99;




    TextView tvValueLatitud, tvValueLongitude;
    Button btnGPS;
    LocationManager locationManager;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Marker mLocationMarker;
    FusedLocationProviderApi mFusedLocationProviderClient;
    Criteria req;
    private LatLng center;


    final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }

        // Todo Location Already on  ... start
        final LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(this)) {
            Toast.makeText(this,"GPS activado",Toast.LENGTH_SHORT).show();

        }
        // Todo Location Already on  ... end

        if(!hasGPSDevice(this)){
            Toast.makeText(this,"Gps not Supported",Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(this)) {
            Log.e("keshav","Gps not enabled");
            Toast.makeText(this,"GPS desactivado",Toast.LENGTH_SHORT).show();
            //enableLoc();
        }else{
            Log.e("keshav","Gps already enabled");
            Toast.makeText(this,"GPS activado",Toast.LENGTH_SHORT).show();
        }



        //valores desde la actividad anterior
        Bundle bundle=getIntent().getExtras();
        clase_de_iluminacion= (String) bundle.get("clase_de_iluminacion");
        tramo=(String) bundle.get("tramo");
        direccion_l1=(String) bundle.get("direccion_l1");
        direccion_l2=(String) bundle.get("direccion_l2");
        barrio=(String) bundle.get("barrio");
        referencia_luxometro=(String) bundle.get("referencia_luxometro");
        condicion_atmosferica=(String) bundle.get("condicion_atmosferica");

        orientacion_l1=(String) bundle.get("orientacion_l1");
        fuente_l1=(String) bundle.get("fuente_l1");
        apoyo_l1=(String) bundle.get("apoyo_l1");
        longitud_l1=(String) bundle.get("longitud_l1");
        avance_calzada_l1=(String) bundle.get("avance_calzada_l1");
        distancia_l1_borde=(String) bundle.get("distancia_l1_borde");
        altura_montaje_l1=(String) bundle.get("altura_montaje_l1");
        angulo_inclinacion_l1=(String) bundle.get("angulo_inclinacion_l1");
        tension_nominal_l1=(String) bundle.get("tension_nominal_l1");
        tension_medida_l1=(String) bundle.get("tension_medida_l1");
        polucion_l1=(String) bundle.get("polucion_l1");

        orientacion_l2=(String) bundle.get("orientacion_l2");
        fuente_l2=(String) bundle.get("fuente_l2");
        apoyo_l2=(String) bundle.get("apoyo_l2");
        longitud_l2=(String) bundle.get("longitud_l2");
        avance_calzada_l2=(String) bundle.get("avance_calzada_l2");
        distancia_l2_borde=(String) bundle.get("distancia_l2_borde");
        altura_montaje_l2=(String) bundle.get("altura_montaje_l2");
        angulo_inclinacion_l2=(String) bundle.get("angulo_inclinacion_l2");
        tension_nominal_l2=(String) bundle.get("tension_nominal_l2");
        tension_medida_l2=(String) bundle.get("tension_medida_l2");
        polucion_l2=(String) bundle.get("polucion_l2");

        interdistancia=(String) bundle.get("interdistancia");
        ancho=(String) bundle.get("ancho_calzada");


        tvValueLatitud = findViewById(R.id.tvValueLongitude);
        tvValueLongitude = findViewById(R.id.tvValueLatitude);
        btnGPS = findViewById(R.id.buttonGPS);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);


    }

    private boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null)
            return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null)
            return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {            // TODO: Consider calling

            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }



    }

    public boolean checkUserLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);

            }
            return false;

        }
        else
        {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                    {
                        if (googleApiClient == null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }


    protected synchronized void buildGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }


    @Override
    public void onLocationChanged(Location location) {
        lastLocation=location;
        if(currentLocationMarker != null)
        {
            currentLocationMarker.remove();
        }
        LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());



        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(16));

        if (googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest=new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void get_gps(View view) {
        tvValueLatitud.setText(null);
        tvValueLongitude.setText(null);
        double mylat = mMap.getCameraPosition().target.latitude;
        double mylon = mMap.getCameraPosition().target.longitude;
        String lat=String.valueOf(mylat);
        String lgn=String.valueOf(mylon);
        tvValueLatitud.setText(lgn);
        tvValueLongitude.setText(lat);
        Log.e("latitud: ", tvValueLatitud.getText().toString());
        Log.e("longitud", tvValueLongitude.getText().toString());

    }

    public void next_activity_nueve_puntos(View view) {

        latitud_enviar=tvValueLongitude.getText().toString();
        longitud_enviar=tvValueLatitud.getText().toString();


        Intent intent= new Intent(MapsActivity.this,DeviceListActivity.class);
        intent.putExtra("clase_de_iluminacion",clase_de_iluminacion);
        intent.putExtra("tramo",tramo);
        intent.putExtra("direccion_l1",direccion_l1);
        intent.putExtra("direccion_l2",direccion_l2);
        intent.putExtra("barrio",barrio);
        intent.putExtra("referencia_luxometro",referencia_luxometro);
        intent.putExtra("condicion_atmosferica",condicion_atmosferica);

        intent.putExtra("orientacion_l1", orientacion_l1);
        intent.putExtra("fuente_l1",fuente_l1);
        intent.putExtra("apoyo_l1",apoyo_l1);
        intent.putExtra("longitud_l1",longitud_l1);
        intent.putExtra("avance_calzada_l1",avance_calzada_l1);
        intent.putExtra("distancia_l1_borde",distancia_l1_borde);
        intent.putExtra("altura_montaje_l1", altura_montaje_l1);
        intent.putExtra("angulo_inclinacion_l1", angulo_inclinacion_l1);
        intent.putExtra("tension_nominal_l1", tension_nominal_l1);
        intent.putExtra("tension_medida_l1",tension_medida_l1);
        intent.putExtra("polucion_l1", polucion_l1);

        intent.putExtra("orientacion_l2", orientacion_l2);
        intent.putExtra("fuente_l2",fuente_l2);
        intent.putExtra("apoyo_l2",apoyo_l2);
        intent.putExtra("longitud_l2",longitud_l2);
        intent.putExtra("avance_calzada_l2",avance_calzada_l2);
        intent.putExtra("distancia_l2_borde",distancia_l2_borde);
        intent.putExtra("altura_montaje_l2", altura_montaje_l2);
        intent.putExtra("angulo_inclinacion_l2", angulo_inclinacion_l2);
        intent.putExtra("tension_nominal_l2", tension_nominal_l2);
        intent.putExtra("tension_medida_l2",tension_medida_l2);
        intent.putExtra("polucion_l2", polucion_l2);

        intent.putExtra("interdistancia", interdistancia);
        intent.putExtra("ancho_calzada", ancho);

        intent.putExtra("latitud", latitud_enviar);
        intent.putExtra("longitud",longitud_enviar);
        startActivity(intent);

    }
}

