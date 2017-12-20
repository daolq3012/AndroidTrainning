package com.fstyle.androidtrainning.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 12/12/17.
 */

public class Attr implements Parcelable {

    @SerializedName("rank")
    @Expose
    private String rank;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.rank);
    }

    public Attr() {
    }

    protected Attr(Parcel in) {
        this.rank = in.readString();
    }

    public static final Parcelable.Creator<Attr> CREATOR = new Parcelable.Creator<Attr>() {
        @Override
        public Attr createFromParcel(Parcel source) {
            return new Attr(source);
        }

        @Override
        public Attr[] newArray(int size) {
            return new Attr[size];
        }
    };
}
