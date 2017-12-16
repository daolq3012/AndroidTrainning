package com.fstyle.androidtrainning.data.remote.service.response;

import com.fstyle.androidtrainning.model.AlbumResults;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 12/15/17.
 */

public class AlbumSearchResponse {
    @SerializedName("results")
    @Expose
    private AlbumResults results;

    public AlbumResults getResults() {
        return results;
    }

    public void setResults(AlbumResults results) {
        this.results = results;
    }

}
