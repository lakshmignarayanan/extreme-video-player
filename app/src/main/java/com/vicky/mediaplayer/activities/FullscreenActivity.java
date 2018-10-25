package com.vicky.mediaplayer.activities;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.vicky.mediaplayer.R;

public class FullscreenActivity extends Activity {

    String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        uri = getIntent().getStringExtra("uri");
        startVideo(uri);
    }

    public void startVideo(String uri) {
        VideoView videoView = (VideoView) findViewById(R.id.fs_videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        Uri uriobj = Uri.parse(uri);
        videoView.setVideoURI(uriobj);
        videoView.requestFocus();
        videoView.start();

    }
}
