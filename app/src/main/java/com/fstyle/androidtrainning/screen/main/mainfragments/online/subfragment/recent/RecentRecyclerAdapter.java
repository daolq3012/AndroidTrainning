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

public class RecentRecyclerAdapter extends RecyclerView.Adapter {

    private RecentTracks mTracks = new RecentTracks();
    private Context mContext;

    private static final int IMAGE = 0;
    private static final int TRACKS = 1;

    public RecentRecyclerAdapter(Context context) {
        mContext = context;
        mTracks.setTrack(new ArrayList<RecentTrack>());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IMAGE;
        } else if (position > 0) {
            return TRACKS;
        }
        return -1;
    }

    public void updateData(RecentTracks tracks) {
        if (tracks.getTrack() == null) {
            return;
        }
        mTracks.setTrack(tracks.getTrack());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case IMAGE:
                View itemImageView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_row_image_recent, parent, false);
                return new ImageViewHolder(itemImageView);
            case TRACKS:
                View viewTracks = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_song, parent, false);
                return new TracksViewHolder(viewTracks);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case IMAGE:
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                imageViewHolder.setImagesRecent(position);
                imageViewHolder.setSongsRecent(position);
                imageViewHolder.setArtistsRecent(position);
                break;
            case TRACKS:
                TracksViewHolder tracksViewHolder = (TracksViewHolder) holder;
                tracksViewHolder.setSongsRecent(position);
                tracksViewHolder.setArtistsRecent(position);
                tracksViewHolder.setImagesRecent(position);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mTracks.getTrack().size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageRecent;
        private TextView mTxtSong, mTxtSinger;
        private ImageView mImageView;

        private static final int EXTRA_IMAGE = 3;
        private static final int MEDIUM_IMAGE = 1;
        private static final int MIN_LENGTH = 0;
        private static final int MAX_LENGTH = 20;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageRecent = itemView.findViewById(R.id.image_recent);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            mImageView = itemView.findViewById(R.id.image_song);
            mTxtSong = itemView.findViewById(R.id.text_name_song);
            mTxtSinger = itemView.findViewById(R.id.text_name_singer);
        }

        private void setImagesRecent(int position) {
            if (mTracks.getTrack().get(position).getImage() == null
                    || mTracks.getTrack().get(position) == null
                    || mTracks.getTrack().get(position).getImage().size() < MEDIUM_IMAGE + 1
                    || mTracks.getTrack().get(position).getImage().size() < EXTRA_IMAGE + 1) {
                return;
            }
            String urlImage =
                    mTracks.getTrack().get(position).getImage().get(MEDIUM_IMAGE).getText();
            String urlExtraImage =
                    mTracks.getTrack().get(position).getImage().get(EXTRA_IMAGE).getText();
            if (urlImage != null && !urlImage.isEmpty()) {
                Glide.with(mContext).load(urlImage).into(mImageView);
                Glide.with(mContext).load(urlExtraImage).into(mImageRecent);
            } else {
                mImageView.setImageResource(R.drawable.ic_unknown);
                mImageRecent.setImageResource(R.drawable.img_demo_800x300);
            }
        }

        private void setSongsRecent(int position) {
            if (mTracks.getTrack().get(position) == null || mTracks.getTrack() == null) {
                return;
            }
            String songs = mTracks.getTrack().get(position).getName();
            if (songs.length() >= MAX_LENGTH) {
                String subSongs = songs.substring(MIN_LENGTH, MAX_LENGTH) + "...";
                mTxtSong.setText(subSongs);
            } else {
                mTxtSong.setText(songs);
            }
        }

        private void setArtistsRecent(int position) {
            if (mTracks.getTrack().get(position).getArtist() == null
                    || mTracks.getTrack() == null) {
                return;
            }
            mTxtSinger.setText(mTracks.getTrack().get(position).getArtist().getText());
        }
    }

    public class TracksViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtSong, mTxtSinger;
        private ImageView mImageView;

        private static final int MEDIUM_IMAGE = 1;
        private static final int MIN_LENGTH = 0;
        private static final int MAX_LENGTH = 20;

        public TracksViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            mImageView = itemView.findViewById(R.id.image);
            mTxtSong = itemView.findViewById(R.id.text_upper);
            mTxtSinger = itemView.findViewById(R.id.text_lower);
        }

        private void setSongsRecent(int position) {
            if (mTracks.getTrack().get(position) == null || mTracks.getTrack() == null) {
                return;
            }
            String songs = mTracks.getTrack().get(position).getName();
            if (songs.length() >= MAX_LENGTH) {
                String subSongs = songs.substring(MIN_LENGTH, MAX_LENGTH) + "...";
                mTxtSong.setText(subSongs);
            } else {
                mTxtSong.setText(songs);
            }
        }

        private void setArtistsRecent(int position) {
            if (mTracks.getTrack().get(position).getArtist() == null
                    || mTracks.getTrack() == null) {
                return;
            }
            mTxtSinger.setText(mTracks.getTrack().get(position).getArtist().getText());
        }

        private void setImagesRecent(int position) {
            if (mTracks.getTrack().get(position).getImage() == null
                    || mTracks.getTrack().get(position) == null
                    || mTracks.getTrack().get(position).getImage().size() < MEDIUM_IMAGE + 1) {
                return;
            }
            String urlImage =
                    mTracks.getTrack().get(position).getImage().get(MEDIUM_IMAGE).getText();
            if (urlImage != null && !urlImage.isEmpty()) {
                Glide.with(mContext).load(urlImage).into(mImageView);
            } else {
                mImageView.setImageResource(R.drawable.img_demo_100x100);
            }
        }
    }
}
