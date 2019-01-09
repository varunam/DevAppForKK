package com.kannadakali.developerapp.app.devappforkk.views.activity


import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kannadakali.developerapp.app.devappforkk.R
import com.kannadakali.developerapp.app.devappforkk.ThisApplication
import com.kannadakali.developerapp.app.devappforkk.data.firebase.EntertainmentContentProvider
import com.kannadakali.developerapp.app.devappforkk.data.firebase.EntertainmentLoadedCallbacks
import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo
import com.kannadakali.developerapp.app.devappforkk.enums.EntertainmentType
import com.kannadakali.developerapp.app.devappforkk.viewmodel.EntertainmentViewModel
import com.kannadakali.developerapp.app.devappforkk.views.fragments.EntertainmentHomeFragment
import com.kannadakali.developerapp.app.devappforkk.views.fragments.EntertainmentListFragment

class MainActivity : AppCompatActivity(), EntertainmentLoadedCallbacks {

    private var entertainmentContentProvider: EntertainmentContentProvider? = null
    private var entertainmentViewModel: EntertainmentViewModel? = null

    private var entertainmentHomeFragment: EntertainmentHomeFragment? = null
    private var entertainmentListFragment: EntertainmentListFragment? = null

    private var featuredVideosList: ArrayList<SimpleVideo>? = null
    private var moviesList: ArrayList<SimpleVideo>? = null
    private var comedyScenesList: ArrayList<SimpleVideo>? = null
    private var songsList: ArrayList<SimpleVideo>? = null
    private var shortMoviesList: ArrayList<SimpleVideo>? = null
    private var trailersList: ArrayList<SimpleVideo>? = null

    private var progressDialog: ProgressDialog? = null

    private var featuredVideosLoaded = false
    private var moviesLoaded = false
    private var comedyLoaded = false
    private var songsLoaded = false
    private var shortMoviesLoaded = false
    private var trailersLoaded = false

    private var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        init()
        launchHomeFragment()
        showLoader("loading data...")

        FirebaseAuth.getInstance()
            .signInAnonymously()
            .addOnSuccessListener {
                entertainmentContentProvider!!.loadEntertainmentPack()
            }
            .addOnFailureListener {
                Log.e("data", "Failed to extract data: " + it.message)
            }
    }

    private fun launchHomeFragment() {
        if (entertainmentHomeFragment == null)
            entertainmentHomeFragment = EntertainmentHomeFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.full_screen_container_id, entertainmentHomeFragment!!)
            .setCustomAnimations(R.anim.from_bottom, R.anim.exit_to_top, R.anim.from_bottom, R.anim.exit_to_top)
            .commit()
    }

    private fun init() {
        actionBar = supportActionBar
        actionBar!!.title = "KK-E Controller"
        entertainmentContentProvider = EntertainmentContentProvider(this)
        entertainmentViewModel = ViewModelProviders.of(this).get(EntertainmentViewModel::class.java)
        entertainmentViewModel!!.entertainmentClicked.observe(this, entertainmentClickObserver)

        progressDialog = ProgressDialog(this)
    }

    override fun onFeaturedVideosLoaded(featuredVideos: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setFeaturedVideos(featuredVideos)
        featuredVideosList = featuredVideos
        featuredVideosLoaded = true
        checkDataLoadedAndDismissLoader()
    }

    override fun onTopMoviesLoaded(topmovies: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setTopMoviesList(topmovies)
        moviesList = topmovies
        moviesLoaded = true
        checkDataLoadedAndDismissLoader()
    }

    override fun onTopComedyLoaded(topComedyScenes: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setTopComediesList(topComedyScenes)
        comedyScenesList = topComedyScenes
        comedyLoaded = true
        checkDataLoadedAndDismissLoader()
    }

    override fun onTopSongsLoaded(topSongs: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setTopSongsList(topSongs)
        songsList = topSongs
        songsLoaded = true
        checkDataLoadedAndDismissLoader()
    }

    override fun onLatestTrailersLoaded(latestTrailers: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setLatestTrailersList(latestTrailers)
        trailersList = latestTrailers
        trailersLoaded = true
        checkDataLoadedAndDismissLoader()
    }

    override fun onTopShortMoviesLoaded(shortMovies: ArrayList<SimpleVideo>?) {
        entertainmentViewModel!!.setTopShortMoviesList(shortMovies)
        shortMoviesList = shortMovies
        shortMoviesLoaded = true
        checkDataLoadedAndDismissLoader()
    }

    private val entertainmentClickObserver =
        Observer<EntertainmentType> {
            when (it) {
                EntertainmentType.COMEDY -> launchListFragment(EntertainmentType.COMEDY, comedyScenesList!!)
                EntertainmentType.SONGS -> launchListFragment(EntertainmentType.SONGS, songsList!!)
                EntertainmentType.SHORT_MOVIES -> launchListFragment(EntertainmentType.SHORT_MOVIES, shortMoviesList!!)
                EntertainmentType.FEATURED -> launchListFragment(EntertainmentType.FEATURED, featuredVideosList!!)
                EntertainmentType.TRAILERS -> launchListFragment(EntertainmentType.TRAILERS, trailersList!!)
                EntertainmentType.MOVIE -> launchListFragment(EntertainmentType.MOVIE, moviesList!!)
                else ->
                    Toast.makeText(ThisApplication.getContext(), "Not available", Toast.LENGTH_LONG).show()
            }
        }

    private fun launchListFragment(entertainmentType: EntertainmentType, videosList: ArrayList<SimpleVideo>) {
        entertainmentListFragment = EntertainmentListFragment.newInstance(entertainmentType, videosList)
        actionBar!!.title = entertainmentType.name
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction()
            .replace(R.id.full_screen_container_id, entertainmentListFragment!!)
            .setCustomAnimations(R.anim.from_bottom, R.anim.exit_to_top, R.anim.from_bottom, R.anim.exit_to_top)
            .addToBackStack(null)
            .commit()
    }

    private fun showLoader(s: String) {
        progressDialog!!.setMessage(s)
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    private fun checkDataLoadedAndDismissLoader() {
        if (featuredVideosLoaded && comedyLoaded && songsLoaded && trailersLoaded && shortMoviesLoaded && moviesLoaded) {
            if (progressDialog!!.isShowing)
                progressDialog!!.dismiss()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
            actionBar!!.setDisplayHomeAsUpEnabled(false)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (entertainmentListFragment!!.isVisible && entertainmentListFragment!!.isAdded)
            actionBar!!.title = "KK-E Controller"
        super.onBackPressed()
    }

}
