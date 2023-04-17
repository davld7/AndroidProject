package com.fullstackteam.androidproject.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.fullstackteam.androidproject.R

// Clase que adapta los datos de la lista de animes al RecyclerView
class AnimeAdapter(private val animeDataSet: java.util.ArrayList<AnimeListItem>, private val onClickListener: (AnimeListItem) -> Unit) : RecyclerView.Adapter<AnimeViewHolder>() {
    
    // Interfaz para detectar cuando un elemento es seleccionado
    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }
    
    // Infla el layout de cada elemento de la lista y lo devuelve como AnimeViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item, parent, false) as LinearLayout
        // Se devuelve el AnimeViewHolder con el layout ya inflado
        return AnimeViewHolder(v)
    }
    
    // Se establece el contenido de cada elemento de la lista en el AnimeViewHolder
    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val item = animeDataSet[position]
        holder.render(item, onClickListener)
    }
    
    // Devuelve la cantidad de elementos en la lista
    override fun getItemCount() = animeDataSet.size
}
