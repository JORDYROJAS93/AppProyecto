package com.example.appproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.adaptador.DocenteAdapter
import com.example.appproyecto.adaptador.MedicamentoAdapter
import com.example.appproyecto.controller.DocenteController
import com.example.appproyecto.entidad.Medicamento
import com.example.appproyecto.services.ApiServiceMedicamento
import com.example.appproyecto.utils.ApiUtils
import com.example.appproyecto.utils.appConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaMedicamentoActivity:AppCompatActivity() {
    private lateinit var rvMedicamentos:RecyclerView
    private lateinit var btnNuevo:Button
    private lateinit var apiMedicamento:ApiServiceMedicamento


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.lista_medicamento_activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvMedicamentos=findViewById(R.id.rvMedicamentos)
        btnNuevo=findViewById(R.id.btnNuevoMedicamento)
        btnNuevo.setOnClickListener {nuevo()}
        //
        apiMedicamento=ApiUtils.getAPIMedicamento()
        cargar()

    }
    fun cargar(){
        //invocar a la función findAll
        apiMedicamento.findAll().enqueue(object:Callback<List<Medicamento>>{
            override fun onResponse(call: Call<List<Medicamento>>,response: Response<List<Medicamento>>) {
                if(response.isSuccessful){
                    var adaptador=MedicamentoAdapter(response.body()!!)
                    rvMedicamentos.adapter=adaptador
                    rvMedicamentos.layoutManager=LinearLayoutManager(appConfig.CONTEXTO)
                }
            }
            override fun onFailure(call: Call<List<Medicamento>>, t: Throwable) {
                showAlert(t.localizedMessage)
            }
        })

    }
    fun nuevo(){
        var intent=Intent(this,MedicamentoActivity::class.java)
        startActivity(intent)
    }
    fun showAlert(men:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
}