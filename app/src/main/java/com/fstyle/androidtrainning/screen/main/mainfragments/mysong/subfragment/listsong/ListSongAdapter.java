package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;
import com.fstyle.androidtrainning.model.Track;
import com.like.LikeButton;
import com.like.OnLikeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/17/17.
 */

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.RecyclerViewHolder> {

    private List<Track> mTrack = new ArrayList<>();
    private Context mContext;
    private OnFavoriteClick mOnFavoriteClick;
    private List<TrackEntity> mFavorites = new ArrayList<>();

    public ListSongAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTrack.addAll(tracks);
        notifyDataSetChanged();
    }

    public void updateFavorite(List<TrackEntity> favorites) {
        if (favorites == null) {
            return;
        }
        mFavorites.addAll(favorites);
        notifyDataSetChanged();
    }

    public void setOnFavoriteClick(OnFavoriteClick onFavoriteClick) {
        mOnFavoriteClick = onFavoriteClick;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_song, parent, false);
        return new RecyclerViewHolder(view, mOnFavoriteClick, mFavorites);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTrack.size();
    }

    public final class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTxtNameSong, mTxtNameSinger;
        private LikeButton mLikeButton;
        private OnFavoriteClick mOnFavoriteClick;
        private List<TrackEntity> mFavorites;
        private boolean isFavoriteClicked = false;

        public RecyclerViewHolder(View itemView, OnFavoriteClick onFavoriteClick,
                List<TrackEntity> favorites) {
            super(itemView);
            initViews(onFavoriteClick);
            mFavorites = favorites;
        }

        private void initViews(OnFavoriteClick onFavoriteClick) {
            mImageView = itemView.findViewById(R.id.image_song);
            mTxtNameSong = itemView.findViewById(R.id.text_name_song);
            mTxtNameSinger = itemView.findViewById(R.id.text_name_singer);
            mLikeButton = itemView.findViewById(R.id.image_favorite);
            mOnFavoriteClick = onFavoriteClick;
        }

        private void handleEvents(final int position) {

            mLikeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    mLikeButton.setLiked(true);
                    mOnFavoriteClick.onFavoriteClicked(mTrack.get(position));
                    isFavoriteClicked = !isFavoriteClicked;
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    mLikeButton.setLiked(false);
                    mOnFavoriteClick.onUnFavoriteClicked(mTrack.get(position));
                    isFavoriteClicked = !isFavoriteClicked;
                }
            });
        }

        public void bind(int position) {
            String name = mTrack.get(position).getName();
            String artist = mTrack.get(position).getNameArtist();
            mTxtNameSong.setText(name);
            mTxtNameSinger.setText(artist);
            mLikeButton.setLiked(false);
            handleEvents(position);
            for (int i = 0; i < mFavorites.size(); i++) {
                if (name.equals(mFavorites.get(i).getNameSong()) && artist.equals(
                        mFavorites.get(i).getNameArtist())) {
                    isFavoriteClicked = true;
                    mLikeButton.setLiked(true);
                    break;
                }
            }
        }
    }
}
