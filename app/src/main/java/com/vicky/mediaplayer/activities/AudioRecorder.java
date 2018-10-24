package com.vicky.mediaplayer.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vicky.mediaplayer.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AudioRecorder extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private Button play, stop, btn_record;
    private MediaRecorder myAudioRecorder;
    private String outputFile;

    public static final int RECORD_AUDIO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("lucky123", "AudioRecorder on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);
        checkForRecordingPermission();
    }

    private void initRecorderUI() {
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        btn_record = (Button) findViewById(R.id.button_record);
        stop.setEnabled(false);
        play.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath();

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // init new MediaRecorder object
                    createNewRecorder();
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
                    String formattedDate = df.format(c);
                    Log.i("lucky123", "formatteddate " + formattedDate);
                    outputFile = outputFile + "/recording_" + formattedDate + ".3gp";
                    myAudioRecorder.setOutputFile(outputFile);
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();

                    Log.i("lucky123", "record clicked. myaudiorecorder started");
                } catch (IllegalStateException ise) {
                    Log.i("lucky123", "record on click ise");
                } catch (IOException ioe) {
                    Log.i("lucky123", "record onclick ioe");
                }
                btn_record.setEnabled(false);
                play.setEnabled(false);
                stop.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Recording audio...", Toast.LENGTH_SHORT).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lucky123", "stop clicked");
                stopAndReleaseAudioRecorder();
                btn_record.setEnabled(true);
                stop.setEnabled(false);
                play.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Recording saved at " + outputFile, Toast.LENGTH_LONG).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    btn_record.setEnabled(false);
                    play.setEnabled(false);
                    stop.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            play.setEnabled(true);
                            btn_record.setEnabled(true);
                            stop.setEnabled(false);
                        }
                    });
                } catch (Exception e) {
                    // make something
                }
            }
        });

    }

    private void stopAndReleaseAudioRecorder() {
        if (myAudioRecorder != null) {
            myAudioRecorder.stop();
            myAudioRecorder.release();
            myAudioRecorder = null;
        }

    }

    private void checkForRecordingPermission() {
        Log.i("lucky123", "checking for permission");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            Log.i("lucky123", "no premission. ask for one");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO);
        } else {
            Log.i("lucky123", "has permission. init the recorder");
            initRecorderUI();
        }
    }

    private void createNewRecorder() {
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RECORD_AUDIO && grantResults[0] == 1) {
            Log.i("lucky123", "permission received. go ahead!");

        } else {
            Log.i("lucky123", "permission denied :( ");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopAndReleaseAudioRecorder();
    }
}