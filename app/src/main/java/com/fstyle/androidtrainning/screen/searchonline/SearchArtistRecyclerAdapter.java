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
                .inflate(R.layout.list_item_song, parent, false);
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
        private ImageView mImageView;
        private TextView mTxtArtist, mTxtCount;
        private static final int MEDIUM_IMAGE = 1;
        private static final int MAX_LENGTH = 25;
        private static final int MIN_LENGTH = 0;
        private static final String MORE = "...";

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            mTxtArtist = itemView.findViewById(R.id.text_upper);
            mTxtCount = itemView.findViewById(R.id.text_lower);
        }

        public void bind(int position) {
            setImage(position);
            setNameArtist(position);
            setCount(position);
        }

        private void setImage(int position) {
            String urlImage = mArtists.get(position).getImage().get(MEDIUM_IMAGE).getText();
            if (!urlImage.isEmpty()) {
                Glide.with(mContext).load(urlImage).into(mImageView);
            } else {
                mImageView.setImageResource(R.drawable.ic_unknown_album);
            }
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

        private void setCount(int position) {
            String count = mArtists.get(position).getListeners();
            if (count.length() >= MAX_LENGTH) {
                String subCount =
                        mContext.getResources().getString(R.string.listener) + count.substring(
                                MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtCount.setText(subCount);
            } else {
                String listener = mContext.getResources().getString(R.string.listener) + count;
                mTxtCount.setText(listener);
            }
        }
    }
}
