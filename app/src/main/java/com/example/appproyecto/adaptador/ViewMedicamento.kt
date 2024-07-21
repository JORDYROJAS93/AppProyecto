package com.example.appproyecto.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.R

class ViewMedicamento(vista:View):RecyclerView.ViewHolder(vista) {
    //atributo
    var imgFoto:ImageView
    var tvCodigo:TextView
    var tvNombre:TextView
    //bloque init
    init {
        imgFoto=vista.findViewById(R.id.imgFotoMedicamento)
        tvCodigo=vista.findViewById(R.id.tvCodigoMedicamento)
        tvNombre=vista.findViewById(R.id.tvNombreMedicamento)
    }
}
