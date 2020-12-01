package com.dani.kotlin.ui.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dani.domain.entities.HeroInfo
import com.dani.kotlin.R
import com.dani.kotlin.ui.HeroDetail
import com.squareup.picasso.Picasso

class HeroAdapter(private val items: List<HeroInfo>)
    : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int):
        HeroViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hero_card,parent,
            false) as CardView

        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val thumbnail = items[position].thumbnail
        val path = thumbnail?.path?.replace(
            "http", "https")

        val imageUrl = "$path.${thumbnail?.extension}"
        Picasso.get().load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.heroImage)

        val name = holder.itemView.context.getString(
            R.string.hero_name, items[position].name)
        val comicCount = holder.itemView.context.
            getString(R.string.hero_comics_available,
            items[position].comics.available)

        holder.heroName.text = name
        holder.heroComicCount.text = comicCount
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, HeroDetail::class.java)
            intent.putExtra("data", items[position])
            (it.context as Activity).startActivity(intent)
        }
    }

    override fun getItemCount() = items.size

    class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val heroImage = view.findViewById(R.id.heroImage) as ImageView
        var heroName  = view.findViewById(R.id.heroName)  as TextView
        var heroComicCount  = view.findViewById(R.id.comicsAvailable) as TextView
    }
}