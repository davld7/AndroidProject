package com.fullstackteam.androidproject.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto que se utiliza para obtener una instancia de AnimeDBService
object AnimeDBClient {
    // Creación de una instancia de la biblioteca Retrofit y configuración de la URL base y el conversor Gson
    private val retrofit = Retrofit.Builder().baseUrl("https://fastapi-1-v7692141.deta.app").addConverterFactory(GsonConverterFactory.create()).build()
     //Instancia de AnimeDBService utilizando Retrofit
    val service: AnimeDBService = retrofit.create(AnimeDBService::class.java)
}
