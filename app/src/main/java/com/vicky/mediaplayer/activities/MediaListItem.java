package com.vicky.mediaplayer.activities;

import android.view.View;

public class MediaListItem {
    public String title;
    public float duration;
    public String uri;
    public int type;

    public String getTitle() {
        return title;
    }

    public float getDuration() {
        return duration;
    }

    public String getUri() {
        return uri;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
