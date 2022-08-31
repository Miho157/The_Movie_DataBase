package com.example.themoviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class WatchListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var watchList: RecyclerView
    private lateinit var watchListAdapter: WatchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            getWatchList()
        }
    }

    override fun onResume() {
        super.onResume()
        getWatchList()
        }

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java,
            "mymovies.db"
        ).allowMainThreadQueries().build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_watch_list, container, false)
        watchList = view.findViewById(R.id.watchlist)
        watchList.layoutManager = GridLayoutManager(context, 3)
        watchListAdapter = WatchListAdapter(listOf())
        watchList.adapter = watchListAdapter

        getWatchList()

        return view
    }


    private fun getWatchList() {
        val movies = db.movieDao().getAll()
        val watchList = mutableListOf<WatchList>()
        watchList.addAll(
            movies.map { movie ->
                WatchList(
                    movie.id,
                    movie.title,
                    movie.overview,
                    movie.posterPath,
                    movie.backdropPath,
                    movie.rating,
                    movie.releaseDate,
                    WatchListType.MovieType
                )
            }
        )

        watchListAdapter.updateItems(watchList)
    }


}