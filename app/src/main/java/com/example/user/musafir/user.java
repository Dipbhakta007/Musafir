package com.example.user.musafir;

/**
 * Created by USER on 9/23/2019.
 */

public class user {

    private String Name;
    private String Phoneno;
    private String Address;
    private int Balance;

    user(){

    }

    user(String n,String m,String address){
        Name=n;
        Phoneno=m;
        Address=address;

        Balance=1000;
    }

    void setName(String name){
        Name=name;
    }
    void setPhoneno(String phoneno){
        Phoneno=phoneno;
    }
    void setAddress(String address){
        Address=address;
    }
    void setBalance(int balance){
        Balance=balance;
    }

    String getName(){
        return Name;
    }
    String getPhoneno(){
        return Phoneno;
    }
    String getAddress(){
        return Address;
    }
    int getBalance(){
        return Balance;
    }
}
