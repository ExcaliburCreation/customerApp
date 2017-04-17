package com.example.excaliburcreations.customerapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmnetLocInfo extends Fragment{
    public static final String TAG = "LOC_PAGE";


    private EditText editTextCity;
    private EditText editTextArea;
    private Button btnContinue;


    public static FragmnetLocInfo newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(TAG, page);
        FragmnetLocInfo fragment = new FragmnetLocInfo();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmnetLocInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmnet_loc_info, container, false);


        return view;
    }


}
