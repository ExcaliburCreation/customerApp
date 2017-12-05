package com.example.excaliburcreations.customerapp;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentItems extends Fragment {
    //authenticating the state change
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    DatabaseReference iDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ChildEventListener sChildEventListener;
    private AdapterOrder mAdapterOrder;
    public ListView mListView;
    public String currentDateandTime;
    //global variable for firebase obj
    ClassOrder classOrder;
    ClassOrder classOrder1;

    Dialog dialog;


    public FragmentItems() {
        // Required empty public constructor

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_items, container, false);
        // Inflate the layout for this fragment
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mDatabaseReference = mFirebaseDatabase.getReference().child("Orders");
        mDatabaseReference = mFirebaseDatabase.getReference().child("Orders");

        // sDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(authKey);
        mListView = (ListView) view.findViewById(R.id.orderListView);

        final List<ClassOrder> classOrders = new ArrayList<>();
        Log.d("showdata",classOrders.toString());
        mAdapterOrder = new AdapterOrder(getActivity(), R.layout.customorder,classOrders);


        List<ClassOrder> classOrders1 = new ArrayList<>();

        Log.d("showdata",mAdapterOrder.toString());
        mListView.setAdapter(mAdapterOrder);
        Log.d("showdata","data setted");

        //creating a temprory list for items
        final ArrayList<String> arrayList = new ArrayList<String>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);

        Log.d("showdata","attaching childeventlistner");
        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.d("showdata", "child added is working");

//                    classOrder = dataSnapshot.getValue(ClassOrder.class);
//
//                    Log.d("LL",dataSnapshot.toString());
                    classOrder = new ClassOrder(dataSnapshot.child("basket").child("total").getValue().toString());
//                    ClassOrder classOrder1 = new ClassOrder();
//
//                    classOrder1 = new ClassOrder("","","",dataSnapshot.child("basket").child("items").getValue().toString());
//
//                    Log.d("testItem", classOrder1.getItemKey());
//                    Log.d("testItem1", classOrder1.toString());

                    //ClassOrder classOrderl = dataSnapshot.child("basket").child("items").getValue(ClassOrder.class);

                    GenericTypeIndicator<ArrayList<ClassOrder>> t = new GenericTypeIndicator<ArrayList<ClassOrder>>() {};
                    ArrayList<ClassOrder> yourStringArray = dataSnapshot.child("basket").child("items").getValue(t);

                    classOrder1 = new ClassOrder();
                    classOrder1 = new ClassOrder("","","",yourStringArray.get(0).getName(),"","");


                   // Toast.makeText(getContext(),yourStringArray.get(0).getName(),Toast.LENGTH_LONG).show();


                    String itemKey = dataSnapshot.child("basket").child("items").getKey();
                    String itemKey1 = dataSnapshot.child("basket").child("items").child("0").getKey();
                    String itemKey2 = dataSnapshot.child("basket").child("items").getChildren().toString();



//                    ArrayList<String> items = new ArrayList<>();
//                    items.add(itemKey);

                    //Log.d("testItem",items.toArray().toString());
                    Log.d("testItem1",itemKey1.toString());
                    Log.d("testItem2", itemKey2.toString());
                    Log.d("testItem3", yourStringArray.toString());
                    Log.d("testItem4", yourStringArray.get(0).toString());
                    Log.d("testItem5", classOrder1.getName());
                    Log.d("testItem6", classOrder1.toString());
                    Log.d("testItem7", t.toString());
//
//
//
//




                    Log.d("Basket",dataSnapshot.child("basket").toString());
                    Log.d("Items",dataSnapshot.child("basket").child("items").toString());
                    Log.d("Items1",dataSnapshot.child("basket").child("items").child("0").toString());
                    Log.d("Total",dataSnapshot.child("basket").child("total").toString());



                    mAdapterOrder.add(classOrder);
                    mAdapterOrder.add(classOrder1);


                    //setting temporary lists
//                    arrayList.add(itemPos.getItem());


                 //   Log.d("showdata", classOrder.toString());

                 //   mListView.setAdapter(mAdapterOrder);
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

//        //other listener
//            if(mChildEventListener == null) {
//            mChildEventListener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    Log.d("showdata", "child added is working");
//                    classOrder = dataSnapshot.getValue(ClassOrder.class);
//                  //  Log.d("ordercheck",classOrder.toString());
////                    Log.d("ordercheck",classOrder.getDatetime());
////                    Log.d("ordercheck",classOrder.getBasket());
//                    Log.d("ordercheck",classOrder.getTotal());
//
//                    mAdapterOrder.add(classOrder);
//
//
//                    //setting temporary lists
//                    arrayList.add(itemPos.getItem());
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

        //listview click items
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                classOrder = classOrders.get(position);
                Log.d("checkingpos",classOrder.toString());
                Log.d("showdata","attaching childeventlistner");

                //setting temporary lists
                arrayList.add(classOrder.getItem());
                Log.d("checkingpos",classOrder.getItem().toString());


                Log.d("showdata", classOrder.toString());

                mListView.setAdapter(mAdapterOrder);



                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_layout);


                Button dBDismiss = (Button) dialog.findViewById(R.id.bDismiss);
                Button dBDeclilne = (Button) dialog.findViewById(R.id.bDecline);
                TextView dName = (TextView) dialog.findViewById(R.id.tdhName);
                TextView dAddress = (TextView) dialog.findViewById(R.id.tdhAddress);
                TextView dContact = (TextView) dialog.findViewById(R.id.tdhContactno);
                TextView dAmount = (TextView) dialog.findViewById(R.id.tdhAmount);
                TextView dPay = (TextView) dialog.findViewById(R.id.tdhPaymethod);

                dName.setText(mAdapterOrder.getItem(position).getConsigneeName());
                dAddress.setText(mAdapterOrder.getItem(position).getAddress());
                dContact.setText(mAdapterOrder.getItem(position).getContactno());
                dAmount.setText(mAdapterOrder.getItem(position).getAmount());
                dPay.setText(mAdapterOrder.getItem(position).getPayMethod());

                Button dBGO = (Button) dialog.findViewById(R.id.bGo);

                ListView dListview = (ListView) dialog.findViewById(R.id.dialog_listview);
                dListview.setAdapter(arrayAdapter);

                dBDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();



            }
        });


        return view;

    }
}


