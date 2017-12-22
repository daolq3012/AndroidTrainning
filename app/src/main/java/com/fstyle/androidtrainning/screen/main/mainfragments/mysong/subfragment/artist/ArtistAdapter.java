package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.artist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Artist;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/17/17.
 */

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.RecyclerViewHolder> {

    private List<Artist> mArtists = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public ArtistAdapter(Context context) {
        mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void updateData(List<Artist> artists) {
        if (artists == null) {
            return;
        }
        mArtists.addAll(artists);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_artist_offline, parent, false);
        return new RecyclerViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mArtists.size();
    }

    public final class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mTxtArtist;
        private OnItemClickListener mOnItemClickListener;
        private int position = 0;

        public RecyclerViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mTxtArtist = itemView.findViewById(R.id.text_name);
            mOnItemClickListener = onItemClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClicked(mArtists.get(position).getName());
                }
            });
        }

        public void bind(int position) {
            this.position = position;
            String nameArtist = mArtists.get(position).getName();
            mTxtArtist.setText(nameArtist);
        }
    }
}
