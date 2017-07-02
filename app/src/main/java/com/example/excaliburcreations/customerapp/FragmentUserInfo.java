package com.example.excaliburcreations.customerapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUserInfo extends Fragment {
    public static final String ARG_PAGE = "USER_PAGE";
    public static final String EXCEPTION = "NULL_POINTER_EXCEPTION";
    public int mPage;
    Button BtnNext;
    Button BtnForward;
    //Edit text..
    public EditText name;
    public EditText cnic;
    public EditText age;
    public EditText cellno;
    public EditText email;
    public EditText password;
    public EditText ntn;
    //fragments..
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    SignupActivity signupActivity;


    public static FragmentUserInfo newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentUserInfo fragment = new FragmentUserInfo();
        fragment.setArguments(args);
        return fragment;
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mPage = getArguments().getInt(ARG_PAGE);
//    }

    public FragmentUserInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_user_info, container, false);
        name = (EditText) view.findViewById(R.id.EditOwnerName);
        cnic = (EditText) view.findViewById(R.id.EditOwnerCNIC);
        age = (EditText) view.findViewById(R.id.EditOwnerAge);
        cellno = (EditText) view.findViewById(R.id.EditOwnerCell);
        email = (EditText) view.findViewById(R.id.EditOwnerEmail);
        password = (EditText) view.findViewById(R.id.EditOwnerPassword);
        ntn = (EditText) view.findViewById(R.id.EditOwnerNTN);
        BtnForward = (Button) view.findViewById(R.id.BtnForward);

//        BtnForward.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ClassCollectInfo.Oname = name.getText().toString();
//                ClassCollectInfo.Ocnic = cnic.getText().toString();
//                ClassCollectInfo.Oage = age.getText().toString();
//                ClassCollectInfo.Ocellno = cellno.getText().toString();
//                ClassCollectInfo.Oemail = email.getText().toString();
//                ClassCollectInfo.Opassword = password.getText().toString();
//                ClassCollectInfo.Ontnno = ntn.getText().toString();
//
//                name.setText("");
//                cnic.setText("");
//                age.setText("");
//                cellno.setText("");
//                email.setText("");
//                password.setText("");
//                ntn.setText("");
//
//            }
//        });


        return view;
    }

    public void storeData(View view){
        ClassCollectInfo.Oname = name.getText().toString();
        ClassCollectInfo.Ocnic = cnic.getText().toString();
        ClassCollectInfo.Oage = age.getText().toString();
        ClassCollectInfo.Ocellno = cellno.getText().toString();
        ClassCollectInfo.Oemail = email.getText().toString();
        ClassCollectInfo.Opassword = password.getText().toString();
        ClassCollectInfo.Ontnno = ntn.getText().toString();

        name.setText("");
        cnic.setText("");
        age.setText("");
        cellno.setText("");
        email.setText("");
        password.setText("");
        ntn.setText("");
    }

}
