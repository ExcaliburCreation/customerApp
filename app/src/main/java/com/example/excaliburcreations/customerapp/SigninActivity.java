package com.example.excaliburcreations.customerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SigninActivity extends AppCompatActivity {
    private static final String MYTAG = "SigninActivity";
    EditText EditUserText;
    EditText EditPassText;
    Button BtnLogin;
    TextView TextSignup;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ClassOrder classOrder;
    private FirebaseUser mFirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = mFirebaseDatabase.getInstance();
        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();




        EditUserText = (EditText) findViewById(R.id.Edit_userName);
        EditPassText = (EditText) findViewById(R.id.Edit_userPassword);
        BtnLogin = (Button) findViewById(R.id.Btn_login);
        TextSignup = (TextView) findViewById(R.id.TextSignup);


//        //attaching a authentication listener
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//                // ...
//            }
//        };
//    }
    }
    //sign in with existing user
    public void Signin(View view){
        mFirebaseAuth.signInWithEmailAndPassword(EditUserText.getText().toString(),EditPassText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(MYTAG, "signInWithEmail:onComplete:" + task.isSuccessful());
//                        mDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid());
                        Log.d("useruid",mFirebaseAuth.getCurrentUser().getUid());
//                            ClassOrder classOrder = new ClassOrder("Karachi","Saddar","9:00","abc house, 123 street, xyz city","0312345678","not accepted","items","Ibad","COD","2000/-","");
//                            Log.d("connectiontest",mDatabaseReference.toString());
//                            mDatabaseReference.push().setValue(classOrder);
                        // if(mChildEventListener == null) {
//                        mChildEventListener = new ChildEventListener() {
//                            @Override
//                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                Log.d("usertest", "child added is working");
//                                classOrder = dataSnapshot.getValue(ClassOrder.class);
//                                Log.d("usertest", classOrder.toString());
//                                Log.d("usertest", classOrder.consigneeName);
//                                ClassProfileInfo.name = classOrder.consigneeName;
//                            }
//
//                            @Override
//                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                            }
//
//                            @Override
//                            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                            }
//
//                            @Override
//                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        };
//                        mDatabaseReference.addChildEventListener(mChildEventListener);

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            Log.w(MYTAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(SigninActivity.this,"User not exists",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Toast.makeText(SigninActivity.this, "Signed in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SigninActivity.this, MapsActivity.class);
//                            intent.putExtra("username",mFirebaseAuth.getCurrentUser().getUid());
//                            Log.d("idtest",mFirebaseAuth.getCurrentUser().getUid());
                            startActivity(intent);
                            }
                            Log.d("usertest","notworking");
                     //   }

                        // ...
                    }
                });
    }

    public void Signup(View view){
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }

}
