package com.example.excaliburcreations.customerapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
//        mDatabaseReference = mFirebaseDatabase.getReference().child("Orders");
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
//               ClassOrder classOrder = new ClassOrder("Karachi","Saddar","9:00","abc house, 123 street, xyz city","0312345678","not accepted","items","Ibad","COD","2000/-","");
//                Log.d("connectiontest",mDatabaseReference.toString());
//                mDatabaseReference.push().setValue(classOrder);
                Log.d("connectiontest","send successfully");
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

//                ClassUserInfo classUserInfo = new ClassUserInfo(formBind.eCompName.getText().toString(),formBind.ePersonName.getText().toString(),
//                                                formBind.ePersonDes.getText().toString(),formBind.eBusinessDes.getText().toString(),
//                                                formBind.eCellno.getText().toString(),formBind.eCity.getSelectedItem().toString(),formBind.eTime.getSelectedItem().toString(),
//                                                formBind.eComments.getText().toString(),formBind.eCountry.getSelectedItem().toString(),formBind.eAddress.getText().toString());
//                            mDatabaseReference.push().setValue(classUserInfo);

                String compName = formBind.eCompName.getText().toString();
                String perName = formBind.ePersonName.getText().toString();
                String perDes = formBind.ePersonDes.getText().toString();
                String busDes = formBind.eBusinessDes.getText().toString();
                String address = formBind.eAddress.getText().toString();
                String cellNo = formBind.eCellno.getText().toString();
                String city = formBind.eCity.getSelectedItem().toString();
                String time = formBind.eTime.getSelectedItem().toString();
                String comments = formBind.eComments.getText().toString();
                String country = formBind.eCountry.getSelectedItem().toString();

                Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
                intent.putExtra("compName",compName);
                intent.putExtra("perName", perName);
                intent.putExtra("address", address);
                intent.putExtra("perDes", perDes);
                intent.putExtra("busDes", busDes);
                intent.putExtra("address", address);
                intent.putExtra("cellNo", cellNo);
                intent.putExtra("city", city);
                intent.putExtra("time", time);
                intent.putExtra("comments", comments);
                intent.putExtra("country", country);

                startActivity(intent);


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
//                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
//                startActivity(intent);

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
