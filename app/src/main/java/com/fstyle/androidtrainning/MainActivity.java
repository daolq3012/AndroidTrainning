package com.fstyle.androidtrainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.data.service.response.GetListMoviesResponse;
import com.fstyle.androidtrainning.utils.Constant;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoviesApi api = MainApplication.getMoviesApi();
        api.getListMovies("upcoming",Constant.API_KEY, Constant.LANGUAGE, 1)
                .enqueue(new Callback<GetListMoviesResponse>() {
                    @Override
                    public void onResponse(Call<GetListMoviesResponse> call,
                            Response<GetListMoviesResponse> response) {
                        List<Movie> movieList = response.body().getResults();
                    }

                    @Override
                    public void onFailure(Call<GetListMoviesResponse> call, Throwable t) {

                    }
                });
    }
}
