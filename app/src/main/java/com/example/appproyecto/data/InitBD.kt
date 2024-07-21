package com.example.appproyecto.data

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appproyecto.utils.appConfig

class InitBD:SQLiteOpenHelper(appConfig.CONTEXTO,
                             appConfig.nombreBD,null,
                             appConfig.VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table tb_docente(" +
                    "cod integer primary key autoincrement,"+
                    "nom varchar(30),"+
                    "pat varchar(30),"+
                    "mat varchar(30),"+
                    "sexo varchar(15),"+
                    "sue double,"+
                    "hijos int,"+
                    "foto varchar(15))")
        //registro
        db.execSQL("insert into tb_docente values(null,'Ana','Soto','Ayala','Femenino',2500.0,0,'d3')")




    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}