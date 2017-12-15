package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.recent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.RecentTrack;
import com.fstyle.androidtrainning.model.RecentTracks;

import java.util.ArrayList;

/**
 * Created by phong on 12/13/17.
 */

public class RecentRecyclerAdapter extends RecyclerView
        .Adapter<RecentRecyclerAdapter.RecyclerViewHolder> {

    private RecentTracks mTracks = new RecentTracks();
    private Context mContext;

    public RecentRecyclerAdapter(Context context) {
        mTracks.setTrack(new ArrayList<RecentTrack>());
        mContext = context;
    }

    public void updateData(RecentTracks tracks) {
        if (tracks.getTrack() == null) {
            return;
        }
        mTracks.setTrack(tracks.getTrack());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_song, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTracks.getTrack().size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTxtSong, mTxtSinger;
        private static final int SMALL_IMAGE = 0;
        private static final int MIN_LENGTH = 0;
        private static final int MAX_LENGTH = 25;

        public RecyclerViewHolder(View itemView) {

            super(itemView);
            mImageView = itemView.findViewById(R.id.image_song);
            mTxtSong = itemView.findViewById(R.id.text_name_song);
            mTxtSinger = itemView.findViewById(R.id.text_name_singer);
        }

        public void bind(int position) {
            getSongsRecent(position);
            getArtistsRecent(position);
            getImagesRecent(position);
        }

        private void getSongsRecent(int position) {
            String songs = mTracks.getTrack().get(position).getName();
            if (songs.length() >= MAX_LENGTH) {
                String subSongs = songs.substring(MIN_LENGTH, MAX_LENGTH) + "...";
                mTxtSong.setText(subSongs);
            } else {
                mTxtSong.setText(songs);
            }
        }

        private void getArtistsRecent(int position) {
            mTxtSinger.setText(mTracks.getTrack().get(position)
                    .getArtist().getText());
        }

        private void getImagesRecent(int position) {
            String urlImage = mTracks.getTrack().get(position).getImage()
                    .get(SMALL_IMAGE).getText();
            if (!urlImage.isEmpty()) {
                Glide.with(mContext).load(urlImage).into(mImageView);
            } else {
                mImageView.setImageResource(R.drawable.img_demo_100x100);
            }
        }
    }
}
