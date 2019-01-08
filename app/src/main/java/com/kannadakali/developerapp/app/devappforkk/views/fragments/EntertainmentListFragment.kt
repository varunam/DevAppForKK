package com.kannadakali.developerapp.app.devappforkk.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kannadakali.developerapp.app.devappforkk.R
import com.kannadakali.developerapp.app.devappforkk.ThisApplication
import com.kannadakali.developerapp.app.devappforkk.adapters.VideosAdapter
import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo

/**
 * Created by varun.am on 08/01/19
 */
class EntertainmentListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var videosAdapter: VideosAdapter? = null

    companion object {
        public var VIDEOS_KEY = "videos-key"
        fun newInstance(videosList: ArrayList<SimpleVideo>): EntertainmentListFragment {

            val args = Bundle()
            args.putParcelableArrayList(VIDEOS_KEY, videosList)

            val fragment = EntertainmentListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.layout_entertainment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }

    private fun init(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_id)
        videosAdapter = VideosAdapter()
        recyclerView!!.layoutManager = LinearLayoutManager(ThisApplication.getContext())
        recyclerView!!.adapter = videosAdapter

        videosAdapter!!.setVideos(arguments!!.getParcelableArrayList(VIDEOS_KEY)!!)
    }
}