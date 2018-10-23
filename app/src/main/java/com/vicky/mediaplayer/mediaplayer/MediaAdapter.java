package com.vicky.mediaplayer.mediaplayer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MyViewHolder> {

    private final Context context;
    private List<MediaListItem> mediaList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.file_name);
        }
    }


    public MediaAdapter(Context context, ArrayList<MediaListItem> allMedia) {
        this.context = context;
        this.mediaList = allMedia;
    }

    public void setMediaList(List<MediaListItem> mediaList) {
        this.mediaList = mediaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.media_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MediaListItem mediaItem = mediaList.get(position);
        holder.title.setText(mediaItem.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaItem.type == 1) {
                    //Video click
                    Log.i("lucky123", "video onclick uri => " + mediaItem.uri);
                    // TODO
                    goToVideoPlayerAndStart(mediaItem);
                } else {
                    //Audio click
                    Log.i("lucky123", "audio onclick uri => " + mediaItem.uri);
                    // TODO go to audio player activity
                    goToAudioPlayerActivity(mediaItem);


                }
            }
        });

    }

    public void goToAudioPlayerActivity(MediaListItem media) {
        Intent intent = new Intent(context, AudioPlayer.class);
        intent.setClass(context, AudioPlayer.class);
        intent.putExtra("uri", media.uri);
        context.startActivity(intent);
    }

    public void goToVideoPlayerAndStart(MediaListItem item) {
        Intent intent = new Intent(context, VideoPlayer.class);
        intent.putExtra("uri", item.uri);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }
}
