package com.vicky.mediaplayer.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import com.vicky.mediaplayer.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AudioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AudioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public AudioFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.audio_list);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        MediaAdapter adapter = new MediaAdapter(getActivity(), getPlayList(Environment.getExternalStorageDirectory().getAbsolutePath()));
        mRecyclerView.setAdapter(adapter);
        return view;
    }


    ArrayList<MediaListItem> getPlayList(String rootPath) {
        ArrayList<MediaListItem> fileList = new ArrayList<>();


        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    MediaListItem mediaListItem = new MediaListItem();
                    mediaListItem.title = file.getName();
                    mediaListItem.uri = file.getAbsolutePath();
                    mediaListItem.type = 2;
                    fileList.add(mediaListItem);
                }
            }
            return fileList;
        } catch (Exception e) {
            return null;
        }
    }
}
