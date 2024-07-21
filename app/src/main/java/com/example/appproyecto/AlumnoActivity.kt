package com.example.appproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appproyecto.controller.DocenteController
import com.example.appproyecto.entidad.Alumno
import com.example.appproyecto.entidad.Docente
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AlumnoActivity : AppCompatActivity() {
    private lateinit var txtDni:TextInputEditText
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtPaterno:TextInputEditText
    private lateinit var txtMaterno:TextInputEditText
    private lateinit var spnSexo:AutoCompleteTextView
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button
    //atributo de base de datos
    private lateinit var BD:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.alumno_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtDni=findViewById(R.id.txtDniAlumno)
        txtNombre=findViewById(R.id.txtNombreAlumno)
        txtPaterno=findViewById(R.id.txtPaternoAlumno)
        txtMaterno=findViewById(R.id.txtMaternoAlumno)
        spnSexo=findViewById(R.id.spnSexoAlumno)
        btnGrabar=findViewById(R.id.btnGrabarAlumno)
        btnCerrar=findViewById(R.id.btnCerrarAlumno)
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
        //
        conectar()
    }
    fun grabar(){
        //leer controles
        var dni=txtDni.text.toString()
        var nom=txtNombre.text.toString()
        var pat=txtPaterno.text.toString()
        var mat=txtMaterno.text.toString()
        var sexo=spnSexo.text.toString()
        //objeto de la clase Alumno
        var alu=Alumno(dni,nom,pat,mat,sexo)
        //generar ID
        var ID=BD.push().key
        //grabar en la bd de firebase
        BD.child("alumno").child(dni).setValue(alu).
            addOnCompleteListener {
                showAlert("Alumno registrado")
            }

    }
    fun cerrar(){
        var intent=Intent(this,ListaAlumnoActivity::class.java)
        startActivity(intent)
    }
    fun showAlert(men:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("SISTEMA")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
    fun conectar(){
        //iniciar Firebase en la clase actual
        FirebaseApp.initializeApp(this)
        //referenciar la base de datos
        BD=FirebaseDatabase.getInstance().reference
    }
}