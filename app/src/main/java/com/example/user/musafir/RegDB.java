package com.example.user.musafir;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.ResultSet;

/**
 * Created by USER on 9/17/2019.
 */

public class RegDB extends SQLiteOpenHelper {

    private Context context;

    public RegDB(Context context) {
        super(context,"Login.db", null, 203);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {

           // Toast.makeText(context, "Enterd", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL("Create table user(Mobile varchar(20) primary key,name varchar(20),password varchar(20))");
            Toast.makeText(context, "Enterd", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            //Toast.makeText(context, "Enterd up", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL("drop table if exists user");
            onCreate(sqLiteDatabase);
            Toast.makeText(context, "Enterd up", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void insert(String mobile,String name,String pass){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("insert into user values('"+mobile+"','"+name+"','"+pass+"')");

    }

    public boolean authenticate(String mobile,String pass){

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("Select* from user where Mobile='"+mobile+"' and password='"+pass+"'",null);
        if(c.moveToFirst())return true;
        else return false;

    }
}
