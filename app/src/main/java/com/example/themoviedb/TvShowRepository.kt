package com.example.themoviedb

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TvShowsRepository {
    private val api: Api
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }
    fun getPopularTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getPopularTvShows(page = page)
            .enqueue(object : Callback<GetTvShowsResponse> {
                override fun onResponse(
                    call: Call<GetTvShowsResponse>,
                    response: Response<GetTvShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowsResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }


    fun getTopRatedTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getTopRatedTvShows(page = page)
            .enqueue(object : Callback<GetTvShowsResponse> {
                override fun onResponse(
                    call: Call<GetTvShowsResponse>,
                    response: Response<GetTvShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowsResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getOnAirTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getOnAirTvShows(page = page)
            .enqueue(object : Callback<GetTvShowsResponse> {
                override fun onResponse(
                    call: Call<GetTvShowsResponse>,
                    response: Response<GetTvShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }
                override fun onFailure(call: Call<GetTvShowsResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

}
