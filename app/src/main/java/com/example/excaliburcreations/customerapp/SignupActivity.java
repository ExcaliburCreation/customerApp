package com.example.excaliburcreations.customerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    Button BtnSignup;
    Button BtnAddloc;
    EditText editFirstName;
    EditText editLastName;
    EditText editEmail;
    EditText editPassword;
    EditText editAddress;
    Spinner spinItemCity;
    Spinner spinItemArea;

    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;
    //referencing a specific database
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //giving reference till the child node of the firebase database.
        mDatabaseReference = mFirebaseDatabase.getReference().child("UserInfo");

        editFirstName = (EditText) findViewById(R.id.EditFirstName);
        editLastName  = (EditText) findViewById(R.id.EditLastName);
        editEmail = (EditText) findViewById(R.id.EditEmail);
        editPassword = (EditText) findViewById(R.id.EditPass);
        editAddress = (EditText) findViewById(R.id.EditAdd);
        spinItemArea = (Spinner) findViewById(R.id.spinnerArea);
        spinItemCity = (Spinner) findViewById(R.id.spinnerCity);
        BtnAddloc = (Button) findViewById(R.id.BtnAddloc);
        BtnAddloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BtnSignup = (Button) findViewById(R.id.BtnSignUp);
        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClassUserInfo classUserInfo = new ClassUserInfo(editFirstName.getText().toString(),editLastName.getText().toString(),editEmail.getText().toString(),editAddress.getText().toString(),spinItemArea.getSelectedItem().toString(),spinItemCity.getSelectedItem().toString());
                mDatabaseReference.push().setValue(classUserInfo);
                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
