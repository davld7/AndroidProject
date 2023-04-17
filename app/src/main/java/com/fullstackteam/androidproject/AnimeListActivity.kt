package com.fullstackteam.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fullstackteam.androidproject.databinding.ActivityAnimeListBinding
import com.fullstackteam.androidproject.model.AnimeAdapter
import com.fullstackteam.androidproject.model.AnimeDBClient
import com.fullstackteam.androidproject.model.AnimeListItem
import kotlin.concurrent.thread

class AnimeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeListBinding
    private var size: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater).apply {
            val view = root
            setContentView(view)
            buttonGoBack.text = "Regresar"
            getAnimeList()
        }
    }

    fun goBack(view: View) {
        finish()
    }

    fun update(view: View) {
        getAnimeList()
    }

    fun getSize(view: View) {
        if (size != 0){
            runOnUiThread {
                Toast.makeText(this, "$size Animes en la Base de Datos", Toast.LENGTH_SHORT).show()
            }
        } else{
            runOnUiThread {
                Toast.makeText(this, "Vuelva a actualizar la Lista de Animes", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getAnimeList() {
        thread {
            try {
                val animeList = AnimeDBClient.service.animeList()
                val response = animeList.execute()
                val animeListBody = response.body()

                if (response.isSuccessful && animeListBody != null) {
                    runOnUiThread {
                        size = animeListBody.size
                        binding.recyclerViewAnimeList.setHasFixedSize(true)
                        val layoutManager = LinearLayoutManager(baseContext)
                        binding.recyclerViewAnimeList.layoutManager = layoutManager
                        binding.recyclerViewAnimeList.adapter = AnimeAdapter(animeListBody) { onItemClick(it) }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this,
                            "La llamada a la API falló con el código de respuesta ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(this,"La llamada a la API falló con una excepción $exception", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onItemClick(anime: AnimeListItem) {
        val intent = Intent(this, AnimeDetailActivity::class.java).apply {
            putExtra("name", anime.name)
            putExtra("description", anime.description)
            putExtra("episodes", anime.episodes)
            putExtra("season", anime.season)
            putExtra("genres", anime.genres)
            putExtra("image_url", anime.image_url)
        }
        startActivity(intent)
    }
}