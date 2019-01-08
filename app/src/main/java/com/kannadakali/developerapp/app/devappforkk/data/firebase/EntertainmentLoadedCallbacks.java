package com.kannadakali.developerapp.app.devappforkk.data.firebase;

import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo;

import java.util.ArrayList;

/**
 * Created by varun.am on 27/12/18
 */
public interface EntertainmentLoadedCallbacks {
    public void onFeaturedVideosLoaded(ArrayList<SimpleVideo> featuredVideos);
    
    public void onTopMoviesLoaded(ArrayList<SimpleVideo> topmovies);
    
    public void onTopComedyLoaded(ArrayList<SimpleVideo> topComedyScenes);
    
    public void onTopSongsLoaded(ArrayList<SimpleVideo> topSongs);
    
    public void onLatestTrailersLoaded(ArrayList<SimpleVideo> latestTrailers);
    
    public void onTopShortMoviesLoaded(ArrayList<SimpleVideo> shortMovies);
    
}
