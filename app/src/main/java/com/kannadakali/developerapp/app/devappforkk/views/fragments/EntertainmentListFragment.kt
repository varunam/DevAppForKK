package com.kannadakali.developerapp.app.devappforkk.views.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.kannadakali.developerapp.app.devappforkk.R
import com.kannadakali.developerapp.app.devappforkk.ThisApplication
import com.kannadakali.developerapp.app.devappforkk.adapters.VideosAdapter
import com.kannadakali.developerapp.app.devappforkk.data.firebase.EntertainmentContentProvider
import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo
import com.kannadakali.developerapp.app.devappforkk.enums.EntertainmentType
import com.kannadakali.developerapp.app.devappforkk.enums.VideoType
import com.kannadakali.developerapp.app.devappforkk.interfaces.VideoClickCallbacks

/**
 * Created by varun.am on 08/01/19
 */
class EntertainmentListFragment : Fragment(), View.OnClickListener, VideoClickCallbacks {

    private var recyclerView: RecyclerView? = null
    private var videosAdapter: VideosAdapter? = null
    private var addVideo: Button? = null
    private var entertainmentContentProvider: EntertainmentContentProvider? = null
    private var videosList: ArrayList<SimpleVideo>? = null
    private var entertainmentType: EntertainmentType? = null

    companion object {
        public var VIDEOS_KEY = "videos-key"
        public var ENTERTAINMENT_KEY = "entertainment-key"
        fun newInstance(
            entertainmentType: EntertainmentType,
            videosList: ArrayList<SimpleVideo>
        ): EntertainmentListFragment {

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
        entertainmentType = arguments!!.getSerializable(ENTERTAINMENT_KEY) as EntertainmentType?

        entertainmentContentProvider = EntertainmentContentProvider(null)

        recyclerView = view.findViewById(R.id.recycler_view_id)
        videosAdapter = VideosAdapter(this)
        recyclerView!!.layoutManager = LinearLayoutManager(ThisApplication.getContext())
        recyclerView!!.adapter = videosAdapter

        videosList = arguments!!.getParcelableArrayList(VIDEOS_KEY)!!
        videosList!!.sortWith(compareBy {
            it.sort_count
        })
        videosAdapter!!.setVideos(videosList!!)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.add_video_button_id -> showAddVideoDialog()
        }
    }

    private fun showAddVideoDialog() {
        val builder = AlertDialog.Builder(activity!!)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_new_video, null, false)
        builder.setView(view)

        val alertDialog = builder.create()

        val titleEt = view.findViewById<EditText>(R.id.title_et_id)
        val videoIdEt = view.findViewById<EditText>(R.id.video_url_et_id)
        val thumbnailUrlEt = view.findViewById<EditText>(R.id.thumbnail_url_et_id)
        val sortCountEt = view.findViewById<EditText>(R.id.sort_count_et_id)
        val viewsEt = view.findViewById<EditText>(R.id.views_et_id)
        val radioGroup = view.findViewById<RadioGroup>(R.id.video_type_radio_group_id)
        val addVideoButton = view.findViewById<Button>(R.id.add_video_dialg_button_id)

        sortCountEt.setText((videosList!![videosList!!.size - 1].sort_count + 1).toString())

        addVideoButton.setOnClickListener {
            val title = titleEt.text.toString()
            val videoId = videoIdEt.text.toString()
            val thumbnailUrl = thumbnailUrlEt.text.toString()
            val sortCountText = sortCountEt.text.toString()
            val viewsText = viewsEt.text.toString()
            val buttonId = radioGroup.checkedRadioButtonId
            var videoTypeText = ""
            videoTypeText = if (buttonId == R.id.video_type_youtube_id)
                VideoType.YOUTUBE.videoType
            else
                VideoType.OTHERS.videoType

            if (TextUtils.isEmpty(title)) {
                titleEt.error = "required field"
                titleEt.requestFocus()
            } else if (buttonId < 0) {
                Toast.makeText(ThisApplication.getContext(), "Please select videoType", Toast.LENGTH_LONG).show()
            } else if (TextUtils.isEmpty(videoId)) {
                videoIdEt.error = "required field"
                videoIdEt.requestFocus()
            } else if (TextUtils.isEmpty(viewsText)) {
                viewsEt.error = "required field"
                viewsEt.requestFocus()
            } else if (TextUtils.isEmpty(thumbnailUrl)) {
                thumbnailUrlEt.error = "required field"
                thumbnailUrlEt.requestFocus()
            } else if (TextUtils.isEmpty(sortCountText)) {
                sortCountEt.error = "required field"
                sortCountEt.requestFocus()
            } else {
                val sortCountLong = sortCountText.toLong()
                val viewsCount = viewsText.toLong()

                val newVideo = SimpleVideo()
                newVideo.title = title
                newVideo.video_type = videoTypeText
                newVideo.id = videoId
                newVideo.thumbnail_url = thumbnailUrl
                newVideo.votes = viewsCount
                newVideo.sort_count = sortCountLong

                entertainmentContentProvider!!.addVideo(
                    entertainmentType,
                    newVideo
                )

                alertDialog.dismiss()
            }
        }
        alertDialog.show()
    }

    override fun onVideoLongClicked(simpleVideo: SimpleVideo) {
        AlertDialog.Builder(activity!!)
            .setTitle("Delete video?")
            .setIcon(R.drawable.error_red_icon)
            .setMessage("Are you sure to delete this video from database? \n\nNote: This will delete video from production environment!")
            .setCancelable(false)
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    entertainmentContentProvider!!.removeVieo(entertainmentType, simpleVideo)
                }
            })
            .setNegativeButton("cancel", null)
            .create().show()
    }
}