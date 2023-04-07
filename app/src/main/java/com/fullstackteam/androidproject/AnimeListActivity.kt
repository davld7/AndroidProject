package com.fullstackteam.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fullstackteam.androidproject.databinding.ActivityAnimeListBinding

class AnimeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textAnimeList.text = "Lista de Animes"
        binding.buttonClose.text = "Cerrar"
    }
    fun close(view: View){
        finish()
    }
}