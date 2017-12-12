package com.fstyle.androidtrainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.data.service.response.GetListUpcomingResponse;
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
        api.getListUpcomingMovies(Constant.API_KEY,Constant.LANGUAGE,1).enqueue(new Callback<GetListUpcomingResponse>() {
            @Override
            public void onResponse(Call<GetListUpcomingResponse> call,
                    Response<GetListUpcomingResponse> response) {
                List<Movie> movieList = response.body().getResults();
            }

            @Override
            public void onFailure(Call<GetListUpcomingResponse> call, Throwable t) {

            }
        });
    }
}
