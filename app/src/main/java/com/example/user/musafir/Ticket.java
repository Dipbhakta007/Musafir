package com.example.user.musafir;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import static java.lang.Math.abs;

public class Ticket extends AppCompatActivity {

    private EditText rideID,numofPaseenger;
    private Spinner spinnerSource,spinnerDestination;
    private Button buyTicket;
    private int balanceFromDB;
    private String addressFromDB;
    private String nameFromDB;
    private String phonenoFromDB;
    private int fare;
    private int i,j,pass;
    private TextView sourcetext,desttext;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        rideID=(EditText)findViewById(R.id.rideID);

        numofPaseenger=(EditText)findViewById(R.id.numofPassenger);

        spinnerSource=(Spinner)findViewById(R.id.spinnerSource);
        spinnerDestination=(Spinner)findViewById(R.id.spinnerDestination);
        buyTicket=(Button)findViewById(R.id.buyTicket);
        sourcetext=(TextView)findViewById(R.id.a);
        desttext=(TextView)findViewById(R.id.b);

        Intent intent=getIntent();
        userID=intent.getStringExtra("mobile");

        spinnerSource.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,TicketPoints.points));

        spinnerDestination.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,TicketPoints.points));

        spinnerSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s= (String) spinnerSource.getSelectedItem();

                sourcetext.setText(s);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String d= (String) spinnerDestination.getSelectedItem();

                desttext.setText(d);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i=spinnerSource.getSelectedItemPosition();
                j=spinnerDestination.getSelectedItemPosition();

                String abc=numofPaseenger.getText().toString();
                pass=Integer.parseInt(abc);

                fare=pass*abs(TicketPoints.fares[i]-TicketPoints.fares[j]);

                String rid=rideID.getText().toString();
                jatri j=new jatri(userID,pass);

               FirebaseDatabase.getInstance().getReference("Riders").child(rid).child(userID).setValue(j).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {

                   }
               });




                FirebaseDatabase.getInstance().getReference().child("Users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                           user use=dataSnapshot.getValue(user.class);

                          balanceFromDB=use.getBalance();
                          addressFromDB=use.getAddress();
                          nameFromDB=use.getName();
                          phonenoFromDB=use.getPhoneno();




                        if(fare>balanceFromDB){
                            Toast.makeText(Ticket.this,"You have not sufficient credits",Toast.LENGTH_LONG).show();
                        }
                        else{

                            user u=new user(nameFromDB,phonenoFromDB,addressFromDB);
                            u.setBalance(balanceFromDB-fare);
                            u.setPhoneno(userID);

                            FirebaseDatabase.getInstance().getReference("Users").child(userID).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Ticket.this,"Ticket Confirmed",Toast.LENGTH_LONG);
                                }
                            });

                            Toast.makeText(Ticket.this,"Ticket Confirmed",Toast.LENGTH_LONG);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });


    }
}
