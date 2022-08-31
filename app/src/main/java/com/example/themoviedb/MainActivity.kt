package com.example.themoviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


private const val MOVIES_FRAGMENT = "movies_fragment"
private const val TV_SHOWS_FRAGMENT = "tv_shows_fragment"
private const val WATCH_LIST_FRAGMENT = "watch_list_fragment"

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavView = findViewById(R.id.bottom_navigation_view)
        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.movies -> showMoviesFragment()
                R.id.shows -> showTvShowsFragment()
                R.id.watchlist -> showWatchListFragment()
            }
            return@setOnNavigationItemSelectedListener true
        }
        showMoviesFragment()
    }



    private fun showMoviesFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val watchListFragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        tvShowsFragment?.let { transaction.hide(it) }
        watchListFragment?.let { transaction.hide(it) }
        if (fragment == null) {
            transaction.add(R.id.fragment_container, MoviesFragment(), MOVIES_FRAGMENT)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }



    private fun showTvShowsFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        moviesFragment?.let { transaction.hide(it) }
        if (fragment == null) {
            transaction.add(R.id.fragment_container, TvShowsFragment(), TV_SHOWS_FRAGMENT)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }

    private fun showWatchListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        moviesFragment?.let { transaction.hide(it) }
        tvShowsFragment?.let { transaction.hide(it) }
        if (fragment == null) {
            transaction.add(R.id.fragment_container, WatchListFragment(), WATCH_LIST_FRAGMENT)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }
}