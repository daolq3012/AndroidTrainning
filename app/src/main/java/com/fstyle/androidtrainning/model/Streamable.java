package com.fstyle.androidtrainning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 12/12/17.
 */

public class Streamable {

    @SerializedName("#text")
    @Expose
    private String text;
    @SerializedName("fulltrack")
    @Expose
    private String fulltrack;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFulltrack() {
        return fulltrack;
    }

    public void setFulltrack(String fulltrack) {
        this.fulltrack = fulltrack;
    }
}
