package com.fullstackteam.androidproject.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.fullstackteam.androidproject.R

class AnimeAdapter(private val animeDataSet: java.util.ArrayList<AnimeListItem>, private val onClickListener: (AnimeListItem) -> Unit) : RecyclerView.Adapter<AnimeViewHolder>() {

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item, parent, false) as LinearLayout
        return AnimeViewHolder(v)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val item = animeDataSet[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount() = animeDataSet.size
}