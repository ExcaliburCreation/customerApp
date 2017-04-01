package com.example.excaliburcreations.customerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
      /*
    * Authentication
    * */

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;

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
                Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        BtnSignup = (Button) findViewById(R.id.BtnSignUp);
        mAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    // signed in
                    Log.d("FAuth","onAuthStateChanged:signed_in:" + user.getUid());

                }else {
                    //signed out
                    Log.d("FAuth","onAuthStateChanged:signed_out:");
                }
            }
        };
    }
    public void signup(View view){
        mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("FAuth", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()){
                            Log.d("FAuth", "Registration SuccessFull");
                            ClassUserInfo classUserInfo = new ClassUserInfo(editFirstName.getText().toString(),editLastName.getText().toString(),editEmail.getText().toString(),editAddress.getText().toString(),spinItemArea.getSelectedItem().toString(),spinItemCity.getSelectedItem().toString());
                            mDatabaseReference.push().setValue(classUserInfo);
                            Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                            startActivity(intent);
                        }else{
                            Log.d("FAuth","Registration InWithEmail:failed"+ task.getException());
                            Log.d("FAuth", "Registration Failed");
                        }
                    }

                });
    }
}
