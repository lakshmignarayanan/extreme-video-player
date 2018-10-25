package com.vicky.mediaplayer.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.vicky.mediaplayer.R;

public class VideoPlayer extends AppCompatActivity {

    String uri;
    Button btn_fullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        btn_fullscreen = (Button) findViewById(R.id.btn_fullscreen);
        btn_fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo go to fullscreen view
                Intent i = new Intent(VideoPlayer.this, FullscreenActivity.class);
                i.putExtra("uri", uri);
                startActivity(i);
            }
        });

        uri = getIntent().getStringExtra("uri");
        startVideo(uri);
    }

    public void startVideo(String uri) {
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        Uri uriobj = Uri.parse(uri);
        videoView.setVideoURI(uriobj);
        videoView.requestFocus();
        videoView.start();

    }
}
