package com.fullstackteam.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fullstackteam.androidproject.databinding.ActivityAnimeListBinding
import com.fullstackteam.androidproject.model.AnimeDBClient
import kotlin.concurrent.thread
import android.widget.Toast
import com.fullstackteam.androidproject.model.AnimeAdapter

class AnimeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater).apply {
            setContentView(root)
            buttonGoBack.text = "Regresar"
            buttonUpdate.text = "Actualizar"
            loadAnimeList()
        }
    }
    fun goBack(view: View){
        finish()
    }
    fun update(view: View){
        loadAnimeList()
    }
    private fun loadAnimeList(){
        thread {
            try {
                val animeList = AnimeDBClient.service.animeList()
                val response = animeList.execute()
                val animeListBody = response.body()

                if (response.isSuccessful && animeListBody != null) {
                    runOnUiThread {
                        val animeAdapter = AnimeAdapter(this, animeListBody)
                        binding.listViewAnimeList.adapter = animeAdapter
                    }
                    binding.listViewAnimeList.setOnItemClickListener { parent, view, position, id ->
                        val intent = Intent(this, AnimeDetailActivity::class.java).apply{
                            putExtra("name", animeListBody[position].name)
                            putExtra("description", animeListBody[position].description)
                            putExtra("episodes", animeListBody[position].episodes)
                            putExtra("season", animeListBody[position].season)
                            putExtra("genres", animeListBody[position].genres)
                            putExtra("image_url", animeListBody[position].image_url)
                        }
                        startActivity(intent)
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this, "La llamada a la API falló con el código de respuesta ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(this, "La llamada a la API falló con una excepción $exception", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}