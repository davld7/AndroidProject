package com.fullstackteam.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fullstackteam.androidproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {    
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflamos el layout y configuramos las vistas
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            textMainMenu.text = "Menú Principal"
            textCreatedBy.text = "Created by Full Stack Team"
            buttonAnimeList.text = "Lista de Animes"
        }
    }
    // Función para ir a la pantalla de lista de animes
    fun goAnimeList(view: View) {
        val intent = Intent(this, AnimeListActivity::class.java)
        startActivity(intent)
    }
}
