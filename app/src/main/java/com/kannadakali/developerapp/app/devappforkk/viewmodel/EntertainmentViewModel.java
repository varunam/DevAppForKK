package com.kannadakali.developerapp.app.devappforkk.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo;

import java.util.ArrayList;

/**
 * Created by varun.am on 26/12/18
 */
public class EntertainmentViewModel extends AndroidViewModel {
    
    private MutableLiveData<SimpleVideo> simpleVideo = new MutableLiveData<>();
    private MutableLiveData<ArrayList<SimpleVideo>> featuredVideosList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<SimpleVideo>> topMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<SimpleVideo>> latestTrailersList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<SimpleVideo>> topSongsList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<SimpleVideo>> topComediesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<SimpleVideo>> topShortMoviesList = new MutableLiveData<>();
    
    public EntertainmentViewModel(@NonNull Application application) {
        super(application);
    }
    
    public MutableLiveData<SimpleVideo> getSimpleVideo() {
        return simpleVideo;
    }
    
    public void setVideo(SimpleVideo simpleVideo) {
        this.simpleVideo.postValue(simpleVideo);
    }
    
    public void setFeaturedVideos(ArrayList<SimpleVideo> featuredVideos) {
        this.featuredVideosList.postValue(featuredVideos);
    }
    
    public void setTopMoviesList(ArrayList<SimpleVideo> moviesList) {
        this.topMoviesList.postValue(moviesList);
    }
    
    public void setLatestTrailersList(ArrayList<SimpleVideo> latestTrailersList) {
        this.latestTrailersList.postValue(latestTrailersList);
    }
    
    public void setTopSongsList(ArrayList<SimpleVideo> topSongsList) {
        this.topSongsList.postValue(topSongsList);
    }
    
    public void setTopComediesList(ArrayList<SimpleVideo> topComediesList) {
        this.topComediesList.postValue(topComediesList);
    }
    
    public void setTopShortMoviesList(ArrayList<SimpleVideo> topShortMoviesList) {
        this.topShortMoviesList.postValue(topShortMoviesList);
    }
    
    public MutableLiveData<ArrayList<SimpleVideo>> getFeaturedVideosList() {
        return featuredVideosList;
    }
    
    public MutableLiveData<ArrayList<SimpleVideo>> getTopMoviesList() {
        return topMoviesList;
    }
    
    public MutableLiveData<ArrayList<SimpleVideo>> getLatestTrailersList() {
        return latestTrailersList;
    }
    
    public MutableLiveData<ArrayList<SimpleVideo>> getTopShortMoviesList() {
        return topShortMoviesList;
    }
    
    public MutableLiveData<ArrayList<SimpleVideo>> getTopSongsList() {
        return topSongsList;
    }
    
    public MutableLiveData<ArrayList<SimpleVideo>> getTopComediesList() {
        return topComediesList;
    }
}
