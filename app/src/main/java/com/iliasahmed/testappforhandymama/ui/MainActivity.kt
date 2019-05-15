package com.iliasahmed.testappforhandymama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.iliasahmed.testappforhandymama.R
import com.iliasahmed.testappforhandymama.ui.discover.DiscoverMovieFragment
import com.iliasahmed.testappforhandymama.ui.nowPlaying.NowPlayingFragment
import com.iliasahmed.testappforhandymama.ui.person.PersonFragment
import com.iliasahmed.testappforhandymama.ui.popular.PopularMovieFragment
import com.iliasahmed.testappforhandymama.ui.topRated.TopRatedFragment
import com.iliasahmed.testappforhandymama.ui.upcoming.UpcomingMoviesFragment
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.popular_movies)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.drawer_open, R.string.drawer_close)

//        checkStatus();

        drawer_layout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        navigationView.bringToFront()
        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull menuItem: MenuItem): Boolean {
                Logger.d("onNavigationItemSelected")
                when (menuItem.itemId) {
                    R.id.menu_popular_movie -> {
                        drawer_layout.closeDrawers()
                        supportActionBar!!.title = getString(R.string.popular_movies)
                        addFragmentClearingAll(PopularMovieFragment())
                    }

                    R.id.menu_now_playing -> {
                        drawer_layout.closeDrawers()
                        supportActionBar!!.title = getString(R.string.now_playing)
                        addFragmentClearingAll(NowPlayingFragment())
                    }

                    R.id.menu_upcoming_movies -> {
                        drawer_layout.closeDrawers()
                        supportActionBar!!.title = getString(R.string.upcoming_movies)
                        addFragmentClearingAll(UpcomingMoviesFragment())
                    }

                    R.id.menu_discover_movies -> {
                        drawer_layout.closeDrawers()
                        supportActionBar!!.title = getString(R.string.discover_movies)
                        addFragmentClearingAll(DiscoverMovieFragment())
                    }

                    R.id.menu_top_rated -> {
                        drawer_layout.closeDrawers()
                        supportActionBar!!.title = getString(R.string.top_rated)
                        addFragmentClearingAll(TopRatedFragment())
                    }

                    R.id.menu_person -> {
                        drawer_layout.closeDrawers()
                        supportActionBar!!.title = getString(R.string.person)
                        addFragmentClearingAll(PersonFragment())
                    }

                }
                return false
            }
        })
        addFragmentClearingAll(PopularMovieFragment())

    }

    private fun addFragmentClearingAll(fragment: Fragment) {
        supportFragmentManager.fragments.clear()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
