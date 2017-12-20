package com.fstyle.androidtrainning.screen.searchoffline;

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
 * Created by Administrator on 12/15/17.
 */

public class SearchArtistRecyclerAdapter
        extends RecyclerView.Adapter<SearchArtistRecyclerAdapter.RecyclerViewHolder> {

    private List<Artist> mArtists = new ArrayList<>();
    private Context mContext;

    public SearchArtistRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Artist> artists) {
        if (artists == null) {
            return;
        }
        mArtists = artists;
        notifyDataSetChanged();
    }

    public void clearData() {
        if (mArtists == null) {
            return;
        }
        mArtists.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_artist_offline, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mArtists.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtArtist;
        private static final int MAX_LENGTH = 25;
        private static final int MIN_LENGTH = 0;
        private static final String MORE = "...";

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mTxtArtist = itemView.findViewById(R.id.text_name);
        }

        public void bind(int position) {
            setNameArtist(position);
        }

        private void setNameArtist(int position) {
            String nameArtist = mArtists.get(position).getName();
            if (nameArtist.length() >= MAX_LENGTH) {
                String subNameArtist = nameArtist.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtArtist.setText(subNameArtist);
            } else {
                mTxtArtist.setText(nameArtist);
            }
        }
    }
}
