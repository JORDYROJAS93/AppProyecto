package com.example.appproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MenuActivity : AppCompatActivity() {
    private lateinit var btnDocente:Button
    private lateinit var btnMedicamento:Button
    private lateinit var btnAlumno:Button
    private lateinit var btnCompra:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.menu_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnDocente=findViewById(R.id.btnVentanaDocente)
        btnMedicamento=findViewById(R.id.btnVentanaMedicamento)
        btnAlumno=findViewById(R.id.btnVentanaAlumno)
        btnCompra=findViewById(R.id.btnVentanaCompra)
        btnDocente.setOnClickListener { docente() }
        btnMedicamento.setOnClickListener { medicamento() }
        btnAlumno.setOnClickListener { alumno() }
        btnCompra.setOnClickListener { compra() }
    }
    fun docente(){
       var intent=Intent(this,ListaDocenteActivity::class.java)
        startActivity(intent)
    }
    fun medicamento(){
        var intent=Intent(this,ListaMedicamentoActivity::class.java)
        startActivity(intent)
    }
    fun alumno(){
        var intent=Intent(this,ListaAlumnoActivity::class.java)
        startActivity(intent)
    }
    fun compra(){
        //var intent=Intent(this,ListaDocenteActivity::class.java)
        //startActivity(intent)
    }
}