package com.example.themoviedb

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TvShowsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var popularTvShows: RecyclerView
    private lateinit var popularTvShowsAdapter: TvShowsAdapter
    private lateinit var popularTvShowsLayoutMgr: LinearLayoutManager
    private var popularTvShowsPage = 1

    private lateinit var topRatedTvShows: RecyclerView
    private lateinit var topRatedTvShowsAdapter: TvShowsAdapter
    private lateinit var topRatedTvShowsLayoutMgr: LinearLayoutManager
    private var topRatedTvShowsPage = 1

    private lateinit var onAirTvShows: RecyclerView
    private lateinit var onAirTvShowsAdapter: TvShowsAdapter
    private lateinit var onAirTvShowsLayoutMgr: LinearLayoutManager
    private var onAirTvShowsPage = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_shows, container, false)
        popularTvShows = view.findViewById(R.id.popular_tv_shows)
        popularTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularTvShows.layoutManager = popularTvShowsLayoutMgr
        popularTvShowsAdapter = TvShowsAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        popularTvShows.adapter = popularTvShowsAdapter

        topRatedTvShows = view.findViewById(R.id.top_rated_tv_shows)
        topRatedTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        topRatedTvShows.layoutManager = topRatedTvShowsLayoutMgr
        topRatedTvShowsAdapter = TvShowsAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        topRatedTvShows.adapter = topRatedTvShowsAdapter

        onAirTvShows = view.findViewById(R.id.on_air_tv_shows)
        onAirTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        onAirTvShows.layoutManager = onAirTvShowsLayoutMgr
        onAirTvShowsAdapter = TvShowsAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        onAirTvShows.adapter = onAirTvShowsAdapter


        getPopularTvShows()
        getTopRatedTvShows()
        getOnAirTvShows()
        return view
    }


    private fun getPopularTvShows() {
        TvShowsRepository.getPopularTvShows(
            popularTvShowsPage,
            ::onPopularTvShowsFetched,
            ::onError
        )
    }
    private fun onPopularTvShowsFetched(tvShows: List<TvShow>) {
        popularTvShowsAdapter.appendTvShows(tvShows)
        attachPopularTvShowsOnScrollListener()
    }
    private fun onError() {
        Toast.makeText(activity, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }

    private fun attachPopularTvShowsOnScrollListener() {
        popularTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularTvShowsLayoutMgr.itemCount
                val visibleItemCount = popularTvShowsLayoutMgr.childCount
                val firstVisibleItem = popularTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularTvShows.removeOnScrollListener(this)
                    popularTvShowsPage++
                    getPopularTvShows()
                }
            }
        })
    }

    private fun getTopRatedTvShows() {
        TvShowsRepository.getTopRatedTvShows(
            topRatedTvShowsPage,
            ::onTopRatedTvShowsFetched,
            ::onError
        )
    }
    private fun attachTopRatedTvShowsOnScrollListener() {
        topRatedTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedTvShowsLayoutMgr.itemCount
                val visibleItemCount = topRatedTvShowsLayoutMgr.childCount
                val firstVisibleItem = topRatedTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedTvShows.removeOnScrollListener(this)
                    topRatedTvShowsPage++
                    getTopRatedTvShows()
                }
            }
        })
    }
    private fun onTopRatedTvShowsFetched(tvShows: List<TvShow>) {
        topRatedTvShowsAdapter.appendTvShows(tvShows)
        attachTopRatedTvShowsOnScrollListener()
    }


    private fun getOnAirTvShows() {
        TvShowsRepository.getOnAirTvShows(
            onAirTvShowsPage,
            ::onOnAirTvShowsFetched,
            ::onError
        )
    }
    private fun attachOnAirTvShowsOnScrollListener() {
        onAirTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = onAirTvShowsLayoutMgr.itemCount
                val visibleItemCount = onAirTvShowsLayoutMgr.childCount
                val firstVisibleItem = onAirTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    onAirTvShows.removeOnScrollListener(this)
                    onAirTvShowsPage++
                    getOnAirTvShows()
                }
            }
        })
    }
    private fun onOnAirTvShowsFetched(tvShows: List<TvShow>) {
        onAirTvShowsAdapter.appendTvShows(tvShows)
        attachOnAirTvShowsOnScrollListener()
    }

    private fun showTvShowDetails(tvShow: TvShow) {
        val intent = Intent(activity, TvShowDetailsActivity::class.java)
        intent.putExtra(TV_SHOW_BACKDROP, tvShow.backdropPath)
        intent.putExtra(TV_SHOW_POSTER, tvShow.posterPath)
        intent.putExtra(TV_SHOW_TITLE, tvShow.name)
        intent.putExtra(TV_SHOW_RATING, tvShow.rating)
        intent.putExtra(TV_SHOW_RELEASE_DATE, tvShow.firstAirDate)
        intent.putExtra(TV_SHOW_OVERVIEW, tvShow.overview)
        startActivity(intent)
    }


}