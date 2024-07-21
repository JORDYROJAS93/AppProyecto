package com.example.appproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class CineActivity : AppCompatActivity() {
    private lateinit var txtCliente: TextInputEditText
    private lateinit var rbtnDramatica:RadioButton
    private lateinit var rbtnInfantiles:RadioButton
    private lateinit var spnPelicula:AutoCompleteTextView
    private lateinit var btnCompra: Button
    //
    var posGenero=-1
    var posPelicula=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cine_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtCliente=findViewById(R.id.txtNombre)
        rbtnDramatica=findViewById(R.id.rbtnDramatica)
        rbtnInfantiles=findViewById(R.id.rbtnInfantiles)
        spnPelicula=findViewById(R.id.spnPelicula)
        btnCompra=findViewById(R.id.btnGrabarDocente)
        btnCompra.setOnClickListener { compra() }

        rbtnDramatica.setOnClickListener { dramatica() }
        rbtnInfantiles.setOnClickListener { infantiles() }
    }
    fun dramatica(){
        //arreglo
        var peliculas= arrayOf("Lo imposible","12 años de esclavitud","Historias cruzadas")
        //adaptador
        var adaptador=ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,peliculas)
        //asignar objeto adaptador al atributo spnPelicula
        spnPelicula.setAdapter(adaptador)
        posGenero=0
    }
    fun infantiles(){

        posGenero=1
    }

    fun compra(){
        var data=Intent(this,CompraActivity::class.java)
        data.putExtra("pGenero",posGenero)
        posPelicula=2
        data.putExtra("pPelicula",posPelicula)
        startActivity(data)
    }
}
















