package com.kannadakali.developerapp.app.devappforkk.adapters

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kannadakali.developerapp.app.devappforkk.R
import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo
import com.kannadakali.developerapp.app.devappforkk.enums.VideoType
import com.kannadakali.developerapp.app.devappforkk.interfaces.VideoClickCallbacks
import com.kannadakali.developerapp.app.devappforkk.youtube.Youtube


/**
 * Created by varun.am on 08/01/19
 */

class VideosAdapter(videoClickCallbacks: VideoClickCallbacks) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    private var videosList: ArrayList<SimpleVideo>? = null
    private var videoClickCallbacks: VideoClickCallbacks? = null

    init {
        this.videoClickCallbacks = videoClickCallbacks
    }

    public fun setVideos(videosList: ArrayList<SimpleVideo>) {
        this.videosList = videosList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): VideosAdapter.ViewHolder {
        val view = LayoutInflater.from(viewgroup.context)
            .inflate(R.layout.item_video, viewgroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (videosList == null)
            0
        else
            videosList!!.size
    }

    override fun onBindViewHolder(viewholder: VideosAdapter.ViewHolder, position: Int) {
        val video = videosList!![position]
        viewholder.title!!.text = video.title
        viewholder.sort_count!!.text = "sort count: " + video.sort_count
        viewholder.video_type!!.text = "video type: " + video.video_type
        viewholder.votes!!.text = "views: " + video.votes

        var videoUrl = ""
        if (video.video_type == VideoType.YOUTUBE.videoType) {
            videoUrl = Youtube.getVideoUrl(video.id)
        } else
            videoUrl = video.id

        viewholder.video_url.text = videoUrl
        viewholder.video_url.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(videoUrl)
            viewholder.title.context.startActivity(i)
        }

        viewholder.delete.setOnClickListener{
            videoClickCallbacks!!.onDeleteVideoClicked(video)
        }

        viewholder.edit.setOnClickListener{
            videoClickCallbacks!!.onEditClicked(video)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var card = itemView.findViewById<CardView>(R.id.video_item_card_id)
        public var title = itemView.findViewById<TextView>(R.id.video_item_title_id)
        public var votes = itemView.findViewById<TextView>(R.id.video_item_views_id)
        public var video_type = itemView.findViewById<TextView>(R.id.video_item_video_type_id)
        public var sort_count = itemView.findViewById<TextView>(R.id.video_item_sort_id)
        public var video_url = itemView.findViewById<TextView>(R.id.video_item_id_id)
        public var edit = itemView.findViewById<ImageView>(R.id.video_item_edit_id)
        public var delete = itemView.findViewById<ImageView>(R.id.video_item_delete_id)
    }

}