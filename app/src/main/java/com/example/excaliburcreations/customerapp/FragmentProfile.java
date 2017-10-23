package com.example.excaliburcreations.customerapp;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {
    //authenticating the state change
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    //global variable for firebase obj
    ClassStore classStore;
    TextView storeName;
    TextView name;
    TextView email;
    TextView password;

    //Dialog
    Dialog dialog;
    final Context context = getContext();


    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_profile, container, false);
        //mywork

        storeName = (TextView) view.findViewById(R.id.tPstore);
        name = (TextView) view.findViewById(R.id.tPname);
        email = (TextView) view.findViewById(R.id.tPemail);
        password = (TextView) view.findViewById(R.id.tPpass);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid()).child("Profile");


        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.d("showdata", "child added is working");
//                                classOrder = dataSnapshot.getValue(ClassOrder.class);
//                                Toast.makeText(context, classOrder.getConsigneeName(), Toast.LENGTH_SHORT).show();
//                                Log.d("showdata",classOrder.getConsigneeName());
//                                ClassProfileInfo.name = classOrder.getConsigneeName();
//                                name.setText(ClassProfileInfo.name);
//                                Log.d("uservar",ClassProfileInfo.name);
                    classStore = dataSnapshot.getValue(ClassStore.class);
                    if(classStore != null) {
                        storeName.setText(classStore.getShopName());
                        Log.d("checkprofile", "name :" + classStore.getShopName());
                        name.setText(classStore.getsName());
                        email.setText(classStore.getsEmail());
                        password.setText(classStore.getsPassword());
                    }


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mDatabaseReference.addChildEventListener(mChildEventListener);
        }

//        //attaching auth listener to get uid
//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                if (firebaseUser != null) {
//                    //user is signed in
////                    Toast.makeText(context, "user is signed in", Toast.LENGTH_SHORT).show();
//                    Log.d("myuserid",mFirebaseAuth.getCurrentUser().getUid());
//                    sDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid());
//
//                    if(sChildEventListener == null) {
//                        sChildEventListener = new ChildEventListener() {
//                            @Override
//                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                Log.d("showdata", "child added is working");
////                                classOrder = dataSnapshot.getValue(ClassOrder.class);
////                                Toast.makeText(context, classOrder.getConsigneeName(), Toast.LENGTH_SHORT).show();
////                                Log.d("showdata",classOrder.getConsigneeName());
////                                ClassProfileInfo.name = classOrder.getConsigneeName();
////                                name.setText(ClassProfileInfo.name);
////                                Log.d("uservar",ClassProfileInfo.name);
//                                classStore = dataSnapshot.getValue(ClassStore.class);
//                                storeName.setText(classStore.getShopName());
//                                Log.d("checkprofile","name :"+classStore.getShopName());
//                                name.setText(classStore.getsName());
//                                email.setText(classStore.getsEmail());
//                                password.setText(classStore.getsPassword());
//
//
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
//
//                        sDatabaseReference.addChildEventListener(sChildEventListener);
//                    }
//
//                    //Toast.makeText(MainActivity.this, "You're Signed in Welcome to the Friendly Chat App", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    //user is signed out
//                    Toast.makeText(context, "not sign in", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        };
        return view;
    }

//    @Override
//    public void onPause() {
//        super.onPause();
////        prefs = PreferenceManager.getDefaultSharedPreferences(context);
////        SharedPreferences.Editor editor = prefs.edit();
////        editor.putString(Name, sName);
////        editor.apply();
////        editor.commit();
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
////        prefs = getSharedPreferences(MyPREFERENCES, 0);
////        myVariable = prefs.getString(Name, sName);
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
//    }

}
