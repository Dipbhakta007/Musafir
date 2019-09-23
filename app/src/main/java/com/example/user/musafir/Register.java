package com.example.user.musafir;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

   private  EditText inputName,inputMobile,inputAddress;
    private  Button reg;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("SIGN UP");







       inputName=(EditText)findViewById(R.id.inputName);

        inputMobile=(EditText)findViewById(R.id.inputMobile);
        inputAddress=(EditText)findViewById(R.id.inputAddress);

        reg=(Button)findViewById(R.id.register);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                databaseReference=FirebaseDatabase.getInstance().getReference("Users");

                reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name=inputName.getText().toString();
                        final String mobile=inputMobile.getText().toString();
                        String address=inputAddress.getText().toString();


                        user u=new user(name,"+88"+mobile,address);

                        Intent intent=getIntent();
                        final String incomingMobile=intent.getStringExtra("mobile");

                        databaseReference.child(incomingMobile).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                  Toast.makeText(Register.this,"Registration Complete",Toast.LENGTH_LONG);

                                Intent intent2=new Intent(Register.this,HomeActivity.class);
                                intent2.putExtra("mobile",incomingMobile);
                                startActivity(intent2);

                            }
                        });
                    }
                });

            }
        });





    }


}
