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

    private List<Track> mTracks = new ArrayList<>();
    private Context mContext;
    private OnFavoriteClick mOnFavoriteClick;
    private List<TrackEntity> mFavorites = new ArrayList<>();
    private OnItemListSongClickListener mOnItemListSongClickListener;
    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 15;

    public ListSongAdapter(Context context,
            OnItemListSongClickListener onItemListSongClickListener) {
        mContext = context;
        mOnItemListSongClickListener = onItemListSongClickListener;
    }

    public void updateData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTracks.addAll(tracks);
        notifyDataSetChanged();
    }

    public void updateFavorite(List<TrackEntity> favorites) {
        if (favorites == null) {
            return;
        }
        mFavorites.addAll(favorites);
        notifyDataSetChanged();
    }

    public void setOnItemListSongClickListener(
            OnItemListSongClickListener onItemListSongClickListener) {
        mOnItemListSongClickListener = onItemListSongClickListener;
    }

    public void setOnFavoriteClick(OnFavoriteClick onFavoriteClick) {
        mOnFavoriteClick = onFavoriteClick;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_song, parent, false);
        return new RecyclerViewHolder(view, mOnFavoriteClick, mFavorites,
                mOnItemListSongClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public final class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTxtNameSong, mTxtNameSinger;
        private LikeButton mLikeButton;
        private OnFavoriteClick mOnFavoriteClick;
        private List<TrackEntity> mFavorites;
        private boolean isFavoriteClicked = false;
        private int position = 0;

        public RecyclerViewHolder(View itemView, OnFavoriteClick onFavoriteClick,
                List<TrackEntity> favorites,
                OnItemListSongClickListener onItemListSongClickListener) {
            super(itemView);
            initViews(onFavoriteClick, favorites, onItemListSongClickListener);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemListSongClickListener.onItemClicked(position, mTracks);
                }
            });
        }

        private void initViews(OnFavoriteClick onFavoriteClick, List<TrackEntity> favorites,
                OnItemListSongClickListener onItemListSongClickListener) {
            mImageView = itemView.findViewById(R.id.image_song);
            mTxtNameSong = itemView.findViewById(R.id.text_name_song);
            mTxtNameSinger = itemView.findViewById(R.id.text_name_singer);
            mLikeButton = itemView.findViewById(R.id.image_favorite);
            mOnFavoriteClick = onFavoriteClick;
            mFavorites = favorites;
            mOnItemListSongClickListener = onItemListSongClickListener;
        }

        private void handleEvents(final int position) {

            mLikeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    mLikeButton.setLiked(true);
                    mOnFavoriteClick.onFavoriteClicked(mTracks.get(position));
                    isFavoriteClicked = !isFavoriteClicked;
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    mLikeButton.setLiked(false);
                    mOnFavoriteClick.onUnFavoriteClicked(mTracks.get(position));
                    isFavoriteClicked = !isFavoriteClicked;
                }
            });
        }

        public void bind(int position) {
            this.position = position;
            String name = mTracks.get(position).getName();
            String artist = mTracks.get(position).getNameArtist();
            setName(name);
            setNameArtist(artist);
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

        private void setName(String name) {
            if (name.length() > MAX_LENGTH) {
                String subName = name.substring(MIN_LENGTH, MAX_LENGTH) + "...";
                mTxtNameSong.setText(subName);
            } else {
                mTxtNameSong.setText(name);
            }
        }

        private void setNameArtist(String artist) {
            if (artist.length() > MAX_LENGTH) {
                String subArtist = artist.substring(MIN_LENGTH, MAX_LENGTH) + "...";
                mTxtNameSinger.setText(subArtist);
            } else {
                mTxtNameSinger.setText(artist);
            }
        }
    }
}
