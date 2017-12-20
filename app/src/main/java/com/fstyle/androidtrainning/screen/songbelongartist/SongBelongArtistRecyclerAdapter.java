package com.fstyle.androidtrainning.screen.songbelongartist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Track;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/15/17.
 */

public class SongBelongArtistRecyclerAdapter
        extends RecyclerView.Adapter<SongBelongArtistRecyclerAdapter.RecyclerViewHolder> {

    private List<Track> mTracks = new ArrayList<>();
    private Context mContext;

    public SongBelongArtistRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTracks = tracks;
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
        private TextView mTxtNameSong, mTxtNameSinger;
        private static final int MAX_LENGTH = 25;
        private static final int MIN_LENGTH = 0;
        private static final String MORE = "...";

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mTxtNameSong = itemView.findViewById(R.id.text_upper);
            mTxtNameSinger = itemView.findViewById(R.id.text_lower);
        }

        public void bind(int position) {
            setNameArtist(position);
            setNameSong(position);
        }

        private void setNameArtist(int position) {
            String nameArtist = mTracks.get(position).getNameArtist();
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
