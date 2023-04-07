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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textMainMenu.text = "Menú Principal"
        binding.buttonAnimeList.text = "Lista de Animes"
    }
    fun changeAnimeList(view: View){
        val intent = Intent(this, AnimeListActivity::class.java)
        startActivity(intent)
    }
}