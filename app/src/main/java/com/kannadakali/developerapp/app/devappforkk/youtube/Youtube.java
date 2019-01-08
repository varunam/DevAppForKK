package com.kannadakali.developerapp.app.devappforkk.youtube;

/**
 * Created by varun.am on 06/12/18
 */
public class Youtube {

    private static final String YOUTUBE_THUMBNAIL_BASE_URL = "https://img.youtube.com/vi/";
    private static final String YOUTUBE_VIDEO_BASE_URL = "https://www.youtube.com/watch?v=";


    
    public static String getVideoUrl(String videoId) {
        return YOUTUBE_VIDEO_BASE_URL + videoId;
    }

    public static String getThumbnailUrl(String youtubeVideoId) {
        return YOUTUBE_THUMBNAIL_BASE_URL + youtubeVideoId + "/default.jpg";
    }
}
