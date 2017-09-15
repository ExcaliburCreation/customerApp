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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private ChildEventListener mChildEventListener;
    private ChildEventListener sChildEventListener;
    private AdapterOrder mAdapterOrder;
    public ListView mListView;
    public String currentDateandTime;
    //global variable for firebase obj
    ClassOrder classOrder;
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
        mDatabaseReference = mFirebaseDatabase.getReference().child("Orders");
        // sDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(authKey);
        mListView = (ListView) view.findViewById(R.id.orderListView);

        final List<ClassOrder> classOrders = new ArrayList<>();
        Log.d("showdata",classOrders.toString());
        mAdapterOrder = new AdapterOrder(getActivity(), R.layout.customorder,classOrders);
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
                    classOrder = dataSnapshot.getValue(ClassOrder.class);
                    mAdapterOrder.add(classOrder);


                    //setting temporary lists
//                    arrayList.add(itemPos.getItem());


                    Log.d("showdata", classOrder.toString());

                    mListView.setAdapter(mAdapterOrder);
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
