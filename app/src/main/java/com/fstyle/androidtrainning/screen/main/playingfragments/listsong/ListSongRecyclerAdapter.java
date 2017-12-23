package com.fstyle.androidtrainning.screen.main.playingfragments.listsong;

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
 * Created by ChuongPC on 24/12/2017.
 */

public class ListSongRecyclerAdapter
        extends RecyclerView.Adapter<ListSongRecyclerAdapter.RecyclerViewHolder> {

    private Context mContext;
    private OnItemSubListSongClickListener mOnItemSubListSongClickListener;
    private List<Track> mTracks = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 15;

    public ListSongRecyclerAdapter(Context context,
            OnItemSubListSongClickListener onItemSubListSongClickListener) {
        mContext = context;
        mOnItemSubListSongClickListener = onItemSubListSongClickListener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void updateData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTracks.clear();
        mTracks.addAll(tracks);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_song, parent, false);
        return new RecyclerViewHolder(view, mOnItemSubListSongClickListener);
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
        private int mPosition = 0;

        public RecyclerViewHolder(View itemView,
                OnItemSubListSongClickListener onItemSubListSongClickListener) {
            super(itemView);
            mTxtNameSong = itemView.findViewById(R.id.text_upper);
            mTxtNameSinger = itemView.findViewById(R.id.text_lower);
            mOnItemSubListSongClickListener = onItemSubListSongClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemSubListSongClickListener.onItemSubListSongClicked(mPosition);
                }
            });
        }

        public void bind(int position) {
            mPosition = position;
            setName(position);
            setArtist(position);
        }

        private void setName(int position) {
            if (mTracks.get(position) == null || mTracks.get(position).getName() == null) {
                return;
            }
            String name = mTracks.get(position).getName();
            if (name.length() >= MAX_LENGTH) {
                String subName = name.substring(MIN_LENGTH, MAX_LENGTH) + "...";
                mTxtNameSong.setText(subName);
            } else {
                mTxtNameSong.setText(name);
            }
        }

        private void setArtist(int position) {
            if (mTracks.get(position) == null || mTracks.get(position).getNameArtist() == null) {
                return;
            }
            String artist = mTracks.get(position).getNameArtist();
            if (artist.length() >= MAX_LENGTH) {
                String subArtist = artist.substring(MIN_LENGTH, MAX_LENGTH) + "...";
                mTxtNameSinger.setText(subArtist);
            } else {
                mTxtNameSinger.setText(artist);
            }
        }
    }
}
