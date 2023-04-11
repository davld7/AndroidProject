package com.fullstackteam.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fullstackteam.androidproject.databinding.ActivityAnimeListBinding
import com.fullstackteam.androidproject.model.AnimeDBClient
import kotlin.concurrent.thread
import android.util.Log
import com.fullstackteam.androidproject.model.AnimeAdapter

class AnimeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater).apply {
            setContentView(root)
            buttonGoBack.text = "Regresar"
        }
        thread{
            loadAnimeList()
        }
    }
    fun goBack(view: View){
        finish()
    }
    private fun loadAnimeList(){
        try {
            val animeList = AnimeDBClient.service.animeList()
            val response = animeList.execute()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                runOnUiThread {
                    val animeAdapter = AnimeAdapter(this, body)
                    binding.listViewAnimeList.adapter = animeAdapter
                }
                binding.listViewAnimeList.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(AnimeListActivity@this, AnimeDetailActivity::class.java).apply{
                        putExtra("name", body[id.toInt()].name)
                        putExtra("description", body[id.toInt()].description)
                        putExtra("episodes", body[id.toInt()].episodes)
                        putExtra("season", body[id.toInt()].season)
                        putExtra("genres", body[id.toInt()].genres)
                        putExtra("image_url", body[id.toInt()].image_url)
                    }
                    startActivity(intent)
                }
            } else {
                Log.e("AnimeListActivity", "La llamada a la API falló con el código de respuesta ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("AnimeListActivity", "La llamada a la API falló con una excepción", e)
        }
    }
}