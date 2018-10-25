package com.vicky.mediaplayer.activities;

import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.vicky.mediaplayer.R;
import com.vicky.mediaplayer.listeners.GestureDetection;
import com.vicky.mediaplayer.listeners.OnSwipeTouchListener;

public class VideoPlayer extends AppCompatActivity implements GestureDetection.SimpleGestureListener {

    String uri;
    VideoView videoView;
    AudioManager audioManager;
    private SurfaceView surfaceView;
    private GestureDetector detector;
    private GestureDetection detection;
    int currentPosition, currentVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        uri = getIntent().getStringExtra("uri");

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        detection = new GestureDetection(this, this);
        startVideo(uri);
//        surfaceView = new SurfaceView(this);
//        surfaceView.setZOrderMediaOverlay(true);
//        surfaceView.setOnTouchListener(new OnSwipeTouchListener(this) {
//            public void onSwipeTop() {
//                Toast.makeText(VideoPlayer.this, "top", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeRight() {
//                Toast.makeText(VideoPlayer.this, "right", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeLeft() {
//                Toast.makeText(VideoPlayer.this, "left", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeBottom() {
//                Toast.makeText(VideoPlayer.this, "bottom", Toast.LENGTH_SHORT).show();
//            }
//        });
//        setContentView(surfaceView);
//        RelativeLayout overlay = findViewById(R.id.overlay);
//        videoView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                detector.onTouchEvent(motionEvent);
//                return true;
//            }
//        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
        this.detection.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    public void startVideo(String uri) {
        videoView = (VideoView) findViewById(R.id.videoView);
        final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        Uri uriobj = Uri.parse(uri);
        videoView.setVideoURI(uriobj);
        videoView.requestFocus();
        videoView.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaController.show(0);
            }
        }, 50);

    }


//    // In the SimpleOnGestureListener subclass you should override
//    // onDown and any other gesture that you want to detect.
//    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
//
//        @Override
//        public boolean onDown(MotionEvent event) {
//            Log.d("TAG", "onDown: ");
//
//            // don't return false here or else none of the other
//            // gestures will work
//            return true;
//        }
//
//        @Override
//        public boolean onSingleTapConfirmed(MotionEvent e) {
//            Log.i("TAG", "onSingleTapConfirmed: ");
//            return true;
//        }
//
//        @Override
//        public void onLongPress(MotionEvent e) {
//            Log.i("TAG", "onLongPress: ");
//        }
//
//        @Override
//        public boolean onDoubleTap(MotionEvent e) {
//            Log.i("TAG", "onDoubleTap: ");
//            return true;
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2,
//                                float distanceX, float distanceY) {
//            Log.i("TAG", "onScroll: ");
//            return true;
//        }
//
//        @Override
//        public boolean onFling(MotionEvent event1, MotionEvent event2,
//                               float velocityX, float velocityY) {
//            Log.d("TAG", "onFling: ");
//            return true;
//        }
//    }

    @Override
    public void onSwipe(int direction) {
        Log.i("lucky123", "swipe detected");
        // TODO Auto-generated method stub
        String str = "";

        switch (direction) {

            case GestureDetection.SWIPE_LEFT:

                currentPosition = videoView.getCurrentPosition();
                currentPosition = videoView.getCurrentPosition() - 10000;
                videoView.seekTo(currentPosition);
                str = "Swipe Left";
                break;

            case GestureDetection.SWIPE_RIGHT:

                currentPosition = videoView.getCurrentPosition();
                currentPosition = videoView.getCurrentPosition() + 10000;
                videoView.seekTo(currentPosition);
                str = "Swipe Right";
                break;

            case GestureDetection.SWIPE_DOWN:

                currentVolume = audioManager
                        .getStreamVolume(AudioManager.STREAM_MUSIC);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        currentVolume - 1, 0);
                str = "Swipe Down";
                break;
            case GestureDetection.SWIPE_UP:

                currentVolume = audioManager
                        .getStreamVolume(AudioManager.STREAM_MUSIC);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        currentVolume + 1, 0);
                str = "Swipe Up";
                break;

        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    public class MyMediaController extends MediaController {
        public MyMediaController(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyMediaController(Context context, boolean useFastForward) {
            super(context, useFastForward);
        }

        public MyMediaController(Context context) {
            super(context);
        }

        @Override
        public void show(int timeout) {
            super.show(0);
        }

    }
}
