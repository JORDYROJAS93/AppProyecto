package com.example.appproyecto.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.R

class ViewDocente(vista:View):RecyclerView.ViewHolder(vista) {
    //atributo
    var imgFoto:ImageView
    var tvCodigo:TextView
    var tvNombre:TextView
    var tvPaterno:TextView
    var tvMaterno:TextView
    //bloque init
    init {
        imgFoto=vista.findViewById(R.id.imgFotoDocente)
        tvCodigo=vista.findViewById(R.id.tvCodigo)
        tvNombre=vista.findViewById(R.id.tvNombre)
        tvPaterno=vista.findViewById(R.id.tvPaterno)
        tvMaterno=vista.findViewById(R.id.tvMaterno)
    }
}
