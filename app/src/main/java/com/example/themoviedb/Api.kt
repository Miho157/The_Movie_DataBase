package com.example.themoviedb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "b4b6c9b9560ed8c88109fb25999f02e0",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "b4b6c9b9560ed8c88109fb25999f02e0",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>


    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "b4b6c9b9560ed8c88109fb25999f02e0",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>


    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = "b4b6c9b9560ed8c88109fb25999f02e0",
        @Query("page") page: Int
    ): Call<GetTvShowsResponse>

    @GET("tv/top_rated")
    fun getTopRatedTvShows(
        @Query("api_key") apiKey: String = "b4b6c9b9560ed8c88109fb25999f02e0",
        @Query("page") page: Int
    ): Call<GetTvShowsResponse>

    @GET("tv/on_the_air")
    fun getOnAirTvShows(
        @Query("api_key") apiKey: String = "b4b6c9b9560ed8c88109fb25999f02e0",
        @Query("page") page: Int
    ): Call<GetTvShowsResponse>

}