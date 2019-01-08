package com.kannadakali.developerapp.app.devappforkk.enums;

/**
 * Created by varun.am on 26/12/18
 */
public enum VideoType {
    YOUTUBE("youtube"),
    OTHERS("others");
    
    private String videoType;
    
    VideoType(String videoType){
        this.videoType = videoType;
    }
    
    public String getVideoType() {
        return videoType;
    }
}
