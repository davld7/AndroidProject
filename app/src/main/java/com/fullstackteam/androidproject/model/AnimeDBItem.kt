package com.fullstackteam.androidproject.model

data class AnimeDBItem(
    val description: String,
    val episodes: Int,
    val genres: String,
    val id: String,
    val image_url: String,
    val name: String,
    val season: String
)