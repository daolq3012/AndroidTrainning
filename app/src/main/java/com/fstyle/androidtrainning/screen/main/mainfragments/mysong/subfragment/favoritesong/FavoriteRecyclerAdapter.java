package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/18/17.
 */

public class FavoriteRecyclerAdapter
        extends RecyclerView.Adapter<FavoriteRecyclerAdapter.RecyclerViewHolder> {
    private List<TrackEntity> mList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;
    private OnItemFavoriteClickListener mOnItemFavoriteClickListener;

    public FavoriteRecyclerAdapter(Context context,
            OnItemFavoriteClickListener onItemFavoriteClickListener) {
        mContext = context;
        mOnItemFavoriteClickListener = onItemFavoriteClickListener;
        mInflater = LayoutInflater.from(context);
    }

    public void updateData(List<TrackEntity> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_song, parent, false);
        return new RecyclerViewHolder(view, mOnItemFavoriteClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTxtName, mTxtArtist;
        private int position = 0;

        public RecyclerViewHolder(View itemView,
                OnItemFavoriteClickListener onItemFavoriteClickListener) {
            super(itemView);
            initViews(itemView, onItemFavoriteClickListener);
        }

        private void initViews(View view, OnItemFavoriteClickListener onItemFavoriteClickListener) {
            mImageView = view.findViewById(R.id.image);
            mTxtName = view.findViewById(R.id.text_upper);
            mTxtArtist = view.findViewById(R.id.text_lower);
            mOnItemFavoriteClickListener = onItemFavoriteClickListener;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemFavoriteClickListener.onItemFavClicked(position, mList);
                }
            });
        }

        public void bind(int position) {
            this.position = position;
            mTxtName.setText(mList.get(position).getNameSong());
            mTxtArtist.setText(mList.get(position).getNameArtist());
        }
    }
}
