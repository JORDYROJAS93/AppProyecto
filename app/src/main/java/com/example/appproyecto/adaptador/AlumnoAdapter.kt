package com.example.appproyecto.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.AlumnoActualizarActivity
import com.example.appproyecto.DocenteActualizarActivity
import com.example.appproyecto.MedicamentoActualizarActivity
import com.example.appproyecto.R
import com.example.appproyecto.entidad.Alumno
import com.example.appproyecto.entidad.Docente
import com.example.appproyecto.entidad.Medicamento
import com.example.appproyecto.utils.appConfig

class AlumnoAdapter(var lista:List<Alumno>):RecyclerView.Adapter<ViewAlumno>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAlumno {
        var info=LayoutInflater.from(parent.context).inflate(R.layout.item_alumno,parent,false)
        return  ViewAlumno(info)
    }
    override fun getItemCount(): Int {
        return lista.size
    }
    override fun onBindViewHolder(holder: ViewAlumno, position: Int) {
       //mostrar valores
        holder.tvDni.setText(lista.get(position).dni)
        holder.tvNombre.setText(lista.get(position).nombre)
        holder.itemView.setOnClickListener {
            var intent=Intent(appConfig.CONTEXTO,AlumnoActualizarActivity::class.java)
            //adicionar clave
            intent.putExtra("dni",lista.get(position).dni)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            ContextCompat.startActivity(appConfig.CONTEXTO,intent,null)
        }
    }
}