package com.example.appproyecto

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CompraActivity : AppCompatActivity() {
    private lateinit var imgFoto:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.compra_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgFoto=findViewById(R.id.imgFoto)
        datos()
    }
    fun datos(){
        var info=intent.extras!!
        var pG=info.getInt("pGenero")
        var pPeli=info.getInt("pPelicula")
        //variable ID para la pelicula
        var ID=0
        //obtener el ID de la pelicula "p"+pG+pPeli
        //                              p12
        ID=this.resources.getIdentifier("p"+pG+pPeli,
            "drawable",this.packageName)
        //mostrar ID en el aributo imgFoto
        imgFoto.setImageResource(ID)
    }

}