package com.kannadakali.developerapp.app.devappforkk.data.model;

import android.net.Uri;
import com.kannadakali.developerapp.app.devappforkk.enums.VideoType;
import com.kannadakali.developerapp.app.devappforkk.youtube.Youtube;

/**
 * Created by varun.am on 26/12/18
 */
public class SimpleVideo {
    private int id_;
    private String id;
    private String title;
    private String thumbnail_url;
    private String video_type;
    private long votes;
    private long sort_count;

    //required for firebase. DON'T DELETE
    public SimpleVideo() {
    }
    
    public SimpleVideo(int id_, String title, String thumbnail_url, String video_type, long votes, long sort_count) {
        this.id_ = id_;
        this.title = title;
        this.thumbnail_url = thumbnail_url;
        this.video_type = video_type;
        this.votes = votes;
        this.sort_count = sort_count;
    }

    public SimpleVideo(String youtubeVideoId, String title) {
        this.id = youtubeVideoId;
        this.title = title;
        this.thumbnail_url = Youtube.getThumbnailUrl(youtubeVideoId);
        this.video_type = VideoType.YOUTUBE.getVideoType();
        this.sort_count = 10;
        this.votes = 0;
    }

    public SimpleVideo(String videoUrl, String title, String thumbNailUrl) {
        this.video_type = VideoType.OTHERS.getVideoType();
        this.id = videoUrl;
        this.title = title;
        this.thumbnail_url = thumbNailUrl;
        this.sort_count = 10;
        this.votes = 0;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getThumbnail_url() {
        return thumbnail_url;
    }
    
    public Uri getThumbnailUrl() {
        return Uri.parse(thumbnail_url);
    }
    
    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }
    
    public String getVideo_type() {
        return video_type;
    }
    
    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }
    
    public long getVotes() {
        return votes;
    }
    
    public void setVotes(long votes) {
        this.votes = votes;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SimpleVideo) {
            return this.getTitle().equals(((SimpleVideo) obj).getTitle());
        } else
            return false;
    }
    
    public long getSort_count() {
        return sort_count;
    }
    
    public void setSort_count(long sort_count) {
        this.sort_count = sort_count;
    }
    
    public int getId_() {
        return id_;
    }
    
    public void setId_(int id_) {
        this.id_ = id_;
    }
}
