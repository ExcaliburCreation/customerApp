package com.example.excaliburcreations.customerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity /*implements Serializable, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener */ {
    private static final String TAG = "SIGN_UP";

//    private GoogleMap mMap;
//    GoogleApiClient mGoogleApiClient;
//    Location mLastKnownLocation;
//    Marker mCurrLocationMarker;
//    LocationRequest mLocationRequest;
//    private boolean mLocationPermissionGranted;
//    private LatLng mDefaultLocation;
//    private CameraPosition mCameraPosition;
//    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
//    int DEFAULT_ZOOM = 11;
//
//    private final int mMaxEntries = 5;
//    private String mLikelyPlaceNames[] = new String[mMaxEntries];
//    private String mLikelyPlaceAddresses[] = new String[mMaxEntries];
//    private String mLikelyPlaceAttributions[] = new String[mMaxEntries];
//    private LatLng mLikelyPlaceLatLngs[] = new LatLng[mMaxEntries];

    //class
    ClassCollectInfo mClassCollectInfo;


    Button BtnSignup;
    Button BtnAddloc;
    EditText editFirstName;
    EditText editLastName;
    EditText editEmail;
    EditText editPassword;
    EditText editAddress;
    Spinner spinItemCity;
    Spinner spinItemArea;
    public CustomViewPager customViewPager;
    Button btnNext;
    Button btnTrackMe;

    public String City;
    public String Area;
    public int selectedIndex = 0;

    //fragments
    public Fragment mFragment;
    FragmnetLocInfo mFragmnetLocInfo;
    public FragmentManager mFragmentManager;
    FragmentUserInfo mFragmentUserInfo;

    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;
    //referencing a specific database
    private DatabaseReference mDatabaseReference;

      /*
    * Authentication
    * */

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;

    ClassCollectInfo classCollectInfo;
    AdapterPagerAdapter mAdapterPagerAdapter;

    Bundle bundle;
    Intent intent;
    public int currentItem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        // Get the ViewPager and set it's PagerAdapter so that it can display items
//        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewpager);
//        viewPager.setAdapter(new AdapterPagerAdapter(getSupportFragmentManager(),
//                SignupActivity.this));


        customViewPager = (CustomViewPager) findViewById(R.id.viewpager);

        customViewPager.setAdapter(new AdapterPagerAdapter(getSupportFragmentManager(),
                SignupActivity.this, bundle));
        customViewPager.setSwipeable(false);

        intent = new Intent(SignupActivity.this, MapsActivity.class);

        btnTrackMe = (Button) findViewById(R.id.BtnTrackMe);
        btnNext = (Button) findViewById(R.id.BtnNext);

        btnTrackMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        //Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(customViewPager);

//        View inflatedView = getLayoutInflater().inflate(R.layout.fragment_fragment_user_info,null);
//        Button b = (Button) inflatedView.findViewById(R.id.BtnForward);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("access","have access");
//                Toast.makeText(SignupActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//            }
//        });



        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addConnectionCallbacks(this)
//                .addApi(LocationServices.API)
//                .addApi(Places.GEO_DATA_API)
//                .addApi(Places.PLACE_DETECTION_API)
//                .addApi(AppIndex.API).build();
//        mGoogleApiClient.connect();

//        customViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////                if(position == 3) {
////                    showNearPlaces();
//////                    mFragmentManager = getSupportFragmentManager();
//////                    mFragmentManager.beginTransaction().add(R.id.viewpager,sendData(City,Area)).commit();
////
////                }
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                if (position == 3) {
//                    showNearPlaces();
//                    //classCollectInfo = new ClassCollectInfo(City,Area);
////                    mFragmentManager = getSupportFragmentManager();
////                    mFragmentManager.beginTransaction().add(R.id.viewpager,sendData(City,Area)).commit();
//
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


//        //main access point of our database
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        //giving reference till the child node of the firebase database.
//        mDatabaseReference = mFirebaseDatabase.getReference().child("UserInfo");

//        editFirstName = (EditText) findViewById(R.id.EditFirstName);
//        editLastName  = (EditText) findViewById(R.id.EditLastName);
//        editEmail = (EditText) findViewById(R.id.EditEmail);
//        editPassword = (EditText) findViewById(R.id.EditPass);
//        editAddress = (EditText) findViewById(R.id.EditAdd);
//        spinItemArea = (Spinner) findViewById(R.id.spinnerArea);
//        spinItemCity = (Spinner) findViewById(R.id.spinnerCity);
//
//        BtnSignup = (Button) findViewById(R.id.BtnSignUp);
//        mAuth = FirebaseAuth.getInstance();
//
//        mAuthListner = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if(user != null){
//                    // signed in
//                    Log.d("FAuth","onAuthStateChanged:signed_in:" + user.getUid());
//
//                }else {
//                    //signed out
//                    Log.d("FAuth","onAuthStateChanged:signed_out:");
//                }
//            }
//        };
    }

    public void jumpToPage(View view) {
        customViewPager.setCurrentItem(customViewPager.getCurrentItem() + 1, true);

//        customViewPager.setCurrentItem(currentItem, true);
//        if(ClassCollectInfo.area != null){
//            currentItem =customViewPager.getCurrentItem() + 1;
//        }
//        else{
//            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
//        }

        if(customViewPager.getCurrentItem() == 2){
            btnNext.setVisibility(View.INVISIBLE);
            btnTrackMe.setVisibility(View.VISIBLE);
        }

//        if (selectedIndex == customViewPager.getCurrentItem() +1) {
//
//            Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
//            startActivity(intent);
//
//        } else {
//
//            selectedIndex++;
//
//        }
    }


//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//        Log.d(TAG, "Connection has been suspended");
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Log.d(TAG, "Failed to acquire a connection");
//
//    }
//    private void getDeviceLocation() {
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            mLocationPermissionGranted = true;
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//        }
//
//        if (mLocationPermissionGranted) {
//            mLastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//
//        }
//
//        if (mCameraPosition != null) {
//            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
//        } else if (mLastKnownLocation != null) {
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), 15));
//
//        } else {
//            Log.d(TAG, "Current Location is null, Using Default");
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
//            mMap.getUiSettings().setMyLocationButtonEnabled(false);
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        mLocationPermissionGranted = false;
//
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    mLocationPermissionGranted = true;
//                }
//            }
//        }
//
//        updateLocationUI();
//    }
//
//    @Override
//    public void onMapReady(final GoogleMap googleMap) {
//
//        mMap = googleMap;
//
//        mDefaultLocation = new LatLng(24.8615, 67.0099);
//        updateLocationUI();
//
//        getDeviceLocation();
//
//
//
//    }
//
//    private void updateLocationUI() {
//        if (mMap == null) {
//            return;
//        }
//
//
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            mLocationPermissionGranted = true;
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//        }
//
//        try {
//            boolean success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_map));
//
//            if (!success) {
//                Log.d(TAG, "Style parsing failed");
//            }
//        } catch (Resources.NotFoundException e) {
//            Log.e(TAG, "Can't find sytle");
//        }
//
//        if (mLocationPermissionGranted) {
//            mMap.setMyLocationEnabled(true);
//            mMap.getUiSettings().setMyLocationButtonEnabled(true);
//
//        } else {
//            mMap.setMyLocationEnabled(false);
//            mMap.getUiSettings().setMyLocationButtonEnabled(false);
//            mLastKnownLocation = null;
//        }
//    }
//    public void showNearPlaces() {
//        if (mMap == null) {
//            return;
//        }
//        if (mLocationPermissionGranted) {
//            @SuppressWarnings("MissingPermission")
//            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient, null);
//            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
//                @Override
//                public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {
//                    int i = 0;
//                    mLikelyPlaceNames = new String[mMaxEntries];
//                    mLikelyPlaceAddresses = new String[mMaxEntries];
//                    mLikelyPlaceAttributions = new String[mMaxEntries];
//                    mLikelyPlaceLatLngs = new LatLng[mMaxEntries];
//
//                    String cityString = null;
//                    String areaString = null;
//
//                    for (PlaceLikelihood places : placeLikelihoods) {
//
//                        mLikelyPlaceNames[i] = (String) places.getPlace().getName();
//                        mLikelyPlaceAddresses[i] = (String) places.getPlace().getAddress();
//                        mLikelyPlaceAttributions[i] = (String) places.getPlace().getAttributions();
//                        mLikelyPlaceLatLngs[i] = places.getPlace().getLatLng();
//
//                        cityString = (String) places.getPlace().getName();
//                        areaString = (String) places.getPlace().getAddress();
//                        Log.d("add", places.getPlace().getAddress().toString());
//
//                        i++;
//                        if (i > (mMaxEntries - 1)) {
//                            break;
//                        }
//                    }
//                    Geocoder geocoder = new Geocoder(SignupActivity.this, Locale.getDefault());
//                    try {
//
//
//                        List<Address> addresses = new ArrayList<Address>();
//                        addresses = geocoder.getFromLocation(mLikelyPlaceLatLngs[0].latitude, mLikelyPlaceLatLngs[0].longitude, 1);
//
//                        Log.d("geo", addresses.get(0).toString());
//
//                        City = (addresses.get(0).getAddressLine(1));
//                        Area = (addresses.get(0).getAddressLine(0));
//
//
//                        ClassCollectInfo.area = Area;
//                        ClassCollectInfo.city = City;
//
////                        Log.d("Static", ClassCollectInfo.area);
//
//
////                        FragmnetLocInfo fragment = FragmnetLocInfo.newInstance(3,"Param One","Param Two");
////
////                        bundle = new Bundle();
////                        bundle.putString("sendCity", City);
////                        Log.d(TAG,City);
////                        bundle.putString("sendArea",Area);
////                        Log.d(TAG,Area);
////
////                        // set Fragmentclass Arguments
////                        fragmnetLocInfo = new FragmnetLocInfo();
////                        fragmnetLocInfo.setArguments(bundle);
//
//                        //sendData(City,Area);
//
//
//                        Toast.makeText(SignupActivity.this, ClassCollectInfo.city, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(SignupActivity.this, ClassCollectInfo.area, Toast.LENGTH_SHORT).show();
//                        //btnContinue.setEnabled(true);
//
//                    } catch (Exception e) {
//                        Log.d(TAG, "Geocoder Error");
//                        AlertDialog.Builder alert = new AlertDialog.Builder(SignupActivity.this);
//                        alert.setMessage("Problem in getting your Location");
//                        alert.setCancelable(false);
//
//                        alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                System.exit(0);
//                            }
//                        });
//                    }
//
//                    placeLikelihoods.release();
//
//
//                    //  openPlacesDialog();
//                }
//            });
//        } else {
//            mMap.addMarker(new MarkerOptions()
//                    .title(getString(R.string.default_info_title))
//                    .position(mDefaultLocation)
//                    .snippet(getString(R.string.default_info_snippet)));
//
//        }
//    }
//
//    public void openPlacesDialog() {
//
//        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                LatLng marketLatLng = mLikelyPlaceLatLngs[which];
//                String marketSnippet = mLikelyPlaceAddresses[which];
//                if (mLikelyPlaceAttributions[which] != null) {
//                    marketSnippet = marketSnippet + "\n" + mLikelyPlaceAttributions[which];
//                }
//
//                mMap.addMarker(new MarkerOptions()
//                        .title(mLikelyPlaceNames[which])
//                        .position(marketLatLng)
//                        .snippet(marketSnippet));
//
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marketLatLng, DEFAULT_ZOOM));
//            }
//        };
//
//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setTitle(R.string.pick_place)
//                .setItems(mLikelyPlaceNames, listener)
//                .show();
//
//    }
//    //sending to fragment
//    public Fragment sendData(String sendCity, String sendArea){
//
//        mFragmentManager = getSupportFragmentManager();
//        mFragmnetLocInfo = new FragmnetLocInfo();
//        bundle = new Bundle();
//        bundle.putString("mCity", sendCity);
//        bundle.putString("mArea",sendArea);
//        mFragmnetLocInfo.setArguments(bundle);
//
//        return mFragmnetLocInfo;
//
//    }
//
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    public Action getIndexApiAction() {
//        Thing object = new Thing.Builder()
//                .setName("Signup Page") // TODO: Define a title for the content shown.
//                // TODO: Make sure this auto-generated URL is correct.
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Action.Builder(Action.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
//                .build();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        mGoogleApiClient.connect();
//        AppIndex.AppIndexApi.start(mGoogleApiClient, getIndexApiAction());
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(mGoogleApiClient, getIndexApiAction());
//        mGoogleApiClient.disconnect();
//    }

//    public void signup(View view){
//        mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d("FAuth", "createUserWithEmail:onComplete:" + task.isSuccessful());
//                        if(task.isSuccessful()){
//                            Log.d("FAuth", "Registration SuccessFull");
//                            ClassUserInfo classUserInfo = new ClassUserInfo(editFirstName.getText().toString(),editLastName.getText().toString(),editEmail.getText().toString(),editAddress.getText().toString(),spinItemArea.getSelectedItem().toString(),spinItemCity.getSelectedItem().toString());
//                            mDatabaseReference.push().setValue(classUserInfo);
//                            Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
//                            startActivity(intent);
//                        }else{
//                            Log.d("FAuth","Registration InWithEmail:failed"+ task.getException());
//                            Log.d("FAuth", "Registration Failed");
//                        }
//                    }
//
//                });
//    }
}
