package com.example.excaliburcreations.customerapp;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
public class FragmentRider extends Fragment {
    Button btnAdd;
    ListView mRiderList;
    AdapterRider mAdapterRider;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private ClassRider mClassRider;
    private Dialog dialog;



    public FragmentRider() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_rider, container, false);

        //firebase
        // Inflate the layout for this fragment
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid()).child("Riders");

        //add childlistener
        if(mChildEventListener == null){
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mClassRider = dataSnapshot.getValue(ClassRider.class);
                    mAdapterRider.add(mClassRider);

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
        mRiderList = (ListView) view.findViewById(R.id.listRider);

        List<ClassRider> classRiders = new ArrayList<>();
        mAdapterRider = new AdapterRider(getActivity(),R.layout.custom_rider,classRiders);
        mRiderList.setAdapter(mAdapterRider);



        btnAdd = (Button) view.findViewById(R.id.btnAddRider);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityRider_reg.class);
                startActivity(intent);
            }
        });

        //adding dialog
        mRiderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_riderlayout);

                TextView name = (TextView) dialog.findViewById(R.id.txtEditRiderName);
                TextView contact = (TextView) dialog.findViewById(R.id.txtEditRiderContact);
                TextView cnic = (TextView) dialog.findViewById(R.id.txtEditRiderCnic);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.rider_image);

                Log.d("dialogcheck","name: "+ mAdapterRider.getItem(position).getRiderName());
                name.setText(mAdapterRider.getItem(position).getRiderName());
                contact.setText(mAdapterRider.getItem(position).getRiderContact());
                cnic.setText(mAdapterRider.getItem(position).getRiderCnic());
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);



            }
        });


        return view;
    }

}
