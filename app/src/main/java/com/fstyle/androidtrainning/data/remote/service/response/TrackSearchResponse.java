package com.fstyle.androidtrainning.data.remote.service.response;

import com.fstyle.androidtrainning.model.TrackResults;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 12/15/17.
 */

public class TrackSearchResponse {

    @SerializedName("results")
    @Expose
    private TrackResults results;

    public TrackResults getResults() {
        return results;
    }

    public void setResults(TrackResults results) {
        this.results = results;
    }

}
