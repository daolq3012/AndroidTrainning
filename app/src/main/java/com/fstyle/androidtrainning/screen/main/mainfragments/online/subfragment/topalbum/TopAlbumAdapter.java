package com.fstyle.androidtrainning.screen.main.mainfragments.online.subfragment.topalbum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Album;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/14/17.
 */

public class TopAlbumAdapter extends RecyclerView.Adapter<TopAlbumAdapter.RecyclerViewHolder> {

    private List<Album> mAlbums = new ArrayList<>();
    private Context mContext;
    private static final int LARGE_IMAGE = 2;
    private static final int LENGTH_NAME = 10;
    private static final int BEGIN_NAME = 0;
    private static final String MORE = "...";

    public TopAlbumAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Album> albums) {
        if (albums == null) {
            return;
        }
        mAlbums.addAll(albums);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_top_album, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.fillData(position);
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTxtAlbum, mTxtSinger;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_album);
            mTxtAlbum = itemView.findViewById(R.id.text_name_album);
            mTxtSinger = itemView.findViewById(R.id.text_name_singer);
        }

        public void fillData(int position) {
            setImage(position);
            setAlbum(position);
            setSinger(position);
        }

        private void setSinger(int position) {
            if (mAlbums.get(position).getArtist() == null || mAlbums.get(position) == null) {
                return;
            }
            String singer = mAlbums.get(position).getArtist().getName();
            if (singer.length() > LENGTH_NAME) {
                String supSinger = singer.substring(BEGIN_NAME, LENGTH_NAME) + MORE;
                mTxtAlbum.setText(supSinger);
            } else {
                mTxtSinger.setText(singer);
            }
        }

        private void setAlbum(int position) {
            if (mAlbums.get(position) == null || mAlbums.get(position).getName() == null) {
                return;
            }
            String album = mAlbums.get(position).getName();
            if (album.length() > LENGTH_NAME) {
                String supAlbum = album.substring(BEGIN_NAME, LENGTH_NAME) + MORE;
                mTxtAlbum.setText(supAlbum);
            } else {
                mTxtAlbum.setText(album);
            }
        }

        private void setImage(int position) {
            if (mAlbums.get(position).getImage() == null
                    || mAlbums.get(position) == null
                    || mAlbums.get(position).getImage().size() < LARGE_IMAGE + 1) {
                return;
            }
            String imageAlbum = mAlbums.get(position).getImage().get(LARGE_IMAGE).getText();
            if (!imageAlbum.isEmpty()) {
                Glide.with(mContext).load(imageAlbum).into(mImageView);
            } else {
                mImageView.setImageResource(R.drawable.ic_unknown_album);
            }
        }
    }
}
