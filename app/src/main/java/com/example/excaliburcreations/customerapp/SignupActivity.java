package com.example.excaliburcreations.customerapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
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
    CustomViewPager customViewPager;

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

//        // Get the ViewPager and set it's PagerAdapter so that it can display items
//        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewpager);
//        viewPager.setAdapter(new AdapterPagerAdapter(getSupportFragmentManager(),
//                SignupActivity.this));



        customViewPager = (CustomViewPager) findViewById(R.id.viewpager);
        customViewPager.setAdapter(new AdapterPagerAdapter(getSupportFragmentManager(),
                 SignupActivity.this));
        customViewPager.setSwipeable(false);

        //Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(customViewPager);

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
    }
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
