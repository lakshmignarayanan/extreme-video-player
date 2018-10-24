package com.vicky.mediaplayer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.vicky.mediaplayer.fragments.AudioFragment;
import com.vicky.mediaplayer.R;
import com.vicky.mediaplayer.fragments.RecorderFragment;
import com.vicky.mediaplayer.fragments.VideoFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new VideoFragment();
                    break;

                case R.id.navigation_dashboard:
                    fragment = new AudioFragment();
                    break;

                case R.id.navigation_notifications:
                    fragment = new RecorderFragment();
//                    goToAudioRecorderActivity();
                    break;
            }

            return loadFragment(fragment);
        }
    };

    private void goToAudioRecorderActivity() {
        Intent i = new Intent();
        i.setClass(this, AudioRecorder.class);
        startActivity(i);
    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new VideoFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
