package com.example.excaliburcreations.customerapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.excaliburcreations.customerapp.databinding.formBind;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity /*implements Serializable, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener */ {
    private static final String TAG = "SIGN_UP";

    private formBind formBind;


    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;
    //referencing a specific database
    private DatabaseReference mDatabaseReference;
      /*
    * Authentication
    * */
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formBind = DataBindingUtil.setContentView(this,R.layout.activity_signup);

        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //giving reference till the child node of the firebase database.
        mDatabaseReference = mFirebaseDatabase.getReference().child("AccountRequest");

        formBind.eCompName.addTextChangedListener(watcher);
        formBind.ePersonName.addTextChangedListener(watcher);
        formBind.ePersonDes.addTextChangedListener(watcher);
        formBind.eCellno.addTextChangedListener(watcher);
        //formBind.eCountry.addTextChangedListener(watcher);
        //formBind.eCity.addTextChangedListener(watcher);
        formBind.eAddress.addTextChangedListener(watcher);
        formBind.eBusinessDes.addTextChangedListener(watcher);
        formBind.eComments.addTextChangedListener(watcher);
        //formBind.eTime.addTextChangedListener(watcher);
        //formBind.eCheckbox.addTextChangedListener(watcher);


        formBind.bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formBind.eCompName.getText().toString();
                formBind.ePersonName.getText().toString();
                formBind.ePersonDes.getText().toString();
                formBind.eBusinessDes.getText().toString();
                formBind.eAddress.getText().toString();
                formBind.eCellno.getText().toString();
                formBind.eCity.getSelectedItem().toString();
                formBind.eTime.getSelectedItem().toString();
                formBind.eComments.getText().toString();
                formBind.eCountry.getSelectedItem().toString();

                ClassUserInfo classUserInfo = new ClassUserInfo(formBind.eCompName.getText().toString(),formBind.ePersonName.getText().toString(),
                                                formBind.ePersonDes.getText().toString(),formBind.eBusinessDes.getText().toString(),
                                                formBind.eCellno.getText().toString(),formBind.eCity.getSelectedItem().toString(),formBind.eTime.getSelectedItem().toString(),
                                                formBind.eComments.getText().toString(),formBind.eCountry.getSelectedItem().toString(),formBind.eAddress.getText().toString());
                            mDatabaseReference.push().setValue(classUserInfo);
                formBind.eCompName.setText("");
                formBind.ePersonName.setText("");
                formBind.ePersonDes.setText("");
                formBind.eBusinessDes.setText("");
                formBind.eAddress.setText("");
                formBind.eCellno.setText("");
                //formBind.eCity.setText("");
               // formBind.eTime.setText("");
                formBind.eComments.setText("");
                //formBind.eCountry.setText("");
                Toast.makeText(SignupActivity.this, "Request successfull", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(formBind.eCompName.getText().toString().length() == 0 || formBind.ePersonName.getText().toString().length() == 0 ||
                    formBind.ePersonDes.getText().toString().length() == 0 || formBind.eBusinessDes.getText().toString().length() == 0 ||
                    formBind.eAddress.getText().toString().length() == 0 || formBind.eCellno.getText().toString().length() == 0 ||
                    formBind.eComments.getText().toString().length() == 0){
                formBind.bSend.setEnabled(false);
            }
            else{
                formBind.bSend.setEnabled(true);
            }

        }
    };
}
