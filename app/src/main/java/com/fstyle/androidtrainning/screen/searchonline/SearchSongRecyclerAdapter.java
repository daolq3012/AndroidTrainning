package com.fstyle.androidtrainning.screen.searchonline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.SearchTrack;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/15/17.
 */

public class SearchSongRecyclerAdapter
        extends RecyclerView.Adapter<SearchSongRecyclerAdapter.RecyclerViewHolder> {

    private List<SearchTrack> mTracks = new ArrayList<>();
    private Context mContext;

    public SearchSongRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<SearchTrack> tracks) {
        if (tracks == null) {
            return;
        }
        mTracks = tracks;
        notifyDataSetChanged();
    }

    public void clearData() {
        if (mTracks == null) {
            return;
        }
        mTracks.clear();
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
        return mTracks.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTxtNameSong, mTxtNameSinger;
        private static final int MEDIUM_IMAGE = 1;
        private static final int MAX_LENGTH = 25;
        private static final int MIN_LENGTH = 0;
        private static final String MORE = "...";

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            mTxtNameSong = itemView.findViewById(R.id.text_upper);
            mTxtNameSinger = itemView.findViewById(R.id.text_lower);
        }

        public void bind(int position) {
            setImage(position);
            setNameArtist(position);
            setNameSong(position);
        }

        private void setImage(int position) {
            String urlImage = mTracks.get(position).getImage().get(MEDIUM_IMAGE).getText();
            if (!urlImage.isEmpty()) {
                Glide.with(mContext).load(urlImage).into(mImageView);
            } else {
                mImageView.setImageResource(R.drawable.ic_unknown_album);
            }
        }

        private void setNameArtist(int position) {
            String nameArtist = mTracks.get(position).getArtist();
            if (nameArtist.length() >= MAX_LENGTH) {
                String subNameArtist = nameArtist.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtNameSinger.setText(subNameArtist);
            } else {
                mTxtNameSinger.setText(nameArtist);
            }
        }

        private void setNameSong(int position) {
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
