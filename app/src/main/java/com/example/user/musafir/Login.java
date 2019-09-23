package com.example.user.musafir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
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

public class Login extends AppCompatActivity {


    EditText loginMobile;
    Button login;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("LOGIN");


        loginMobile=(EditText)findViewById(R.id.loginMobile);

        login=(Button)findViewById(R.id.login);
        //sgnup=(Button)findViewById(R.id.signup);


        // String mobile=loginMobile.getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobile=loginMobile.getText().toString();

                if(mobile.isEmpty() || mobile.length()!=11){
                    Toast.makeText(Login.this,"Enter a valid phone no.",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(Login.this, VerifyPhoneActivity.class);
                    intent.putExtra("mobile",mobile);
                    startActivity(intent);
                }
            }
        });



    }




}
