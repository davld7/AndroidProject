package com.fullstackteam.androidproject.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.fullstackteam.androidproject.databinding.AnimeItemBinding

class AnimeAdapter(private val context: Context, private val arrayList: java.util.ArrayList<AnimeListItem>): BaseAdapter(){
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val binding = AnimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        var convertView = convertView
        convertView = binding.root
        binding.textName.text = arrayList[position].name
        binding.textSeason.text = arrayList[position].season
        binding.textGenres.text = arrayList[position].genres
        return convertView
    }

}