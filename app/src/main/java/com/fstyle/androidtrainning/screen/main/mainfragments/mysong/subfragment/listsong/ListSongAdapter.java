package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/17/17.
 */

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.RecyclerViewHolder> {

    private List<Track> mTrack = new ArrayList<>();
    private Context mContext;

    public ListSongAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void updateData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTrack.addAll(tracks);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_song, parent, false);
        return new RecyclerViewHolder(view);
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

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_top_track);
            mTxtNameSong = itemView.findViewById(R.id.text_name_song);
            mTxtNameSinger = itemView.findViewById(R.id.text_name_singer);
        }

        public void bind(int position) {
            String name = mTrack.get(position).getName();
            String artist = mTrack.get(position).getNameArtist();
            mTxtNameSong.setText(name);
            mTxtNameSinger.setText(artist);
        }
    }
}
