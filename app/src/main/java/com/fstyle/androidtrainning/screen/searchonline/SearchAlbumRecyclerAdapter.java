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
import com.fstyle.androidtrainning.model.SearchAlbum;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/15/17.
 */

public class SearchAlbumRecyclerAdapter
        extends RecyclerView.Adapter<SearchAlbumRecyclerAdapter.RecyclerViewHolder> {

    private List<SearchAlbum> mAlbums = new ArrayList<>();
    private Context mContext;

    public SearchAlbumRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<SearchAlbum> albums) {
        if (albums == null) {
            return;
        }
        mAlbums = albums;
        notifyDataSetChanged();
    }

    public void clearData() {
        if (mAlbums == null) {
            return;
        }
        mAlbums.clear();
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
        return mAlbums.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTxtName, mTxtArtist;
        private static final int MEDIUM_IMAGE = 1;
        private static final int MAX_LENGTH = 25;
        private static final int MIN_LENGTH = 0;
        private static final String MORE = "...";

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            mTxtName = itemView.findViewById(R.id.text_upper);
            mTxtArtist = itemView.findViewById(R.id.text_lower);
        }

        public void bind(int position) {
            setImage(position);
            setName(position);
            setArtist(position);
        }

        private void setImage(int position) {
            String urlImage = mAlbums.get(position).getImage().get(MEDIUM_IMAGE).getText();
            if (!urlImage.isEmpty()) {
                Glide.with(mContext).load(urlImage).into(mImageView);
            } else {
                mImageView.setImageResource(R.drawable.img_demo_50x50);
            }
        }

        private void setName(int position) {
            String name = mAlbums.get(position).getName();
            if (name.length() >= MAX_LENGTH) {
                String subName = name.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtName.setText(subName);
            } else {
                mTxtName.setText(name);
            }
        }

        private void setArtist(int position) {
            String artist = mAlbums.get(position).getArtist();
            if (artist.length() >= MAX_LENGTH) {
                String subArtist = artist.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtArtist.setText(subArtist);
            } else {
                mTxtArtist.setText(artist);
            }
        }
    }
}
