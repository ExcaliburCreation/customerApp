package com.example.excaliburcreations.customerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity /*implements Serializable, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener */ {
    private static final String TAG = "SIGN_UP";


    Button btnNext;
    Button btnTrackMe;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        // Get the ViewPager and set it's PagerAdapter so that it can display items
//        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewpager);
//        viewPager.setAdapter(new AdapterPagerAdapter(getSupportFragmentManager(),
//                SignupActivity.this));


        intent = new Intent(SignupActivity.this, MapsActivity.class);

//        btnTrackMe = (Button) findViewById(R.id.BtnTrackMe);
//        btnNext = (Button) findViewById(R.id.BtnNext);
//
//        btnTrackMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
//                startActivity(intent);
//            }
//        });


    }
}
