package com.example.appproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.adaptador.DocenteAdapter
import com.example.appproyecto.controller.DocenteController

class ListaDocenteActivity:AppCompatActivity() {
    private lateinit var rvDocentes:RecyclerView
    private lateinit var btnNuevo:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.lista_docente_activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvDocentes=findViewById(R.id.rvDocentes)
        btnNuevo=findViewById(R.id.btnNuevoDocente)
        btnNuevo.setOnClickListener {nuevo()}
        //obtener la lista de los docentes
        var info=DocenteController().findAll()
        //crear adaptador
        var adaptador=DocenteAdapter(info)
        //enviar el objeto "adapatdor" al atributo rvDocentes
        rvDocentes.adapter=adaptador
        //mostrar los docentes en forma lineal
        rvDocentes.layoutManager=LinearLayoutManager(this)
    }
    fun nuevo(){
        var intent=Intent(this,DocenteActivity::class.java)
        startActivity(intent)
    }
}