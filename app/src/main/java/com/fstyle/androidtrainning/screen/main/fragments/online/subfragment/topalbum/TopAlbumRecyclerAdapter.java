package com.fstyle.androidtrainning.screen.main.fragments.online.subfragment.topalbum;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fstyle.androidtrainning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/13/17.
 */

public class TopAlbumRecyclerAdapter extends RecyclerView.Adapter<TopAlbumRecyclerAdapter.RecyclerViewHolder> {

    private List<String> test = new ArrayList<>();

    public TopAlbumRecyclerAdapter(List<String> test) {
        this.test = test;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_album, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return test.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTxtNameAlbum, mTxtCountSong;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_top_album);
            mTxtCountSong = itemView.findViewById(R.id.text_count_song);
            mTxtNameAlbum = itemView.findViewById(R.id.text_name_album);
        }

        public void bind(int position) {
            mTxtNameAlbum.setText(test.get(position));
        }
    }
}
