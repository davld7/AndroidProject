package com.fullstackteam.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fullstackteam.androidproject.databinding.ActivityAnimeListBinding

class AnimeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater).apply {
            setContentView(root)
            textAnimeList.text = "Lista de Animes"
            buttonClose.text = "Cerrar"
        }
    }
    fun close(view: View){
        finish()
    }
}