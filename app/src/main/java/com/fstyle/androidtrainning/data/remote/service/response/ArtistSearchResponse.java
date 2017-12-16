package com.fstyle.androidtrainning.data.remote.service.response;

import com.fstyle.androidtrainning.model.ArtistResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 12/15/17.
 */

public class ArtistSearchResponse {
    @SerializedName("results")
    @Expose
    private ArtistResult results;

    public ArtistResult getResults() {
        return results;
    }

    public void setResults(ArtistResult results) {
        this.results = results;
    }
}
