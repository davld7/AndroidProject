package com.fullstackteam.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.fullstackteam.androidproject.databinding.ActivityAnimeListBinding
import com.fullstackteam.androidproject.model.AnimeDBClient
import kotlin.concurrent.thread
import android.util.Log

class AnimeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater).apply {
            setContentView(root)
            textAnimeList.text = "Lista de Animes"
            buttonClose.text = "Cerrar"
        }
        thread{
            loadAnimeNames()
        }
    }
    fun close(view: View){
        finish()
    }
    private fun loadAnimeNames(){
        try {
            val animeList = AnimeDBClient.service.animeList()
            val response = animeList.execute()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                val animeNames = ArrayList<String>()

                for (anime in body) {
                    animeNames.add(anime.name)
                }

                runOnUiThread {
                    val itemsAdapter = ArrayAdapter(this@AnimeListActivity, android.R.layout.simple_list_item_1, animeNames)
                    binding.listViewAnimeList.adapter = itemsAdapter
                }
            } else {
                Log.e("AnimeListActivity", "La llamada a la API falló con el código de respuesta ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("AnimeListActivity", "La llamada a la API falló con una excepción", e)
        }
    }
}