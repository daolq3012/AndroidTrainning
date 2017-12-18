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

    private List<Artist> mArtist = new ArrayList<>();
    private Context mContext;

    public ArtistAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Artist> artists) {
        if (artists == null) {
            return;
        }
        mArtist.addAll(artists);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_artist_offline, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mArtist.size();
    }

    public final class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mTxtArtist;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mTxtArtist = itemView.findViewById(R.id.text_name);
        }

        public void bind(int position) {
            String nameArtist = mArtist.get(position).getName();
            mTxtArtist.setText(nameArtist);
        }
    }
}
