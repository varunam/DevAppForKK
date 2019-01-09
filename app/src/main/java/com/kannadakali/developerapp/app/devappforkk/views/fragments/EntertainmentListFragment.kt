package com.kannadakali.developerapp.app.devappforkk.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.kannadakali.developerapp.app.devappforkk.R
import com.kannadakali.developerapp.app.devappforkk.ThisApplication
import com.kannadakali.developerapp.app.devappforkk.adapters.VideosAdapter
import com.kannadakali.developerapp.app.devappforkk.data.firebase.EntertainmentContentProvider
import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo
import com.kannadakali.developerapp.app.devappforkk.enums.EntertainmentType

/**
 * Created by varun.am on 08/01/19
 */
class EntertainmentListFragment : Fragment(), View.OnClickListener {

    private var recyclerView: RecyclerView? = null
    private var videosAdapter: VideosAdapter? = null
    private var addVideo: Button? = null
    private var entertainmentContentProvider: EntertainmentContentProvider? = null

    companion object {
        public var VIDEOS_KEY = "videos-key"
        public var ENTERTAINMENT_KEY = "entertainment-key"
        fun newInstance(entertainmentType: EntertainmentType, videosList: ArrayList<SimpleVideo>): EntertainmentListFragment {

            val args = Bundle()
            args.putParcelableArrayList(VIDEOS_KEY, videosList)
            args.putSerializable(ENTERTAINMENT_KEY, entertainmentType)

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
        addVideo = view.findViewById(R.id.add_video_button_id)
        addVideo!!.setOnClickListener(this)

        entertainmentContentProvider = EntertainmentContentProvider(null)

        recyclerView = view.findViewById(R.id.recycler_view_id)
        videosAdapter = VideosAdapter()
        recyclerView!!.layoutManager = LinearLayoutManager(ThisApplication.getContext())
        recyclerView!!.adapter = videosAdapter

        videosAdapter!!.setVideos(arguments!!.getParcelableArrayList(VIDEOS_KEY)!!)
    }

    override fun onClick(p0: View?) {
        val sampleVideo = SimpleVideo()
        sampleVideo.title = "test title"
        sampleVideo.video_type = "youtube"
        sampleVideo.id = "dtmr-rDIR6s"
        sampleVideo.thumbnail_url = "dtmr-rDIR6s"
        sampleVideo.votes = 200
        sampleVideo.sort_count = 100

        entertainmentContentProvider!!.addVideo(arguments!!.getSerializable(ENTERTAINMENT_KEY) as EntertainmentType?, sampleVideo)
    }
}