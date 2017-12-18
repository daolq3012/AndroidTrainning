package com.fstyle.androidtrainning.screen.tabsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.screen.OnRecyclerViewItemListener;
import com.fstyle.androidtrainning.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynh on 16/12/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    private List<Movie> mMovieList = new ArrayList<>();
    private OnRecyclerViewItemListener mOnRecyclerViewItemListener;
    private Context mContext;
    private LayoutInflater mInflater;

    public SearchAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.movie_item, parent, false);
        return new SearchHolder(itemView, mOnRecyclerViewItemListener);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.bind(mMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setOnRecyclerViewItemListener(
            OnRecyclerViewItemListener onRecyclerViewItemListener) {
        mOnRecyclerViewItemListener = onRecyclerViewItemListener;
    }

    public void updateData(List<Movie> movieList) {
        if (movieList == null) {
            return;
        }
        mMovieList.clear();
        mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }

    static class SearchHolder extends RecyclerView.ViewHolder {

        private Movie mMovie;
        private ImageView mSearchPoster;
        private TextView mMovieName;
        private OnRecyclerViewItemListener mOnRecyclerViewItemListener;

        SearchHolder(View itemView, OnRecyclerViewItemListener onRecyclerViewItemListener) {
            super(itemView);
            mSearchPoster = itemView.findViewById(R.id.image_poster_item);
            mMovieName = itemView.findViewById(R.id.text_name_item);
            mOnRecyclerViewItemListener = onRecyclerViewItemListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnRecyclerViewItemListener.onItemClick(mMovie);
                }
            });
        }

        public void bind(Movie movie) {
            mMovie = movie;
            String urlPoster = StringUtils.convertPosterPathToUrlPoster(movie.getPosterPath());
            String titleMovie = StringUtils.convertLongTitleToShortTitle(movie.getTitle(),
                    movie.getReleaseDate());
            Glide.with(itemView.getContext()).load(urlPoster).into(mSearchPoster);
            mMovieName.setText(titleMovie);
        }
    }
}
