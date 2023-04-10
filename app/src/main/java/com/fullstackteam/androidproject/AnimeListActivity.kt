package com.fullstackteam.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.fullstackteam.androidproject.databinding.ActivityAnimeListBinding
import com.fullstackteam.androidproject.model.AnimeDBClient
import kotlin.concurrent.thread

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
            loadAnimeName()
        }
    }
    fun close(view: View){
        finish()
    }
    private fun loadAnimeName(){
        val animeList = AnimeDBClient.service.animeList()
        val response = animeList.execute()
        val body = response.body()
        val animeName = ArrayList<String>()

        if (response.isSuccessful && body != null){
            for (anime in body){
                animeName.add(anime.name)
            }

            // Configuración del adaptador en el hilo principal
            runOnUiThread {
                val itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, animeName)
                binding.listViewAnimeList.adapter = itemsAdapter
            }
        }
    }
}