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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AlumnoActualizarActivity : AppCompatActivity() {
    private lateinit var txtDni:TextInputEditText
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtPaterno:TextInputEditText
    private lateinit var txtMaterno:TextInputEditText
    private lateinit var spnSexo:AutoCompleteTextView
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button
    private lateinit var btnEliminar:Button

    //atributo de base de datos
    private lateinit var BD:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.alumno_actualizar_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtDni=findViewById(R.id.txtDniAlumnoActualizar)
        txtNombre=findViewById(R.id.txtNombreAlumnoActualizar)
        txtPaterno=findViewById(R.id.txtPaternoAlumnoActualizar)
        txtMaterno=findViewById(R.id.txtMaternoAlumnoActualizar)
        spnSexo=findViewById(R.id.spnSexoAlumnoActualizar)
        btnGrabar=findViewById(R.id.btnActualizarAlumno)
        btnCerrar=findViewById(R.id.btnCerrarAlumnoActualizar)
        btnEliminar=findViewById(R.id.btnEliminarAlumno)
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
        btnEliminar.setOnClickListener { eliminar() }
        //
        conectar()
        datos()
    }
    fun eliminar(){
        var dni=txtDni.text.toString()
        BD.child("alumno").child(dni).removeValue().addOnCompleteListener {
            showAlert("Alumno eliminado")
        }
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
                showAlert("Alumno actualizado")
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
    fun datos(){
        var info=intent.extras!!
        var DNI=info.getString("dni")
        //acceder al nodo "alumno"
        var CONSULTA=FirebaseDatabase.getInstance().getReference("alumno")
        //buscar por el campo dni
        CONSULTA.orderByChild("dni").equalTo(DNI).
            addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        var row=snapshot.children.first()
                        var bean=row.getValue(Alumno::class.java)!!
                        txtDni.setText(bean.dni)
                        txtNombre.setText(bean.nombre)
                        txtPaterno.setText(bean.paterno)
                        txtMaterno.setText(bean.materno)
                        spnSexo.setText(bean.sexo,false)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    showAlert(error.message)
                }
            })
    }


}