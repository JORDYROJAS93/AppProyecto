package com.example.appproyecto.utils

import com.example.appproyecto.services.ApiServiceMedicamento

class ApiUtils {

    companion object {
        val BASE_URL="https://puedeser.onrender.com"
        fun getAPIMedicamento(): ApiServiceMedicamento {
            return RetrofitClient.getClient(BASE_URL).create(ApiServiceMedicamento::class.java)
        }
    }
}