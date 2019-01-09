package com.kannadakali.developerapp.app.devappforkk.data.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.kannadakali.developerapp.app.devappforkk.enums.VideoType;
import com.kannadakali.developerapp.app.devappforkk.youtube.Youtube;

/**
 * Created by varun.am on 26/12/18
 */
public class SimpleVideo implements Parcelable {
    private String id;
    private String title;
    private String thumbnail_url;
    private String video_type;
    private long votes;
    private long sort_count;

    //required for firebase. DON'T DELETE
    public SimpleVideo() {
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

    protected SimpleVideo(Parcel in) {
        id = in.readString();
        title = in.readString();
        thumbnail_url = in.readString();
        video_type = in.readString();
        votes = in.readLong();
        sort_count = in.readLong();
    }

    public static final Creator<SimpleVideo> CREATOR = new Creator<SimpleVideo>() {
        @Override
        public SimpleVideo createFromParcel(Parcel in) {
            return new SimpleVideo(in);
        }

        @Override
        public SimpleVideo[] newArray(int size) {
            return new SimpleVideo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(thumbnail_url);
        parcel.writeString(video_type);
        parcel.writeLong(votes);
        parcel.writeLong(sort_count);
    }
}
