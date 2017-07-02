package com.example.excaliburcreations.customerapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBankInfo extends Fragment {
    public static final String ARG_PAGE = "BANK_PAGE";
    public int mPage;

    public static FragmentBankInfo newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentBankInfo fragment = new FragmentBankInfo();
        fragment.setArguments(args);
        return fragment;
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mPage = getArguments().getInt(ARG_PAGE);
//    }


    public FragmentBankInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_bank_info, container, false);
        return view;
    }

}
