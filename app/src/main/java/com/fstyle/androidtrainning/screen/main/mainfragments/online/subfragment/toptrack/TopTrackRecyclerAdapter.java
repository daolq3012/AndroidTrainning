package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.toptrack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Track;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/14/17.
 */

public class TopTrackRecyclerAdapter
        extends RecyclerView.Adapter<TopTrackRecyclerAdapter.RecyclerViewHolder> {

    private Context mContext;
    private List<Track> mTracks = new ArrayList<>();

    public TopTrackRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTracks.addAll(tracks);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_track, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTxtNameSong;
        private TextView mTxtNameSinger;
        private static final int MEDIUM_IMAGE = 1;
        private static final int MAX_LENGTH = 30;
        private static final int MIN_LENGTH = 0;
        private static final String MORE = "...";

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_top_track);
            mTxtNameSong = itemView.findViewById(R.id.text_name_song);
            mTxtNameSinger = itemView.findViewById(R.id.text_name_singer);
        }

        public void bind(int position) {
            setImage(position);
            setNameSong(position);
            setNameArtist(position);
        }

        private void setImage(int position) {
            if (mTracks.get(position) == null
                    || mTracks.get(position).getImage() == null
                    || mTracks.get(position).getImage().size() < MEDIUM_IMAGE + 1) {
                return;
            }
            String urlImage = mTracks.get(position).getImage().get(MEDIUM_IMAGE).getText();
            if (!urlImage.isEmpty()) {
                Glide.with(mContext).load(urlImage).into(mImageView);
            } else {
                mImageView.setImageResource(R.drawable.img_demo_50x50);
            }
        }

        private void setNameArtist(int position) {
            if (mTracks.get(position) == null || mTracks.get(position).getArtist() == null) {
                return;
            }
            String nameArtist = mTracks.get(position).getArtist().getName();
            if (nameArtist.length() >= MAX_LENGTH) {
                String subNameArtist = nameArtist.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtNameSinger.setText(subNameArtist);
            } else {
                mTxtNameSinger.setText(nameArtist);
            }
        }

        private void setNameSong(int position) {
            if (mTracks.get(position) == null || mTracks.get(position).getName() == null) {
                return;
            }
            String nameSong = mTracks.get(position).getName();
            if (nameSong.length() >= MAX_LENGTH) {
                String subNameSong = nameSong.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtNameSong.setText(subNameSong);
            } else {
                mTxtNameSong.setText(nameSong);
            }
        }
    }
}
