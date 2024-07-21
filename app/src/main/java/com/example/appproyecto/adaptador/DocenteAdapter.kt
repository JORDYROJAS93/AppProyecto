package com.example.appproyecto.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.DocenteActualizarActivity
import com.example.appproyecto.R
import com.example.appproyecto.entidad.Docente
import com.example.appproyecto.utils.appConfig

class DocenteAdapter(var lista:ArrayList<Docente>):RecyclerView.Adapter<ViewDocente>() {
    //1. permite adicionar "item_docente" dentro del Adaptador
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewDocente {
        var info=LayoutInflater.from(parent.context).inflate(R.layout.item_docente,parent,false)
        return  ViewDocente(info)
    }
    override fun getItemCount(): Int {
       //retornar la cantidad de objetos que existen el arreglo "lista"
        return lista.size
    }
    override fun onBindViewHolder(holder: ViewDocente, position: Int) {
       //mostrar valores
        holder.tvCodigo.setText(lista.get(position).codigo.toString())
        holder.tvNombre.setText(lista.get(position).nombre)
        holder.tvPaterno.setText(lista.get(position).paterno)
        holder.tvMaterno.setText(lista.get(position).materno)
        //asignar evento click al parámetro holder
        holder.itemView.setOnClickListener {
            var intent=Intent(appConfig.CONTEXTO,DocenteActualizarActivity::class.java)
            //adicionar clave
            intent.putExtra("codigo",lista.get(position).codigo)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            ContextCompat.startActivity(appConfig.CONTEXTO,intent,null)
        }

    }
}