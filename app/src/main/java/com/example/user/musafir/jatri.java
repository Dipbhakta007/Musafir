package com.example.user.musafir;

/**
 * Created by USER on 10/28/2019.
 */

public class jatri {
    private String phoneno;
    private int numberofTickets;

    jatri(){

    }
    jatri(String a,int b){
        phoneno=a;
        numberofTickets=b;

    }


    void setPhoneno(String a){
        phoneno=a;
    }
    void setNumberofTickets(int b){
        numberofTickets=b;
    }

    String getPhoneno(){
        return phoneno;

    }
    int getNumberofTickets()
    {
        return numberofTickets;
    }
}
