package com.example.appproyecto

import android.content.DialogInterface
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
import com.example.appproyecto.entidad.Docente
import com.google.android.material.textfield.TextInputEditText

class DocenteActualizarActivity : AppCompatActivity() {
    private lateinit var txtCodigo:TextInputEditText
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtPaterno:TextInputEditText
    private lateinit var txtMaterno:TextInputEditText
    private lateinit var txtSueldo:TextInputEditText
    private lateinit var txtHijos:TextInputEditText
    private lateinit var spnSexo:AutoCompleteTextView
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.docente_actualizar_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtCodigo=findViewById(R.id.txtCodigoActualizar)
        txtNombre=findViewById(R.id.txtNombreActualizar)
        txtPaterno=findViewById(R.id.txtPaternoActualizar)
        txtMaterno=findViewById(R.id.txtMaternoActualizar)
        txtSueldo=findViewById(R.id.txtSueldoActualizar)
        txtHijos=findViewById(R.id.txtHijosActualizar)
        spnSexo=findViewById(R.id.spnSexoActualizar)
        btnGrabar=findViewById(R.id.btnActualizarDocente)
        btnCerrar=findViewById(R.id.btnCerrarDocenteActualizar)
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
        mostrarDatos()
    }
    fun grabar(){
        //leer controles
        var cod=txtCodigo.text.toString().toInt()
        var nom=txtNombre.text.toString()
        var pat=txtPaterno.text.toString()
        var mat=txtMaterno.text.toString()
        var sexo=spnSexo.text.toString()
        var sueldo=txtSueldo.text.toString().toDouble()
        var hijos=txtHijos.text.toString().toInt()
        //crear objeto de la clase Docente
        var bean=Docente(cod,nom,pat,mat,sexo,sueldo,hijos,"")
        //invocar al método update
        var salida=DocenteController().update(bean)
        //validar salida
        if(salida>0)
            showAlert("Docente actualizado")
        else
            showAlert("Error en la actualización")
    }
    fun cerrar(){
        //var intent=Intent(this,ListaDocenteActivity::class.java)
        //startActivity(intent)
        var cod=txtCodigo.text.toString().toInt()
        showAlertElminar("Seguro de eliminar Docente con ID : "+cod,cod)
    }
    fun showAlert(men:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("SISTEMA")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
    fun mostrarDatos(){
        //recuperar claves
        var info=intent.extras!!
        //invocar función findById
        var bean=DocenteController().findById(info.getInt("codigo"))
        //mostrar en los atributos el contenido de info
        txtCodigo.setText(bean.codigo.toString())
        txtNombre.setText(bean.nombre)
        txtPaterno.setText(bean.paterno)
        txtMaterno.setText(bean.materno)
        spnSexo.setText(bean.sexo,false)
        txtSueldo.setText(bean.sueldo.toString())
        txtHijos.setText(bean.hijos.toString())
    }
    fun showAlertElminar(men:String,cod:Int){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("SISTEMA")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",DialogInterface.OnClickListener {
                dialogInterface: DialogInterface, i: Int ->
                    //invocar al método delete
                    var salida=DocenteController().delete(cod)
                    if(salida>0)
                        showAlert("Docente eliminado")
                    else
                        showAlert("Error en la elimnación")

        })
        builder.setNegativeButton("Cancelar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
}