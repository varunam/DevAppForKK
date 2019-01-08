package com.kannadakali.developerapp.app.devappforkk.views.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.kannadakali.developerapp.app.devappforkk.R
import com.kannadakali.developerapp.app.devappforkk.data.firebase.EntertainmentContentProvider
import com.kannadakali.developerapp.app.devappforkk.data.firebase.EntertainmentLoadedCallbacks
import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo
import com.kannadakali.developerapp.app.devappforkk.viewmodel.EntertainmentViewModel
import com.kannadakali.developerapp.app.devappforkk.views.fragments.EntertainmentHomeFragment

class MainActivity : AppCompatActivity(), EntertainmentLoadedCallbacks {

    private var entertainmentContentProvider: EntertainmentContentProvider? = null
    private var entertainmentViewModel: EntertainmentViewModel? = null
    private var entertainmentHomeFragment: EntertainmentHomeFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        launchHomeFragment()

        FirebaseAuth.getInstance()
            .signInAnonymously()
            .addOnSuccessListener {
                entertainmentContentProvider!!.loadEntertainmentPack()
            }
            .addOnFailureListener{
                Log.e("data","Failed to extract data: " + it.message)
            }
    }

    private fun launchHomeFragment() {
        if(entertainmentHomeFragment==null)
            entertainmentHomeFragment = EntertainmentHomeFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.full_screen_container_id, entertainmentHomeFragment!!)
            .commit()
    }

    private fun init() {
        entertainmentContentProvider = EntertainmentContentProvider(this)
        entertainmentViewModel = ViewModelProviders.of(this).get(EntertainmentViewModel::class.java)
        entertainmentViewModel!!.topMoviesList.observe(this, topMoviesObserver)
    }

    override fun onFeaturedVideosLoaded(featuredVideos: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setFeaturedVideos(featuredVideos)
    }

    override fun onTopMoviesLoaded(topmovies: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setTopMoviesList(topmovies)
    }

    override fun onTopComedyLoaded(topComedyScenes: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setTopComediesList(topComedyScenes)
    }

    override fun onTopSongsLoaded(topSongs: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setTopSongsList(topSongs)
    }

    override fun onLatestTrailersLoaded(latestTrailers: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setLatestTrailersList(latestTrailers)
    }

    override fun onTopShortMoviesLoaded(shortMovies: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setTopShortMoviesList(shortMovies)
    }

    private val topMoviesObserver =
        Observer<ArrayList<SimpleVideo>> {
            Log.e("data", it!![0].title)
        }

}
