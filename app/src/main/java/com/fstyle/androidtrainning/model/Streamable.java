package com.fstyle.androidtrainning.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 12/12/17.
 */

public class Streamable implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.fulltrack);
    }

    public Streamable() {
    }

    protected Streamable(Parcel in) {
        this.text = in.readString();
        this.fulltrack = in.readString();
    }

    public static final Parcelable.Creator<Streamable> CREATOR =
            new Parcelable.Creator<Streamable>() {
                @Override
                public Streamable createFromParcel(Parcel source) {
                    return new Streamable(source);
                }

                @Override
                public Streamable[] newArray(int size) {
                    return new Streamable[size];
                }
            };
}
