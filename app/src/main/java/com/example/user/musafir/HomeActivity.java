package com.example.user.musafir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button balance,ticket,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        balance=(Button)findViewById(R.id.balance);
        ticket=(Button)findViewById(R.id.ticket);
        profile=(Button)findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                String mobile=intent.getStringExtra("mobile");
                Intent intent1=new Intent(HomeActivity.this,Main2Activity.class);
                intent1.putExtra("mobile",mobile);
                startActivity(intent1);
            }
        });

        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                String mobile=intent.getStringExtra("mobile");
                Intent intent1=new Intent(HomeActivity.this,Ticket.class);
                intent1.putExtra("mobile",mobile);
                startActivity(intent1);
            }
        });

        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                String mobile=intent.getStringExtra("mobile");
                Intent intent1=new Intent(HomeActivity.this,CheckBalance.class);
                intent1.putExtra("mobile",mobile);
                startActivity(intent1);
            }
        });
    }
}
