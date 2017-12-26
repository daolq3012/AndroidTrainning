package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.album;

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
 * Created by Administrator on 12/17/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.RecyclerViewHolder> {

    private List<Album> mAlbums = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 15;

    public AlbumAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Album> albums) {
        if (albums == null) {
            return;
        }
        mAlbums.addAll(albums);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_top_album, parent, false);
        return new RecyclerViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public final class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageAlbum;
        private TextView mTxtAlbum, mTxtArtist;
        private OnItemClickListener mOnItemClickListener;
        private int position = 0;

        private RecyclerViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mImageAlbum = itemView.findViewById(R.id.image_album);
            mTxtAlbum = itemView.findViewById(R.id.text_name_album);
            mTxtArtist = itemView.findViewById(R.id.text_name_singer);
            mOnItemClickListener = onItemClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAlbums.get(position).getName() == null) {
                        return;
                    }
                    mOnItemClickListener.onItemClicked(mAlbums.get(position).getName());
                }
            });
        }

        private void bind(int position) {
            if (mAlbums.get(position).getName() == null
                    || mAlbums.get(position).getNameArtist() == null
                    || mAlbums.get(position).getBmAlbum() == null) {
                return;
            }
            this.position = position;
            Bitmap image = mAlbums.get(position).getBmAlbum();
            String nameArtist = mAlbums.get(position).getNameArtist();
            mImageAlbum.setImageBitmap(image);
            mTxtArtist.setText(nameArtist);
            setNameAlbum();
        }

        private void setNameAlbum() {
            String nameAlbum = mAlbums.get(position).getName();
            if (nameAlbum.length() >= MAX_LENGTH) {
                String subNameAlbum = nameAlbum.substring(MIN_LENGTH, MAX_LENGTH) + "...";
                mTxtAlbum.setText(subNameAlbum);
            } else {
                mTxtAlbum.setText(nameAlbum);
            }
        }
    }
}
