package com.example.user.musafir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "ViewDatabase";
    ListView mListView;
    String userID;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth=FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                   // toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        Intent intent=getIntent();
        userID=intent.getStringExtra("mobile");

        mListView=(ListView)findViewById(R.id.listview);
       // Toast.makeText(getApplicationContext(),"hua",Toast.LENGTH_LONG).show();


               FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       for(DataSnapshot ds : dataSnapshot.getChildren()){
                           user uInfo = new user();
                           uInfo.setName(ds.child(userID).getValue(user.class).getName()); //set the name
                           uInfo.setPhoneno(ds.child(userID).getValue(user.class).getPhoneno()); //set the email

                           uInfo.setAddress(ds.child(userID).getValue(user.class).getAddress()); //set the phone_num

                           //display all the information
                           Log.d(TAG, "showData: name: " + uInfo.getName());
                           Log.d(TAG, "showData: email: " + uInfo.getPhoneno());
                           Log.d(TAG, "showData: phone_num: " + uInfo.getAddress());

                           ArrayList<String> array  = new ArrayList<>();
                           array.add(uInfo.getName());
                           array.add(uInfo.getPhoneno());
                           array.add(uInfo.getAddress());
                           ArrayAdapter adapter = new ArrayAdapter(Main2Activity.this,android.R.layout.simple_expandable_list_item_1,array);
                           mListView.setAdapter(adapter);
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



}
