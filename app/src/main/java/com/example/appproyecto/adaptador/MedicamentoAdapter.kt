package com.example.appproyecto.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.DocenteActualizarActivity
import com.example.appproyecto.MedicamentoActualizarActivity
import com.example.appproyecto.R
import com.example.appproyecto.entidad.Docente
import com.example.appproyecto.entidad.Medicamento
import com.example.appproyecto.utils.appConfig

class MedicamentoAdapter(var lista:List<Medicamento>):RecyclerView.Adapter<ViewMedicamento>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMedicamento {
        var info=LayoutInflater.from(parent.context).inflate(R.layout.item_medicamento,parent,false)
        return  ViewMedicamento(info)
    }
    override fun getItemCount(): Int {
        return lista.size
    }
    override fun onBindViewHolder(holder: ViewMedicamento, position: Int) {
       //mostrar valores
        holder.tvCodigo.setText(lista.get(position).codigo.toString())
        holder.tvNombre.setText(lista.get(position).nombre)
        holder.itemView.setOnClickListener {
            var intent=Intent(appConfig.CONTEXTO,MedicamentoActualizarActivity::class.java)
            //adicionar clave
            intent.putExtra("codigo",lista.get(position).codigo)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            ContextCompat.startActivity(appConfig.CONTEXTO,intent,null)
        }
    }
}