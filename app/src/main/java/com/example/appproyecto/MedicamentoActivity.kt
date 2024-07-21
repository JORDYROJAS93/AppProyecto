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
import com.example.appproyecto.entidad.Docente
import com.example.appproyecto.entidad.Medicamento
import com.example.appproyecto.services.ApiServiceMedicamento
import com.example.appproyecto.utils.ApiUtils
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicamentoActivity : AppCompatActivity() {
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtStock:TextInputEditText
    private lateinit var txtPrecio:TextInputEditText
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button
    private lateinit var  apiMedicamento:ApiServiceMedicamento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.medicamento_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtNombre=findViewById(R.id.txtNombreMedicamento)
        txtStock=findViewById(R.id.txtStockMedicamento)
        txtPrecio=findViewById(R.id.txtPrecioMedicamento)
        btnGrabar=findViewById(R.id.btnGrabarMedicamento)
        btnCerrar=findViewById(R.id.btnCerrarMedicamento)
        apiMedicamento=ApiUtils.getAPIMedicamento()
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
    }
    fun grabar(){
        //leer controles
        var nom=txtNombre.text.toString()
        var sto=txtStock.text.toString().toInt()
        var pre=txtPrecio.text.toString().toDouble()
        var bean=Medicamento(0,nom,sto,pre)
        grabarMedicamento(bean)
    }
    fun grabarMedicamento(med:Medicamento){
        apiMedicamento.save(med).enqueue(object: Callback<Medicamento>{
            override fun onResponse(call: Call<Medicamento>, response: Response<Medicamento>) {
                if(response.isSuccessful){
                       var bean=response.body()!!
                        showAlert("Se registro medicamento con ID : "+bean.codigo.toString())
                }
            }
            override fun onFailure(call: Call<Medicamento>, t: Throwable) {
                showAlert(t.localizedMessage)
            }
        })
    }

    fun cerrar(){
        var intent=Intent(this,ListaMedicamentoActivity::class.java)
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
}