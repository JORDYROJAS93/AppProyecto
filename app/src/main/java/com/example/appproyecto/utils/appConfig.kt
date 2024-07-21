package com.example.appproyecto.utils

import android.app.Application
import android.content.Context
import com.example.appproyecto.data.InitBD

class appConfig:Application() {
    //declarar atributos globales
    //declarar e inicializar atributos de tipo primitivo
    companion object{
        lateinit var CONTEXTO:Context
        lateinit var BD:InitBD
        var nombreBD="consorcio20.bd"
        var VERSION=1
    }
    //instanciar
    override fun onCreate() {
        super.onCreate()
        CONTEXTO=applicationContext
        BD=InitBD()
    }
}