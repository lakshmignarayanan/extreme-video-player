package com.vicky.mediaplayer.activities;

import android.media.AudioManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.vicky.mediaplayer.R;
import com.vicky.mediaplayer.listeners.GestureDetection;
import com.vicky.mediaplayer.listeners.OnSwipeTouchListener;

public class VideoPlayer extends AppCompatActivity  {

    String uri;
    VideoView videoView;
    AudioManager audioManager;
    private SurfaceView surfaceView;
//    private GestureDetection detector;
//    int currentPosition, currentVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        uri = getIntent().getStringExtra("uri");

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
//        detector = new GestureDetection(this, this);
        startVideo(uri);
        surfaceView = new SurfaceView(this);
        surfaceView.setZOrderMediaOverlay(true);
        surfaceView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                Toast.makeText(VideoPlayer.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(VideoPlayer.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(VideoPlayer.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(VideoPlayer.this, "bottom", Toast.LENGTH_SHORT).show();
            }
        });
//        setContentView(surfaceView);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
//        detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    public void startVideo(String uri) {
        videoView = (VideoView) findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        Uri uriobj = Uri.parse(uri);
        videoView.setVideoURI(uriobj);
        videoView.requestFocus();
        videoView.start();

    }

//    @Override
//    public void onSwipe(int direction) {
//        Log.i("lucky123", "swipe detected");
//        // TODO Auto-generated method stub
//        String str = "";
//
//        switch (direction) {
//
//            case GestureDetection.SWIPE_LEFT:
//
//                currentPosition = videoView.getCurrentPosition();
//                currentPosition = videoView.getCurrentPosition() - 10000;
//                videoView.seekTo(currentPosition);
//                str = "Swipe Left";
//                break;
//
//            case GestureDetection.SWIPE_RIGHT:
//
//                currentPosition = videoView.getCurrentPosition();
//                currentPosition = videoView.getCurrentPosition() + 10000;
//                videoView.seekTo(currentPosition);
//                str = "Swipe Right";
//                break;
//
//            case GestureDetection.SWIPE_DOWN:
//
//                currentVolume = audioManager
//                        .getStreamVolume(AudioManager.STREAM_MUSIC);
//                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
//                        currentVolume - 1, 0);
//                str = "Swipe Down";
//                break;
//            case GestureDetection.SWIPE_UP:
//
//                currentVolume = audioManager
//                        .getStreamVolume(AudioManager.STREAM_MUSIC);
//                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
//                        currentVolume + 1, 0);
//                str = "Swipe Up";
//                break;
//
//        }
//        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
//    }

}
