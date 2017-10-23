package com.example.excaliburcreations.customerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ActivityRider_reg extends AppCompatActivity {
    Button btnAdd;
    Button btnCancel;
    TextView imagePicker;
    EditText editName;
    EditText editContact;
    EditText editCnic;
    private static final int RC_PHOTO_PICKER = 2;

    //entry point of a firebase
    private FirebaseDatabase mFirebaseDatabase;

    //referencing a specific database
    private DatabaseReference mDatabaseReference;

    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotoStorageReference;
    //authenticating the state change
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    Uri selectedImageUri;
    Uri downloadUrl;

    ClassRider mClassRider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_reg);

        btnAdd = (Button) findViewById(R.id.btnInsertRider);
        editName = (EditText) findViewById(R.id.editName);
        editContact = (EditText) findViewById(R.id.editContact);
        editCnic = (EditText) findViewById(R.id.editcnic);

        //main access point of our database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mChatPhotoStorageReference = mFirebaseStorage.getReference().child("RiderImages");
        mDatabaseReference = mFirebaseDatabase.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid()).child("Riders");



//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
//                if(mFirebaseUser != null){
//                    //giving reference till the child node of the firebase database.
//                    //chat_photos is our folder in storage to store photos.
////                    mChatPhotoStorageReference = mFirebaseStorage.getReference().child("Shopkeepers").child(mFirebaseAuth.getCurrentUser().getUid()).child("RiderImages");
//
//
//                }
//
//            }
//        };


        imagePicker = (TextView) findViewById(R.id.txtInsertImage);

        imagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("checkpicker","starting picker intent");
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
                Log.d("checkpicker", "closing picker intent");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("urlcheck",downloadUrl.toString());
                mClassRider = new ClassRider(editName.getText().toString(), editContact.getText().toString(), editCnic.getText().toString(),downloadUrl.toString(),"Idle");
                Log.d("urlcheck",downloadUrl.toString());
                mDatabaseReference.push().setValue(mClassRider);

                Intent intent = new Intent(ActivityRider_reg.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRider_reg.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK){
            selectedImageUri = data.getData();
            imagePicker.setText(selectedImageUri.getLastPathSegment());
            Log.d("checkpicker","selectedimage :"+selectedImageUri.toString());

            StorageReference photoRef = mChatPhotoStorageReference.child(selectedImageUri.getLastPathSegment());
            Log.d("checkpicker","photoRef :"+ photoRef.toString());
            Log.d("checkpicker", "chatstorageref :"+ mChatPhotoStorageReference.toString());

            photoRef.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    downloadUrl = taskSnapshot.getDownloadUrl();
                    Log.d("checkpicker","downloadurl :"+ downloadUrl.toString());

                }
            });
        }
    }
}
