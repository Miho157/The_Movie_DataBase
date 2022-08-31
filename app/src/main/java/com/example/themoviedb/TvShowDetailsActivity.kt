package com.example.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

const val TV_SHOW_BACKDROP = "extra_tv_show_backdrop"
const val TV_SHOW_POSTER = "extra_tv_show_poster"
const val TV_SHOW_TITLE = "extra_tv_show_title"
const val TV_SHOW_RATING = "extra_tv_show_rating"
const val TV_SHOW_RELEASE_DATE = "extra_tv_show_release_date"
const val TV_SHOW_OVERVIEW = "extra_tv_show_overview"

class TvShowDetailsActivity : AppCompatActivity() {
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_details)
        backdrop = findViewById(R.id.tv_show_backdrop)
        poster = findViewById(R.id.tv_show_poster)
        title = findViewById(R.id.tv_show_title)
        rating = findViewById(R.id.tv_show_rating)
        releaseDate = findViewById(R.id.tv_show_release_date)
        overview = findViewById(R.id.tv_show_overview)

        val extras = intent.extras
        if (extras != null) {
            populateDetails(extras)
        } else {
            finish()
        }

    }

    private fun populateDetails(extras: Bundle) {
        extras.getString(TV_SHOW_BACKDROP)?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .transform(CenterCrop())
                .into(backdrop)
        }
        extras.getString(TV_SHOW_POSTER)?.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342$posterPath")
                .transform(CenterCrop())
                .into(poster)
        }
        title.text = extras.getString(TV_SHOW_TITLE, "")
        rating.rating = extras.getFloat(TV_SHOW_RATING, 0f) / 2
        releaseDate.text = extras.getString(TV_SHOW_RELEASE_DATE, "")
        overview.text = extras.getString(TV_SHOW_OVERVIEW, "")
    }

        }