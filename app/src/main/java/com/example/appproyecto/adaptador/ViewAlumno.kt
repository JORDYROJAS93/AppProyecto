package com.example.appproyecto.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appproyecto.R

class ViewAlumno(vista:View):RecyclerView.ViewHolder(vista) {
    //atributo
    var imgFoto:ImageView
    var tvDni:TextView
    var tvNombre:TextView
    //bloque init
    init {
        imgFoto=vista.findViewById(R.id.imgFotoAlumno)
        tvDni=vista.findViewById(R.id.tvDniAlumno)
        tvNombre=vista.findViewById(R.id.tvNombreAlumno)
    }
}
