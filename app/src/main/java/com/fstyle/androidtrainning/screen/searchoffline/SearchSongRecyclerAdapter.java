package com.fstyle.androidtrainning.screen.searchoffline;

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

public class SearchSongRecyclerAdapter
        extends RecyclerView.Adapter<SearchSongRecyclerAdapter.RecyclerViewHolder> {

    private List<Track> mTracks = new ArrayList<>();
    private List<Track> mTracksTemp = new ArrayList<>();
    private Context mContext;
    private OnItemSongClickListener mOnItemSongClickListener;

    public SearchSongRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTracks.clear();
        mTracks.addAll(tracks);
        notifyDataSetChanged();
    }

    public void setTracksTemp(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTracksTemp.addAll(tracks);
    }

    public void setOnItemSongClickListener(OnItemSongClickListener onItemSongClickListener) {
        mOnItemSongClickListener = onItemSongClickListener;
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
        return new RecyclerViewHolder(view, mOnItemSongClickListener);
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
        private int position = 0;
        private String nameSong, nameArtist;

        public RecyclerViewHolder(View itemView, OnItemSongClickListener onItemSongClickListener) {
            super(itemView);
            mTxtNameSong = itemView.findViewById(R.id.text_upper);
            mTxtNameSinger = itemView.findViewById(R.id.text_lower);
            mOnItemSongClickListener = onItemSongClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemSongClickListener.onItemSongClicked(nameSong, nameArtist, mTracksTemp);
                }
            });
        }

        public void bind(int position) {
            this.position = position;
            setNameArtist(position);
            setNameSong(position);
        }

        private void setNameArtist(int position) {
            nameArtist = mTracks.get(position).getNameArtist();
            if (nameArtist.length() >= MAX_LENGTH) {
                String subNameArtist = nameArtist.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtNameSinger.setText(subNameArtist);
            } else {
                mTxtNameSinger.setText(nameArtist);
            }
        }

        private void setNameSong(int position) {
            nameSong = mTracks.get(position).getName();
            if (nameSong.length() >= MAX_LENGTH) {
                String subNameSong = nameSong.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtNameSong.setText(subNameSong);
            } else {
                mTxtNameSong.setText(nameSong);
            }
        }
    }
}
