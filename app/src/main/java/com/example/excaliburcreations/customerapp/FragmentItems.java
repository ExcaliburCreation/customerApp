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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
    DatabaseReference rDatabaseReference;
    DatabaseReference oDatabaseReference;

    private ChildEventListener mChildEventListener;
    private ChildEventListener rChildEventListener;

    private AdapterOrder mAdapterOrder;
    private AdapterItem mAdapterItem;
    private AdapterRiderList mAdapterRider;

    public ListView mListView;
    public ListView rListView;
    public String currentDateandTime;
    //global variable for firebase obj
    ClassOrder classOrder;
    ClassRider classRider;
    List<ClassOrder> classOrders;
    List<ClassRider> classRiders;

    //generic type indicator for item
    ArrayList<ClassOrder> yourStringArray;
    GenericTypeIndicator<ArrayList<ClassOrder>> t ;

    Dialog dialog;
    Dialog dialogGo;
    Dialog dialogConfirm;
    int i;
    int pos;


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
        mDatabaseReference = mFirebaseDatabase.getReference().child("order");
        rDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid()).child("Riders");
        oDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid()).child("Riders").child("orders");

        Log.d("showdata", "attaching childeventlistner");

            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    t = new GenericTypeIndicator<ArrayList<ClassOrder>>() {};

                    yourStringArray = dataSnapshot.getValue(t);

                    mAdapterOrder.add(yourStringArray.get(0));


                        for (i = 0; i < yourStringArray.size(); i++) {
                            mAdapterItem.add(yourStringArray.get(i));

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
                    // Failed to read value
                    Log.w("error1","Failed to read value.",databaseError.toException());
                }
            };

//        Log.d("check3", "count:"+ yourStringArray.size());

        mDatabaseReference.addChildEventListener(mChildEventListener);

        mListView = (ListView) view.findViewById(R.id.orderListView);

        classOrders = new ArrayList<>();
        Log.d("showdata", classOrders.toString());
        mAdapterOrder = new AdapterOrder(getActivity(), R.layout.customorder, classOrders);
       /////////////////////////////////////////////////////////////////
        final List<ClassOrder> classItems = new ArrayList<>();
        mAdapterItem = new AdapterItem(getActivity(), R.layout.custom_item, classItems);

        mListView.setAdapter(mAdapterOrder);

        Log.d("showdata", "data setted");

//        Log.d("check4", "count:"+ yourStringArray.size());




        //listview click items
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                Log.d("check5", "count:"+ yourStringArray.size());

                classOrder = classOrders.get(position);

                Log.d("test1",mAdapterItem.getItem(position).getName());

                pos = position;


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

                ////////////////////////////////////////////////

                ListView dListview = (ListView) dialog.findViewById(R.id.dialog_listview);



                Log.d("showdata", classOrders.toString());


                Log.d("showdata", mAdapterItem.toString());
                dListview.setAdapter(mAdapterItem);
                Log.d("showdata", "data setted");
                //////////////////////////////////////////////////



                dBDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.show();

//////////////////////////////rider go/////////////////////////////////////////////////////
                Button dBGO = (Button) dialog.findViewById(R.id.bGo);
                dBGO.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        dialogGo = new Dialog(getContext());
                        dialogGo.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogGo.setContentView(R.layout.dialog_rider_select);

                        rListView = (ListView) dialogGo.findViewById(R.id.rider);
                        classRiders = new ArrayList<>();
                        mAdapterRider = new AdapterRiderList(getActivity(), R.layout.custom_rider_select, classRiders);

                        rChildEventListener = new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                classRider = dataSnapshot.getValue(ClassRider.class);
                                mAdapterRider.add(classRider);

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

                        rListView.setAdapter(mAdapterRider);
                        rDatabaseReference.addChildEventListener(rChildEventListener);

                        Window window = dialog.getWindow();
                        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        dialogGo.show();

                        rListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                dialogGo.dismiss();

                                classRider = classRiders.get(position);
                                dialogConfirm = new Dialog(getContext());
                                dialogConfirm.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialogConfirm.setContentView(R.layout.dialog_rider_confirm);

                                ImageView imageView = (ImageView) dialogConfirm.findViewById(R.id.rc_image);
                                TextView name = (TextView) dialogConfirm.findViewById(R.id.rc_name);
                                TextView contact  = (TextView) dialogConfirm.findViewById(R.id.rc_contact);
                                TextView state = (TextView) dialogConfirm.findViewById(R.id.rc_state);
                                TextView message = (TextView) dialogConfirm.findViewById(R.id.rc_message);

                                Button send = (Button) dialogConfirm.findViewById(R.id.rc_button);

                                Glide.with(imageView.getContext())
                                        .load(mAdapterRider.getItem(position).getRiderImageUrl())
                                        .into(imageView);
                                name.setText(mAdapterRider.getItem(position).getRiderName());
                                contact.setText(mAdapterRider.getItem(position).getRiderContact());
                                state.setText(mAdapterRider.getItem(position).getRiderStatus());
                                message.setText("Are you sure to send order?");

                                send.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
//                                        String[] order = {mAdapterOrder.getItem(pos).getConsigneeName(),mAdapterOrder.getItem(pos).getAddress(),
//                                                mAdapterOrder.getItem(pos).getContactno(),mAdapterOrder.getItem(pos).getAmount(),mAdapterOrder.getItem(pos).getPayMethod()};
                                        ClassOrder c = new ClassOrder(mAdapterOrder.getItem(pos).getConsigneeName(),mAdapterOrder.getItem(pos).getContactno()
                                        ,mAdapterOrder.getItem(pos).getPayMethod(),mAdapterOrder.getItem(pos).getDatetime());

                                        ArrayList<ClassOrder> co = new ArrayList<ClassOrder>();
                                        co.add(c);
                                        oDatabaseReference.push().setValue(co);

                                        dialogConfirm.dismiss();
                                        Toast.makeText(getActivity(), "Successfully forwarded", Toast.LENGTH_SHORT).show();

                                    }
                                });

                                Window window = dialogConfirm.getWindow();
                                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                dialogConfirm.show();

                            }
                        });
                    }
                });

            }
        });


        return view;

    }



}



