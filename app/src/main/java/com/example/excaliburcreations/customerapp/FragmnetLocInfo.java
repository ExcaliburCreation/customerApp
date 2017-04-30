package com.example.excaliburcreations.customerapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmnetLocInfo extends Fragment {
    public static final String TAG = "LOC_PAGE";

    private EditText editTextCity1;
    private EditText editTextArea1;
    public String getCity;
    public String getArea ;
    private Button btnContinue;

    public static FragmnetLocInfo newInstance(int page) {
        FragmnetLocInfo fragment = new FragmnetLocInfo();
        Bundle args = new Bundle();
        args.putInt(TAG, page);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmnet_loc_info, container, false);
        editTextArea1 = (EditText) view.findViewById(R.id.editTextArea1);
        editTextCity1 = (EditText) view.findViewById(R.id.editTextCity1);

//        Bundle args = getArguments();
//        if(args != null) {
//            getCity = args.getString("mCity");
//            getArea = args.getString("mArea");
//        }

        getCity = ClassCollectInfo.city;
        getArea = ClassCollectInfo.area;

//        Log.d("frag", ClassCollectInfo.area);

        if(getCity!=null && getArea!=null) {
            Log.d(TAG, getCity);
            Log.d(TAG, getArea);
        }
        else {
            Log.d(TAG, "args null");
        }

        editTextCity1.setText(ClassCollectInfo.city);
        editTextArea1.setText(ClassCollectInfo.area);
        return view;
    }
}

