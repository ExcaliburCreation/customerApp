package com.example.excaliburcreations.customerapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.excaliburcreations.customerapp.databinding.formBind;

public class SignupActivity extends AppCompatActivity /*implements Serializable, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener */ {
    private static final String TAG = "SIGN_UP";

    private formBind formBind;


    Button btnNext;
    Button btnTrackMe;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formBind = DataBindingUtil.setContentView(this,R.layout.activity_signup);

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
