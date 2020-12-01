package com.dani.kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dani.domain.entities.HeroInfo
import com.dani.kotlin.R
import com.dani.kotlin.databinding.HeroDetailBinding
import com.squareup.picasso.Picasso

class HeroDetail : AppCompatActivity() {

    private lateinit var binding: HeroDetailBinding
    private lateinit var data: HeroInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HeroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = intent.extras?.get("data") as HeroInfo
        val thumbnail = data.thumbnail
        val path = thumbnail?.path?.replace(
            "http", "https")

        val imageUrl = "$path.${thumbnail?.extension}"
        Picasso.get().load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(android.R.drawable.stat_notify_error)
            .into(binding.heroImage)

        binding.detailName.text = getString(
            R.string.hero_name, data.name)
        binding.detailComicCount.text   = getString(
            R.string.hero_comics_available, data.comics.available)
        binding.detailSeriesCount.text  = getString(
            R.string.hero_series_available, data.series.available)
        binding.detailStoriesCount.text = getString(
            R.string.hero_stories_available, data.stories.available)
        binding.detailEventsCount.text  = getString(
            R.string.hero_events_available, data.events.available)
    }
}