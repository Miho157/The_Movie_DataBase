package com.example.themoviedb

import com.google.gson.annotations.SerializedName

data class GetTvShowsResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tvShows: List<TvShow>,
    @SerializedName("total_pages") val pages: Int
)