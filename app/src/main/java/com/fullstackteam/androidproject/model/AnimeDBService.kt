package com.fullstackteam.androidproject.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaz que define una llamada a un servicio web para obtener una lista de anime
interface AnimeDBService{
    // Anotación que indica que se va a realizar una petición GET a la URL "animes"
    @GET("/animes/page")
    // Función que se utiliza para obtener una lista de anime utilizando la biblioteca Retrofit
    fun animeList(@Query("number") number: Int = 1): Call<AnimeList>
    @GET("/animes/total-animes-pages/")
    fun totalAnimesPages(): Call<TotalAnimesPages>
}