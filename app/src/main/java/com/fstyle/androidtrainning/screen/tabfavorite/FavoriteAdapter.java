package com.fstyle.androidtrainning.screen.tabfavorite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.local.entity.MovieEntity;
import com.fstyle.androidtrainning.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynh on 19/12/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {
    private List<MovieEntity> mMovieEntityList = new ArrayList<>();
    private OnFavoriteItemListener mOnFavoriteItemListener;
    private Context mContext;
    private LayoutInflater mInflater;

    public FavoriteAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.movie_favorite_item_layout, parent, false);
        return new FavoriteHolder(itemView, mOnFavoriteItemListener);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.FavoriteHolder holder, int position) {
        holder.bind(mMovieEntityList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieEntityList.size();
    }

    public void setOnFavoriteItemListener(
            OnFavoriteItemListener onFavoriteItemListener) {
        mOnFavoriteItemListener = onFavoriteItemListener;
    }

    public void updateData(List<MovieEntity> movieEntityList) {
        if (movieEntityList == null) {
            return;
        }
        mMovieEntityList.clear();
        mMovieEntityList.addAll(movieEntityList);
        notifyDataSetChanged();
    }

    static class FavoriteHolder extends RecyclerView.ViewHolder {
        private MovieEntity mMovieEntity;
        private ImageView mFavoritePoster;
        private TextView mFavoriteMovieName;
        private OnFavoriteItemListener mOnFavoriteItemListener;

        FavoriteHolder(View itemView,
                OnFavoriteItemListener onFavoriteItemListener) {
            super(itemView);
            mFavoritePoster = itemView.findViewById(R.id.image_poster_item);
            mFavoriteMovieName = itemView.findViewById(R.id.text_name_item);
            mOnFavoriteItemListener = onFavoriteItemListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnFavoriteItemListener.onClickItem(mMovieEntity);
                }
            });
        }

        public void bind(MovieEntity movieEntity) {
            mMovieEntity = movieEntity;
            String urlPoster =
                    StringUtils.convertPosterPathToUrlPoster(movieEntity.getPosterPath());
            String titleMovie = StringUtils.convertLongTitleToShortTitle(movieEntity.getTitle(),
                    movieEntity.getReleaseDate());
            Glide.with(itemView.getContext()).load(urlPoster).into(mFavoritePoster);
            mFavoriteMovieName.setText(titleMovie);
        }
    }
}
