package com.fullstackteam.androidproject.model

import retrofit2.Call
import retrofit2.http.GET

interface AnimeDBService{
    @GET("animes")
    fun animeList(): Call<AnimeList>
}