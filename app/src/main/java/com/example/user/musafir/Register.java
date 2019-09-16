package com.example.user.musafir;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    RegDB regDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("SIGN UP");

         regDB=new RegDB(this);
        //SQLiteDatabase sqLiteDatabase=regDB.getWritableDatabase();


       final EditText inputName,inputMobile,inputPass,inputRetypePass;
        Button reg;

       inputName=(EditText)findViewById(R.id.inputName);
        inputMobile=(EditText)findViewById(R.id.inputMobile);
        inputPass=(EditText)findViewById(R.id.inputPassword);
       inputRetypePass=(EditText)findViewById(R.id.inputRetypePassword);
        reg=(Button)findViewById(R.id.register);




       reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=inputName.getText().toString();
                String pass=inputPass.getText().toString();
                String rePass=inputRetypePass.getText().toString();
                String mobile=inputMobile.getText().toString();

                if(name =="" || pass=="" || rePass=="" || mobile==""){
                    Toast.makeText(getApplicationContext(),"You have to fill all the information",Toast.LENGTH_SHORT).show();
                    inputName.setText("");
                    inputMobile.setText("");
                    inputPass.setText("");
                    inputRetypePass.setText("");


                }
                else if(pass.length()<8){
                    Toast.makeText(getApplicationContext(),"Password length must be at least 8",Toast.LENGTH_SHORT).show();
                    inputPass.setText("");
                    inputRetypePass.setText("");

                }
                else if(mobile.length()!=11 || mobile.charAt(0)!='0' || mobile.charAt(1)!='1'){
                    Toast.makeText(getApplicationContext(),"You have to enter a valid mobile number",Toast.LENGTH_SHORT).show();
                    inputMobile.setText("");

                }
                else if(!pass.equals(rePass)){
                    Toast.makeText(getApplicationContext(),"Passwords don't match",Toast.LENGTH_SHORT).show();
                    inputPass.setText("");
                    inputRetypePass.setText("");

                }
                else{
                    try {
                        regDB.insert(mobile, name, pass);
                        Toast.makeText(getApplicationContext(),"Registered successfully!",Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),"An error occured",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
