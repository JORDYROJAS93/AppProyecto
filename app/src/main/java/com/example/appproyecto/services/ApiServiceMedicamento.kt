package com.example.appproyecto.services

import com.example.appproyecto.entidad.Medicamento
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServiceMedicamento {

    @GET("/medicamento/lista")
    fun findAll(): Call<List<Medicamento>>
    @POST("/medicamento/registrar")
    fun save(@Body med:Medicamento): Call<Medicamento>
    @GET("/medicamento/buscar/{codigo}")
    fun findById(@Path("codigo") cod:Int):Call<Medicamento>
    @PUT("/medicamento/actualizar")
    fun update(@Body med:Medicamento): Call<Medicamento>
    @DELETE("/medicamento/eliminar/{codigo}")
    fun deleteById(@Path("codigo") cod:Int) :Call<Void>
}