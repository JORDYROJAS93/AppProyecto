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
import com.example.appproyecto.entidad.Medicamento
import com.example.appproyecto.services.ApiServiceMedicamento
import com.example.appproyecto.utils.ApiUtils
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class nMedicamentoActualizarActivity : AppCompatActivity() {
    private lateinit var txtCodigo:TextInputEditText
    private lateinit var txtNombre:TextInputEditText
    private lateinit var txtStock:TextInputEditText
    private lateinit var txtPrecio:TextInputEditText
    private lateinit var btnGrabar:Button
    private lateinit var btnCerrar:Button
    private lateinit var btnEliminar:Button
    //
    private lateinit var api:ApiServiceMedicamento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.medicamento_actualizar_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtCodigo=findViewById(R.id.txtCodigoMedicamentoActualizar)
        txtNombre=findViewById(R.id.txtNombreMedicamentoActualizar)
        txtStock=findViewById(R.id.txtStockMedicamentoActualizar)
        txtPrecio=findViewById(R.id.txtPrecioMedicamentoActualizar)
        btnGrabar=findViewById(R.id.btnActualizarMedicamento)
        btnCerrar=findViewById(R.id.btnCerrarMedicamentoActualizar)
        btnEliminar=findViewById(R.id.btnEliminarMedicamento)
        //
        api=ApiUtils.getAPIMedicamento()
        btnGrabar.setOnClickListener { grabar() }
        btnCerrar.setOnClickListener { cerrar() }
        btnEliminar.setOnClickListener { eliminar() }
        mostrarDatos()
    }
    fun grabar(){
        //leer controles
        var cod=txtCodigo.text.toString().toInt()
        var nom=txtNombre.text.toString()
        var sto=txtStock.text.toString().toInt()
        var pre=txtPrecio.text.toString().toDouble()
        var bean=Medicamento(cod,nom,sto,pre)
        //invocar a la función update
        api.update(bean).enqueue(object:Callback<Medicamento>{
            override fun onResponse(call: Call<Medicamento>, response: Response<Medicamento>) {
                if(response.isSuccessful)
                    showAlert("Medicamneto Actualizado")
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
    fun eliminar(){
        var cod=txtCodigo.text.toString().toInt()
        showAlertElminar("Seguro de eliminar Medicamento con ID : "+cod,cod)
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
        //obtener valor de la clave "codigo"
        var cod=info.getInt("codigo")
        //invocar a la función findById
        api.findById(cod).enqueue(object:Callback<Medicamento>{
            override fun onResponse(call: Call<Medicamento>, response: Response<Medicamento>) {
                if(response.isSuccessful){
                    var data=response.body()!!
                    txtCodigo.setText(data.codigo.toString())
                    txtNombre.setText(data.nombre)
                    txtStock.setText(data.stock.toString())
                    txtPrecio.setText(data.precio.toString())
                }
            }
            override fun onFailure(call: Call<Medicamento>, t: Throwable) {
                showAlert(t.localizedMessage)
            }
        })
    }
    fun showAlertElminar(men:String,cod:Int){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("SISTEMA")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",DialogInterface.OnClickListener {
                dialogInterface: DialogInterface, i: Int ->
                    //invocar al método deleteById
           api.deleteById(cod).enqueue(object:Callback<Void>{
               override fun onResponse(call: Call<Void>, response: Response<Void>) {
                   if(response.isSuccessful)
                       showAlert("Medicamento eliminado")
               }
               override fun onFailure(call: Call<Void>, t: Throwable) {
                   showAlert(t.localizedMessage)
               }
           })
        })
        builder.setNegativeButton("Cancelar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
}