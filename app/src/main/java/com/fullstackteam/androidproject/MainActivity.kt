package com.fullstackteam.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.fullstackteam.androidproject.databinding.ActivityMainBinding
import com.fullstackteam.androidproject.model.AnimeDBClient
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            textMainMenu.text = "Menú Principal"
            buttonAnimeList.text = "Lista de Animes"
        }
        // Mediante una solicitud GET hecha a la API, imprimir la cantidad de animes que hay en la base de datos.
        thread{
            val animeList = AnimeDBClient.service.animeList()
            val body = animeList.execute().body()
            if (body != null)
                Log.d("MainActivity", "Anime count: ${body.size}")
        }
    }
    fun changeAnimeList(view: View){
        val intent = Intent(this, AnimeListActivity::class.java)
        startActivity(intent)
    }
}