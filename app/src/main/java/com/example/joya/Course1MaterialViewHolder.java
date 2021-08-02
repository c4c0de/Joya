package com.example.joya;

import android.app.Application;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.media2.exoplayer.external.SimpleExoPlayer;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ui.PlayerView;

import org.jetbrains.annotations.NotNull;

public class Course1MaterialViewHolder extends RecyclerView.ViewHolder {

    SimpleExoPlayer exoPlayer;
    PlayerView playerView;

    public Course1MaterialViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
    }

    public void setPlayer(Application activity, String title, String vurl) {

        TextView textView = itemView.findViewById(R.id.tvPlayerTitle);
        TextView textView1 = itemView.findViewById(R.id.tvPlayerTitle1);

        textView.setText(title);
        textView1.setText(vurl);

//        try {
//            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(activity).build();
//            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
//            exoPlayer = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(activity);
//            Uri video = Uri.parse(vurl);
//            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("video");
//            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//            MediaSource mediaSource = new ExtractorMediaSource(video, dataSourceFactory, extractorsFactory, null, null);
//            playerView.setPlayer(exoPlayer);
//            exoPlayer.prepare(mediaSource);
//            exoPlayer.setPlayWhenReady(false);
//
//
//
//
//
//        }catch (Exception e){
//            Log.e("ViewHolder","exoPlayer crashed"+e.toString());
//        }

    }
}

