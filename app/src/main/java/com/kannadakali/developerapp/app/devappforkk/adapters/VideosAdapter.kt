package com.kannadakali.developerapp.app.devappforkk.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kannadakali.developerapp.app.devappforkk.R
import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo

/**
 * Created by varun.am on 08/01/19
 */

class VideosAdapter : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    private var videosList: ArrayList<SimpleVideo>? = null

    public fun setVideos(videosList: ArrayList<SimpleVideo>){
        this.videosList = videosList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): VideosAdapter.ViewHolder {
        val view = LayoutInflater.from(viewgroup.context)
            .inflate(R.layout.item_video, viewgroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(videosList==null)
            return 0
        else
            return videosList!!.size
    }

    override fun onBindViewHolder(viewholder: VideosAdapter.ViewHolder, position: Int) {
        val video = videosList!![position]
        viewholder.title!!.text = video.title
        viewholder.sort_count!!.text = "sort count: " + video.sort_count
        viewholder.video_type!!.text = "video type: " + video.video_type
        viewholder.votes!!.text = "views: " + video.votes
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var title = itemView.findViewById<TextView>(R.id.video_item_title_id)
        public var votes = itemView.findViewById<TextView>(R.id.video_item_views_id)
        public var video_type = itemView.findViewById<TextView>(R.id.video_item_video_type_id)
        public var sort_count =  itemView.findViewById<TextView>(R.id.video_item_sort_id)
    }

}