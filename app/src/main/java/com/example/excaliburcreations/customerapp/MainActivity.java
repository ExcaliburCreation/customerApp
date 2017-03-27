package com.example.excaliburcreations.customerapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                    startActivity(intent);

                    MainActivity.this.finish();
                }
            }, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
