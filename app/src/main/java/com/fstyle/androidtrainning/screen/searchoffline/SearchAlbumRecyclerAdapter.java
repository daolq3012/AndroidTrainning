package com.fstyle.androidtrainning.screen.searchoffline;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.model.Album;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 12/15/17.
 */

public class SearchAlbumRecyclerAdapter
        extends RecyclerView.Adapter<SearchAlbumRecyclerAdapter.RecyclerViewHolder> {

    private List<Album> mAlbums = new ArrayList<>();
    private Context mContext;
    private OnItemAlbumClickListener mOnItemAlbumClickListener;

    public SearchAlbumRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Album> albums) {
        if (albums == null) {
            return;
        }
        mAlbums = albums;
        notifyDataSetChanged();
    }

    public void setOnItemAlbumClickListener(OnItemAlbumClickListener onItemAlbumClickListener) {
        mOnItemAlbumClickListener = onItemAlbumClickListener;
    }

    public void clearData() {
        if (mAlbums == null) {
            return;
        }
        mAlbums.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_song, parent, false);
        return new RecyclerViewHolder(view, mOnItemAlbumClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTxtName, mTxtArtist;
        private static final int MAX_LENGTH = 25;
        private static final int MIN_LENGTH = 0;
        private static final String MORE = "...";
        private int position = 0;

        public RecyclerViewHolder(View itemView,
                OnItemAlbumClickListener onItemAlbumClickListener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            mTxtName = itemView.findViewById(R.id.text_upper);
            mTxtArtist = itemView.findViewById(R.id.text_lower);
            mOnItemAlbumClickListener = onItemAlbumClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemAlbumClickListener.onItemAlbumClicked(mAlbums.get(position).getName());
                }
            });
        }

        public void bind(int position) {
            this.position = position;
            setImage(position);
            setName(position);
            setArtist(position);
        }

        private void setImage(int position) {
            Bitmap bitmap = mAlbums.get(position).getBmAlbum();
            if (bitmap != null) {
                mImageView.setImageBitmap(bitmap);
            } else {
                mImageView.setImageResource(R.drawable.ic_unknown_album);
            }
        }

        private void setName(int position) {
            String name = mAlbums.get(position).getName();
            if (name.length() >= MAX_LENGTH) {
                String subName = name.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtName.setText(subName);
            } else {
                mTxtName.setText(name);
            }
        }

        private void setArtist(int position) {
            String artist = mAlbums.get(position).getNameArtist();
            if (artist.length() >= MAX_LENGTH) {
                String subArtist = artist.substring(MIN_LENGTH, MAX_LENGTH) + MORE;
                mTxtArtist.setText(subArtist);
            } else {
                mTxtArtist.setText(artist);
            }
        }
    }
}
