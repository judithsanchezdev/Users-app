package com.geekoo.users_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.geekoo.users_app.databinding.ItemAnimeBinding

class AnimeAdapter(private val anime: List<Anime>, private val listener: OnClickListener) :
    RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context =  parent.context
        val view  =  LayoutInflater.from(context).inflate(R.layout.item_anime, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anime = anime.get(position)
        with(holder){
            setListener(anime, position + 1)
            binding.tvOrder.text = ( position + 1 ).toString()
            binding.tvName.text = anime.name
            Glide.with(context)
                .load(anime.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int = anime.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAnimeBinding.bind(view)

        fun setListener(anime: Anime, position: Int){
            binding.root.setOnClickListener{
                listener.onClick(anime, position)
            }
        }
    }

}
