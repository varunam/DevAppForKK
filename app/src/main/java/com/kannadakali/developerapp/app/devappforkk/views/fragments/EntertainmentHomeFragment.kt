package com.kannadakali.developerapp.app.devappforkk.views.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kannadakali.developerapp.app.devappforkk.R
import com.kannadakali.developerapp.app.devappforkk.enums.EntertainmentType
import com.kannadakali.developerapp.app.devappforkk.viewmodel.EntertainmentViewModel

/**
 * Created by varun.am on 08/01/19
 */
class EntertainmentHomeFragment : Fragment(), View.OnClickListener {

    private var movies: TextView? = null
    private var featuredVideos: TextView? = null
    private var songs: TextView? = null
    private var comedy: TextView? = null
    private var trailers: TextView? = null
    private var shortMovies: TextView? = null

    private var entertainmentViewModel: EntertainmentViewModel? = null

    companion object {
        fun newInstance(): EntertainmentHomeFragment {

            val args = Bundle()

            val fragment = EntertainmentHomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.layout_entertainment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }

    private fun init(view: View) {
        entertainmentViewModel = ViewModelProviders.of(activity!!).get(EntertainmentViewModel::class.java)

        movies = view.findViewById(R.id.ent_home_movies_id)
        movies!!.setOnClickListener(this)

        featuredVideos = view.findViewById(R.id.ent_home_featured_videos_id)
        featuredVideos!!.setOnClickListener(this)

        songs = view.findViewById(R.id.ent_home_songs_id)
        songs!!.setOnClickListener(this)

        comedy = view.findViewById(R.id.ent_home_comedy_id)
        comedy!!.setOnClickListener(this)

        trailers = view.findViewById(R.id.ent_home_trailers_id)
        trailers!!.setOnClickListener(this)

        shortMovies = view.findViewById(R.id.ent_home_short_moviess_id)
        shortMovies!!.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id){
            R.id.ent_home_movies_id -> {
                entertainmentViewModel!!.setEntertainmentClicked(EntertainmentType.MOVIE)
            }
            R.id.ent_home_comedy_id -> {
                entertainmentViewModel!!.setEntertainmentClicked(EntertainmentType.COMEDY)
            }
            R.id.ent_home_short_moviess_id -> {
                entertainmentViewModel!!.setEntertainmentClicked(EntertainmentType.SHORT_MOVIES)
            }
            R.id.ent_home_trailers_id -> {
                entertainmentViewModel!!.setEntertainmentClicked(EntertainmentType.TRAILERS)
            }
            R.id.ent_home_featured_videos_id -> {
                entertainmentViewModel!!.setEntertainmentClicked(EntertainmentType.FEATURED)
            }
            R.id.ent_home_songs_id -> {
                entertainmentViewModel!!.setEntertainmentClicked(EntertainmentType.SONGS)
            }
        }
    }
}