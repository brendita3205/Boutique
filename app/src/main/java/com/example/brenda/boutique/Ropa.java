package com.example.brenda.boutique;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Brenda on 27/05/2015.
 */
class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Ropa (id_Ropa integer primary key unique, Marca text, Descripcion text,  precio integer, Talla integer, color text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Ropa");
        db.execSQL("create table Ropa (id_Ropa integer primary key unique, Marca text, Descripcion text,  precio integer, Talla integer, Color text) ");


    }
}

