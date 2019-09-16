package com.example.user.musafir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    RegDB regDB;
    EditText loginMobile,loginPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("LOGIN");

        regDB=new RegDB(this);

        loginMobile=(EditText)findViewById(R.id.loginMobile);
        loginPassword=(EditText)findViewById(R.id.loginPassword);
        login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String logMob=loginMobile.getText().toString();
                String logPass=loginPassword.getText().toString();

                try{
                    if (regDB.authenticate(logMob,logPass)){
                        startActivity(new Intent(getApplicationContext(),Ticket.class));

                    }
                    else{
                        Toast.makeText(Login.this,"Username or parssword didn't match",Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e){
                    Toast.makeText(Login.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void btn_signup(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
    }

}
