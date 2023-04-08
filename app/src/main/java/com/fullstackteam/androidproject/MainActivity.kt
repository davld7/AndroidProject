package com.fullstackteam.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // Hilo de prueba
        thread{
            // Solicitud GET hecha a la API
            val animeList = AnimeDBClient.service.animeList()
            val response = animeList.execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                // Imprimir la cantidad de animes que hay en la base de datos.
                println("Cantidad: ${body.size}")
                // Imprimir animes.
                for (anime in body){
                    println("ID: ${anime.id}")
                    println("Nombre: ${anime.name}")
                    println("Descripción: ${anime.description}")
                    println("Episodios: ${anime.episodes}")
                    println("Temporada: ${anime.season}")
                    println("Géneros: ${anime.genres}")
                    println("URL de la imagen: ${anime.image_url}")
                }
            }
        }
    }
    fun changeAnimeList(view: View){
        val intent = Intent(this, AnimeListActivity::class.java)
        startActivity(intent)
    }
}