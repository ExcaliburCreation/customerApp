package com.example.excaliburcreations.customerapp;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //authenticating the state change
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseDatabase mFirebaseDatabase;
//    DatabaseReference mDatabaseReference;
    DatabaseReference sDatabaseReference;
   // private ChildEventListener mChildEventListener;
    private ChildEventListener sChildEventListener;
//    private AdapterOrder mAdapterOrder;
//    public ListView mListView;
    public String currentDateandTime;
    //global variable for firebase obj
    //ClassOrder classOrder;
    ClassStore classStore;
    //to get the particular item position
    ClassOrder itemPos;

    //Dialog
    Dialog dialog;
    final Context context = this;

    //variables
    public TextView name;
    public TextView storeName;


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public final String sName = ClassProfileInfo.name;
    SharedPreferences prefs;
    String myVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //open a fragment by default
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.home_frag, new FragmentItems());
        tx.commit();

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        //getting auth key
//        Intent intent = getIntent();
//        final String authKey = intent.getStringExtra("username");
        //final String myKey = authKey;
 //       Log.d("myvariable",authKey);



        //mywork

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
      //  mDatabaseReference = mFirebaseDatabase.getReference().child("Orders");
       // sDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(authKey);
        //mListView = (ListView) findViewById(R.id.orderListView);

        //Navigation View
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        View header=mNavigationView.getHeaderView(0);

        name = (TextView) header.findViewById(R.id.tUsername);
        storeName = (TextView)header.findViewById(R.id.tStoreName);

        //setting current time and date.
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy KK:mm");
        currentDateandTime = sdf.format(new Date());

        //initializing activity textview and its adapter


//        final List<ClassOrder> classOrders = new ArrayList<>();
//        Log.d("showdata",classOrders.toString());
//        mAdapterOrder = new AdapterOrder(this, R.layout.customorder,classOrders);
//        Log.d("showdata",mAdapterOrder.toString());
//        mListView.setAdapter(mAdapterOrder);
//        Log.d("showdata","data setted");
//
//        //creating a temprory list for items
//        final ArrayList<String> arrayList = new ArrayList<String>();
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
//
//        Log.d("showdata","attaching childeventlistner");
//        if(mChildEventListener == null) {
//            mChildEventListener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    Log.d("showdata", "child added is working");
//                    classOrder = dataSnapshot.getValue(ClassOrder.class);
//                    mAdapterOrder.add(classOrder);
//
//
//                    //setting temporary lists
////                    arrayList.add(itemPos.getItem());
//
//
//                    Log.d("showdata", classOrder.toString());
//
//                    mListView.setAdapter(mAdapterOrder);
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            };
//
//            mDatabaseReference.addChildEventListener(mChildEventListener);
//        }

        //attaching auth listener to get uid
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    //user is signed in
                    Toast.makeText(context, "user is signed in", Toast.LENGTH_SHORT).show();
                    Log.d("myuserid",mFirebaseAuth.getCurrentUser().getUid());

                    sDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid()).child("Profile");

                    if(sChildEventListener == null) {
                        sChildEventListener = new ChildEventListener() {
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
                                storeName.setText(classStore.getShopName());
                                Log.d("checkname","shopname :"+ classStore.getShopName());
                                name.setText(classStore.getsName());

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

                        sDatabaseReference.addChildEventListener(sChildEventListener);
                    }

                    //Toast.makeText(MainActivity.this, "You're Signed in Welcome to the Friendly Chat App", Toast.LENGTH_SHORT).show();

                } else {
                    //user is signed out
                    Toast.makeText(context, "not sign in", Toast.LENGTH_SHORT).show();
                }

            }
        };

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                classOrder = classOrders.get(position);
//                Log.d("checkingpos",classOrder.toString());
//                Log.d("showdata","attaching childeventlistner");
//
//                            //setting temporary lists
//                            arrayList.add(classOrder.getItem());
//                            Log.d("checkingpos",classOrder.getItem().toString());
//
//
//                            Log.d("showdata", classOrder.toString());
//
//                            mListView.setAdapter(mAdapterOrder);
//
//
//
//                dialog = new Dialog(context);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.dialog_layout);
//
//
//                Button dBDismiss = (Button) dialog.findViewById(R.id.bDismiss);
//                Button dBDeclilne = (Button) dialog.findViewById(R.id.bDecline);
//                Button dBGO = (Button) dialog.findViewById(R.id.bGo);
//
//                ListView dListview = (ListView) dialog.findViewById(R.id.dialog_listview);
//                dListview.setAdapter(arrayAdapter);
//
//                dBDismiss.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//
//
//
//            }
//        });

//        prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        myVariable = prefs.getString(Name, sName);



        // Toast.makeText(context, myVariable, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(Name, sName);
//        editor.apply();
//        editor.commit();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(Name, sName);
//        editor.apply();
//        editor.commit();
//    }

    @Override
    protected void onPause() {
        super.onPause();
//        prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(Name, sName);
//        editor.apply();
//        editor.commit();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        prefs = getSharedPreferences(MyPREFERENCES, 0);
//        myVariable = prefs.getString(Name, sName);
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        prefs = getSharedPreferences(MyPREFERENCES, 0);
//        myVariable = prefs.getString(Name, sName);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement(
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_frag,new FragmentItems()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_frag,new FragmentProfile()).commit();
                break;
            case R.id.nav_rider:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_frag,new FragmentRider()).commit();
                break;
            default:
                getSupportFragmentManager().beginTransaction().add(R.id.home_frag,new FragmentItems()).commit();
        }

//        if (id == R.id.nav_home) {
//            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
//            startActivity(intent);
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.home_frag,new FragmentItems()).commit();
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
