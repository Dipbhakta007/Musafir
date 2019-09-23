package com.example.user.musafir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckBalance extends AppCompatActivity {

    private TextView balanceView;
    private String userID;
    int balanceFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);

        Intent intent=getIntent();
        balanceView=(TextView)findViewById(R.id.balanceView);
        userID=intent.getStringExtra("mobile");


        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    balanceFromDB=ds.child(userID).getValue(user.class).getBalance();



                }

                balanceView.setText(String.valueOf(balanceFromDB));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
