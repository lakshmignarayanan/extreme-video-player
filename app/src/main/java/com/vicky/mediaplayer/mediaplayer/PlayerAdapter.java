package com.vicky.mediaplayer.mediaplayer;

public interface PlayerAdapter {

    void loadMedia(String resourceId);

    void release();

    boolean isPlaying();

    void play();

    void reset();

    void pause();

    void initializeProgressCallback();

    void seekTo(int position);
    
}
