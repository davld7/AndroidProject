package com.fullstackteam.androidproject.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimeDBClient {
    private val retrofit = Retrofit.Builder().baseUrl("https://fastapi-1-v7692141.deta.app/").addConverterFactory(GsonConverterFactory.create()).build()
    val service: AnimeDBService = retrofit.create(AnimeDBService::class.java)
}