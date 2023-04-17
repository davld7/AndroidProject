package com.fullstackteam.androidproject.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fullstackteam.androidproject.databinding.AnimeItemBinding
import com.squareup.picasso.Picasso

// ViewHolder personalizado utilizado por el adaptador de RecyclerView para mostrar los elementos de la lista de anime
class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Declaración de las vistas que se utilizarán para mostrar la información de anime
    val textAnimeName: TextView
    val textAnimeSeason: TextView
    val textAnimeGenres: TextView
    val imageAnime: ImageView
    // Binding generado por ViewBinding
    val binding = AnimeItemBinding.bind(view)
    
    // Inicialización de las vistas utilizando la instancia de binding generada previamente
    init {
        textAnimeName = binding.textName
        textAnimeSeason = binding.textSeason
        textAnimeGenres = binding.textGenres
        imageAnime = binding.imageAnime
    }
    
    // Función utilizada para mostrar la información de anime en las vistas correspondientes
    fun render(anime: AnimeListItem, onClickListener: (AnimeListItem) -> Unit) {
        textAnimeName.text = anime.name
        textAnimeSeason.text = anime.season
        textAnimeGenres.text = anime.genres
        // Carga de la imagen de anime utilizando la biblioteca Picasso
        Picasso.get().load(anime.image_url).into(imageAnime)
        // Configuración de un listener de clic en el elemento de anime
        itemView.setOnClickListener {
            onClickListener(anime)
        }

    }
}
