package com.kannadakali.developerapp.app.devappforkk.data.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.database.*;
import com.kannadakali.developerapp.app.devappforkk.data.model.SimpleVideo;
import com.kannadakali.developerapp.app.devappforkk.enums.EntertainmentType;
import com.kannadakali.developerapp.app.devappforkk.enums.VideoType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by varun.am on 27/12/18
 */
public class EntertainmentContentProvider {

    private static final String TAG = EntertainmentContentProvider.class.getSimpleName();

    private static final String FEATURED = "featured";
    private static final String MOVIES = "movies";
    private static final String ENTERTAINMENT = "entertainment";
    private static final String TRAILERS = "trailers";
    private static final String COMEDY = "comedy";
    private static final String SONGS = "songs";
    private static final String VIDEOS = "videos";
    private static final String TOP = "top";
    private static final String VOTES = "votes";
    private static final String SHORT_MOVIES = "short_movies";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EntertainmentLoadedCallbacks entertainmentLoadedCallbacks;

    public EntertainmentContentProvider(EntertainmentLoadedCallbacks entertainmentLoadedCallbacks) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        this.entertainmentLoadedCallbacks = entertainmentLoadedCallbacks;
    }

    public void loadEntertainmentPack() {
        loadFeaturedVideos();
        loadTopMovies();
        loadTopSongs();
        loadLatestTrailers();
        loadTopComedyScenes();
        loadLatestShortMovies();
    }

    private void loadFeaturedVideos() {
        databaseReference.child(ENTERTAINMENT).child(FEATURED).child(VIDEOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (entertainmentLoadedCallbacks != null) {
                    ArrayList<SimpleVideo> featuredVideos = new ArrayList<>();
                    for (DataSnapshot videoSnapshot : dataSnapshot.getChildren()) {
                        try {
                            SimpleVideo featuredVideo = videoSnapshot.getValue(SimpleVideo.class);
                            featuredVideos.add(featuredVideo);

                            //logging data
                            Log.d(TAG, "title - " + featuredVideo.getTitle());
                            Log.d(TAG, "id - " + featuredVideo.getId());
                            Log.d(TAG, "votes - " + featuredVideo.getVotes());
                            Log.d(TAG, "thumbnail_url - " + featuredVideo.getThumbnailUrl());
                            Log.d(TAG, "video_type - " + featuredVideo.getVideo_type());
                        } catch (Exception e) {
                            Log.e(TAG, "junk object received: " + videoSnapshot.getValue());
                        }
                    }
                    entertainmentLoadedCallbacks.onFeaturedVideosLoaded(featuredVideos);
                } else {
                    throw new NullPointerException("callback instance is NULL");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadTopMovies() {
        databaseReference.child(ENTERTAINMENT).child(MOVIES).child(TOP).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (entertainmentLoadedCallbacks != null) {
                    ArrayList<SimpleVideo> topMovies = new ArrayList<>();
                    for (DataSnapshot videoSnapshot : dataSnapshot.getChildren()) {
                        try {
                            SimpleVideo featuredVideo = videoSnapshot.getValue(SimpleVideo.class);
                            topMovies.add(featuredVideo);

                            //logging data
                            Log.d(TAG, "title - " + featuredVideo.getTitle());
                            Log.d(TAG, "id - " + featuredVideo.getId());
                            Log.d(TAG, "votes - " + featuredVideo.getVotes());
                            Log.d(TAG, "thumbnail_url - " + featuredVideo.getThumbnailUrl());
                            Log.d(TAG, "video_type - " + featuredVideo.getVideo_type());
                        } catch (Exception e) {
                            Log.e(TAG, "junk object received: " + videoSnapshot.getValue());
                        }
                    }
                    entertainmentLoadedCallbacks.onTopMoviesLoaded(topMovies);
                } else {
                    throw new NullPointerException("callback instance is NULL");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadLatestTrailers() {
        databaseReference.child(ENTERTAINMENT).child(TRAILERS).child(TOP).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (entertainmentLoadedCallbacks != null) {
                    ArrayList<SimpleVideo> latestTrailers = new ArrayList<>();
                    for (DataSnapshot videoSnapshot : dataSnapshot.getChildren()) {
                        try {
                            SimpleVideo featuredVideo = videoSnapshot.getValue(SimpleVideo.class);
                            latestTrailers.add(featuredVideo);

                            //logging data
                            Log.d(TAG, "title - " + featuredVideo.getTitle());
                            Log.d(TAG, "id - " + featuredVideo.getId());
                            Log.d(TAG, "votes - " + featuredVideo.getVotes());
                            Log.d(TAG, "thumbnail_url - " + featuredVideo.getThumbnailUrl());
                            Log.d(TAG, "video_type - " + featuredVideo.getVideo_type());
                        } catch (Exception e) {
                            Log.e(TAG, "junk object received: " + videoSnapshot.getValue());
                        }
                    }
                    entertainmentLoadedCallbacks.onLatestTrailersLoaded(latestTrailers);
                } else {
                    throw new NullPointerException("callback instance is NULL");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadLatestShortMovies() {
        databaseReference.child(ENTERTAINMENT).child(SHORT_MOVIES).child(TOP).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (entertainmentLoadedCallbacks != null) {
                    ArrayList<SimpleVideo> topShortMovies = new ArrayList<>();
                    for (DataSnapshot videoSnapshot : dataSnapshot.getChildren()) {
                        try {
                            SimpleVideo featuredVideo = videoSnapshot.getValue(SimpleVideo.class);
                            topShortMovies.add(featuredVideo);

                            //logging data
                            Log.d(TAG, "title - " + featuredVideo.getTitle());
                            Log.d(TAG, "id - " + featuredVideo.getId());
                            Log.d(TAG, "votes - " + featuredVideo.getVotes());
                            Log.d(TAG, "thumbnail_url - " + featuredVideo.getThumbnailUrl());
                            Log.d(TAG, "video_type - " + featuredVideo.getVideo_type());
                        } catch (Exception e) {
                            Log.e(TAG, "junk object received: " + videoSnapshot.getValue());
                        }
                    }
                    entertainmentLoadedCallbacks.onTopShortMoviesLoaded(topShortMovies);
                } else {
                    throw new NullPointerException("callback instance is NULL");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadTopComedyScenes() {
        databaseReference.child(ENTERTAINMENT).child(COMEDY).child(TOP).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (entertainmentLoadedCallbacks != null) {
                    ArrayList<SimpleVideo> topComedyScenes = new ArrayList<>();
                    for (DataSnapshot videoSnapshot : dataSnapshot.getChildren()) {
                        try {
                            SimpleVideo featuredVideo = videoSnapshot.getValue(SimpleVideo.class);
                            topComedyScenes.add(featuredVideo);

                            //logging data
                            Log.d(TAG, "title - " + featuredVideo.getTitle());
                            Log.d(TAG, "id - " + featuredVideo.getId());
                            Log.d(TAG, "votes - " + featuredVideo.getVotes());
                            Log.d(TAG, "thumbnail_url - " + featuredVideo.getThumbnailUrl());
                            Log.d(TAG, "video_type - " + featuredVideo.getVideo_type());
                        } catch (Exception e) {
                            Log.e(TAG, "junk object received: " + videoSnapshot.getValue());
                        }
                    }
                    entertainmentLoadedCallbacks.onTopComedyLoaded(topComedyScenes);
                } else {
                    throw new NullPointerException("callback instance is NULL");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadTopSongs() {
        databaseReference.child(ENTERTAINMENT).child(SONGS).child(TOP).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (entertainmentLoadedCallbacks != null) {
                    ArrayList<SimpleVideo> topSongs = new ArrayList<>();
                    for (DataSnapshot videoSnapshot : dataSnapshot.getChildren()) {
                        try {
                            SimpleVideo featuredVideo = videoSnapshot.getValue(SimpleVideo.class);
                            topSongs.add(featuredVideo);

                            //logging data
                            Log.d(TAG, "title - " + featuredVideo.getTitle());
                            Log.d(TAG, "id - " + featuredVideo.getId());
                            Log.d(TAG, "votes - " + featuredVideo.getVotes());
                            Log.d(TAG, "thumbnail_url - " + featuredVideo.getThumbnailUrl());
                            Log.d(TAG, "video_type - " + featuredVideo.getVideo_type());
                        } catch (Exception e) {
                            Log.e(TAG, "junk object received: " + videoSnapshot.getValue());
                        }
                    }
                    entertainmentLoadedCallbacks.onTopSongsLoaded(topSongs);
                } else {
                    throw new NullPointerException("callback instance is NULL");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addVideo(Context context, EntertainmentType entertainmentType, SimpleVideo simpleVideo) {
        databaseReference = getDatabaseReferenceFor(entertainmentType);
        DatabaseReference videoReference;
        if (databaseReference != null) {
            videoReference = databaseReference.child(getJsonObjectParentFor(simpleVideo));
            videoReference.child("title").setValue(simpleVideo.getTitle());
            videoReference.child("video_type").setValue(simpleVideo.getVideo_type());
            videoReference.child("votes").setValue(simpleVideo.getVotes());
            videoReference.child("id").setValue(simpleVideo.getId());
            videoReference.child("sort_count").setValue(simpleVideo.getSort_count());
            videoReference.child("thumbnail_url").setValue(simpleVideo.getThumbnail_url());
            Toast.makeText(context, "Video added successfully!", Toast.LENGTH_LONG).show();
        }
    }

    public void updateVideo(Context context, EntertainmentType entertainmentType, SimpleVideo simpleVideo) {
        databaseReference = getDatabaseReferenceFor(entertainmentType);
        DatabaseReference videoReference;
        if (databaseReference != null) {
            videoReference = databaseReference.child(getJsonObjectParentFor(simpleVideo));
            videoReference.child("title").setValue(simpleVideo.getTitle());
            videoReference.child("video_type").setValue(simpleVideo.getVideo_type());
            videoReference.child("votes").setValue(simpleVideo.getVotes());
            videoReference.child("id").setValue(simpleVideo.getId());
            videoReference.child("sort_count").setValue(simpleVideo.getSort_count());
            videoReference.child("thumbnail_url").setValue(simpleVideo.getThumbnail_url());
            Toast.makeText(context, "Video updated successfully!", Toast.LENGTH_LONG).show();
        }
    }

    public void removeVieo(final Context context, EntertainmentType entertainmentType, @NotNull final SimpleVideo simpleVideo) {
        databaseReference = getDatabaseReferenceFor(entertainmentType);
        if (databaseReference != null) {
            databaseReference.child(getJsonObjectParentFor(simpleVideo))
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getKey() != null)
                                if (dataSnapshot.getKey().equals(getJsonObjectParentFor(simpleVideo)))
                                {
                                    dataSnapshot.getRef().removeValue();
                                    Toast.makeText(context, "Video deleted successfully!", Toast.LENGTH_LONG).show();
                                }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }
    }


    private String getJsonObjectParentFor(SimpleVideo simpleVideo) {
        if (simpleVideo.getVideo_type().equals(VideoType.YOUTUBE.getVideoType()))
            return simpleVideo.getId();
        else
            return simpleVideo.getId().replaceAll("[^A-Za-z0-9]", "");
    }

    private DatabaseReference getDatabaseReferenceFor(EntertainmentType entertainmentType) {
        switch (entertainmentType) {
            case MOVIE:
                return firebaseDatabase.getReference().child(ENTERTAINMENT).child(MOVIES).child(TOP);
            case SONGS:
                return firebaseDatabase.getReference().child(ENTERTAINMENT).child(SONGS).child(TOP);
            case COMEDY:
                return firebaseDatabase.getReference().child(ENTERTAINMENT).child(COMEDY).child(TOP);
            case TRAILERS:
                return firebaseDatabase.getReference().child(ENTERTAINMENT).child(TRAILERS).child(TOP);
            case FEATURED:
                return firebaseDatabase.getReference().child(ENTERTAINMENT).child(FEATURED).child(VIDEOS);
            case SHORT_MOVIES:
                return firebaseDatabase.getReference().child(ENTERTAINMENT).child(SHORT_MOVIES).child(TOP);
            default:
                return null;
        }
    }

    public ArrayList<SimpleVideo> getDummyList() {
        ArrayList<SimpleVideo> simpleYouTubeVideos = new ArrayList<>();
        SimpleVideo simpleYouTubeVideo = new SimpleVideo("Ovq4k8L2YO0", "Kolar Gold Fields \n(trailer)");
        SimpleVideo simpleYouTubeVideo1 = new SimpleVideo("osAKnebiPSo", "Tequila \n(party song)");
        SimpleVideo simpleYouTubeVideo2 = new SimpleVideo("ydwqn9s400s", "Edeyolage guitaru \n(song)");
        SimpleVideo simpleYouTubeVideo3 = new SimpleVideo("Ovq4k8L2YO0", "Kolar Gold Fields \n(trailer)");
        SimpleVideo simpleYouTubeVideo4 = new SimpleVideo("osAKnebiPSo", "Tequila \n(party song)");
        SimpleVideo simpleYouTubeVideo5 = new SimpleVideo("ydwqn9s400s", "Edeyolage guitaru \n(song)");

        simpleYouTubeVideos.add(simpleYouTubeVideo);
        simpleYouTubeVideos.add(simpleYouTubeVideo1);
        simpleYouTubeVideos.add(simpleYouTubeVideo2);
        simpleYouTubeVideos.add(simpleYouTubeVideo3);
        simpleYouTubeVideos.add(simpleYouTubeVideo4);
        simpleYouTubeVideos.add(simpleYouTubeVideo5);

        return simpleYouTubeVideos;
    }
}
