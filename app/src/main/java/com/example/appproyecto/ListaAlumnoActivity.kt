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
import com.example.appproyecto.adaptador.AlumnoAdapter
import com.example.appproyecto.adaptador.DocenteAdapter
import com.example.appproyecto.adaptador.MedicamentoAdapter
import com.example.appproyecto.controller.DocenteController
import com.example.appproyecto.entidad.Alumno
import com.example.appproyecto.entidad.Medicamento
import com.example.appproyecto.services.ApiServiceMedicamento
import com.example.appproyecto.utils.ApiUtils
import com.example.appproyecto.utils.appConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaAlumnoActivity:AppCompatActivity() {
    private lateinit var rvAlumnos:RecyclerView
    private lateinit var btnNuevo:Button
    //
    private lateinit var BD:DatabaseReference
    //
    private lateinit var lista:ArrayList<Alumno>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.lista_alumno_activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvAlumnos=findViewById(R.id.rvAlumnos)
        btnNuevo=findViewById(R.id.btnNuevoAlumno)
        lista= ArrayList<Alumno>()
        btnNuevo.setOnClickListener {nuevo()}

        //
        FirebaseApp.initializeApp(this)
        BD=FirebaseDatabase.getInstance().reference
        cargar()

    }
    fun cargar(){
        //acceder al nodo "alumno"
        BD.child("alumno").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //bucle para realizar recorrido sobre snapshot "almacena documentos"
                  for(row in snapshot.children){
                    //convertir el valor de "row" en Alumno
                      var bean=row.getValue(Alumno::class.java)
                      lista.add(bean!!)
                  }
                  var adaptador=AlumnoAdapter(lista)
                  rvAlumnos.adapter=adaptador
                  rvAlumnos.layoutManager=LinearLayoutManager(appConfig.CONTEXTO)
            }
            override fun onCancelled(error: DatabaseError) {
                showAlert(error.message)
            }
        })
    }
    fun nuevo(){
        var intent=Intent(this,AlumnoActivity::class.java)
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