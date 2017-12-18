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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.RecyclerViewHoler> {

    private List<Album> mAlbum = new ArrayList<>();
    private Context mContext;

    public AlbumAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Album> albums) {
        if (albums == null) {
            return;
        }
        mAlbum.addAll(albums);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_top_album, parent, false);
        return new RecyclerViewHoler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHoler holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mAlbum.size();
    }

    public final class RecyclerViewHoler extends RecyclerView.ViewHolder {

        private ImageView mImageAlbum;
        private TextView mTxtAlbum, mTxtArtist;

        private RecyclerViewHoler(View itemView) {
            super(itemView);
            mImageAlbum = itemView.findViewById(R.id.image_album);
            mTxtAlbum = itemView.findViewById(R.id.text_name_album);
            mTxtArtist = itemView.findViewById(R.id.text_name_singer);
        }

        private void bind(int position) {
            Bitmap image = mAlbum.get(position).getBmAlbum();
            String nameAlbum = mAlbum.get(position).getName();
            String nameArtist = mAlbum.get(position).getNameArtist();
            mImageAlbum.setImageBitmap(image);
            mTxtAlbum.setText(nameAlbum);
            mTxtArtist.setText(nameArtist);
        }
    }
}
