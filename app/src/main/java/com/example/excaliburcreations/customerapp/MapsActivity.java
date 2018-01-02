package com.example.excaliburcreations.customerapp;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements Serializable, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = MapsActivity.class.getSimpleName();

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastKnownLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private boolean mLocationPermissionGranted;
    private LatLng mDefaultLocation;
    private CameraPosition mCameraPosition;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    int DEFAULT_ZOOM = 11;

    private final int mMaxEntries = 5;
    private String mLikelyPlaceNames[] = new String[mMaxEntries];
    private String mLikelyPlaceAddresses[] = new String[mMaxEntries];
    private String mLikelyPlaceAttributions[] = new String[mMaxEntries];
    private LatLng mLikelyPlaceLatLngs[] = new LatLng[mMaxEntries];

    private EditText editTextCity;
    private EditText editTextArea;
    private Button btnContinue;

    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;
    //referencing a specific database
    private DatabaseReference mDatabaseReference;

    Geocoder geocoder;
    List<Address> addresses;

    String Area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().setTitle("Location");

        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("AccountRequest");

        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextArea = (EditText) findViewById(R.id.editTextArea);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnContinue.setEnabled(false);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                String compName = intent1.getStringExtra("compName");
                String perName = intent1.getStringExtra("perName");
                String perDes = intent1.getStringExtra("perDes");
                String busDes = intent1.getStringExtra("busDes");
                String address = intent1.getStringExtra("address");
                String cellNo = intent1.getStringExtra("cellNo");
                String city = intent1.getStringExtra("city");
                String time = intent1.getStringExtra("time");
                String comments = intent1.getStringExtra("comments");
                String country = intent1.getStringExtra("country");


                Log.d("location3", "longititude: "+ addresses.get(0).getLongitude());
                Log.d("location3", "latititude: "+ addresses.get(0).getLatitude());


                ClassUserInfo classUserInfo = new ClassUserInfo(compName,perName,
                                                perDes,busDes,
                                                cellNo,city,time,
                                                comments,country,address,addresses.get(0).getLongitude(),addresses.get(0).getLatitude());
                            mDatabaseReference.push().setValue(classUserInfo);

                String authKey = getIntent().getStringExtra("username");
                Intent intent = new Intent(MapsActivity.this, SigninActivity.class);
                //intent.putExtra("username",authKey);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Failed to acquire a connection");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection has been suspended");
    }

    private void getDeviceLocation() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mLocationPermissionGranted) {
            mLastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        }

        if (mCameraPosition != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mLastKnownLocation != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), 15));

        } else {
            Log.d(TAG, "Current Location is null, Using Default");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;

        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }

        updateLocationUI();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mDefaultLocation = new LatLng(24.8615, 67.0099);
        updateLocationUI();

        getDeviceLocation();

        showNearPlaces();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }


        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        try {
            boolean success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_map));

            if (!success) {
                Log.d(TAG, "Style parsing failed");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find sytle");
        }

        if (mLocationPermissionGranted) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

        } else {
            mMap.setMyLocationEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mLastKnownLocation = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.current_places_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.option_get_place) {
            showNearPlaces();
        }
        return true;
    }

    public void showNearPlaces() {
        if (mMap == null) {
            return;
        }
        if (mLocationPermissionGranted) {
            @SuppressWarnings("MissingPermission")
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient, null);
            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {
                    int i = 0;
                    mLikelyPlaceNames = new String[mMaxEntries];
                    mLikelyPlaceAddresses = new String[mMaxEntries];
                    mLikelyPlaceAttributions = new String[mMaxEntries];
                    mLikelyPlaceLatLngs = new LatLng[mMaxEntries];

                    String cityString = null;
                    String areaString = null;

                    for (PlaceLikelihood places : placeLikelihoods) {

                        mLikelyPlaceNames[i] = (String) places.getPlace().getName();
                        mLikelyPlaceAddresses[i] = (String) places.getPlace().getAddress();
                        mLikelyPlaceAttributions[i] = (String) places.getPlace().getAttributions();
                        mLikelyPlaceLatLngs[i] = places.getPlace().getLatLng();

                        cityString = (String) places.getPlace().getName();
                        areaString = (String) places.getPlace().getAddress();
                        Log.d("add", places.getPlace().getAddress().toString());

                        i++;
                        if (i > (mMaxEntries - 1)) {
                            break;
                        }
                    }
                    geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                    try {


                        addresses = new ArrayList<Address>();
                         addresses = geocoder.getFromLocation(mLikelyPlaceLatLngs[0].latitude, mLikelyPlaceLatLngs[0].longitude, 1);

                        Log.d("geo", addresses.get(0).toString());

                        editTextCity.setText(addresses.get(0).getAddressLine(1));
//                        Log.d("location1",addresses.get(0).getAddressLine(1));


                        addresses.get(0).getLatitude();
                        editTextArea.setText(addresses.get(0).getAddressLine(0));
                        Log.d("location2",addresses.get(0).getAddressLine(0));
                        Log.d("location2","adress: "+ addresses.toString());
                        Log.d("location2","geocoder: "+ geocoder.toString());
                        Log.d("location2", "longititude: "+ addresses.get(0).getLongitude());
                        Log.d("location2", "latititude: "+ addresses.get(0).getLatitude());

                        btnContinue.setEnabled(true);
                    } catch (IOException e) {
                        Log.d(TAG, "Geocoder Error");
                    }

                    placeLikelihoods.release();


                    //  openPlacesDialog();
                }
            });
        } else {
            mMap.addMarker(new MarkerOptions()
                    .title(getString(R.string.default_info_title))
                    .position(mDefaultLocation)
                    .snippet(getString(R.string.default_info_snippet)));

        }
    }

    public void openPlacesDialog() {

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                LatLng marketLatLng = mLikelyPlaceLatLngs[which];
                String marketSnippet = mLikelyPlaceAddresses[which];
                if (mLikelyPlaceAttributions[which] != null) {
                    marketSnippet = marketSnippet + "\n" + mLikelyPlaceAttributions[which];
                }

                mMap.addMarker(new MarkerOptions()
                        .title(mLikelyPlaceNames[which])
                        .position(marketLatLng)
                        .snippet(marketSnippet));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marketLatLng, DEFAULT_ZOOM));
            }
        };

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.pick_place)
                .setItems(mLikelyPlaceNames, listener)
                .show();

    }

}
