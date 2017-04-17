package com.example.excaliburcreations.customerapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStoreInfo extends Fragment{
        public static final String ARG_PAGE = "STORE_PAGE";
        public int mPage;

        public static FragmentStoreInfo newInstance(int page) {
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            FragmentStoreInfo fragment = new FragmentStoreInfo();
            fragment.setArguments(args);
            return fragment;
        }
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            mPage = getArguments().getInt(ARG_PAGE);
//        }

    public FragmentStoreInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_store_info, container, false);
    }

}
