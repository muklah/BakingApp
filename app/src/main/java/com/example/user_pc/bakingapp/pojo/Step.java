package com.example.user_pc.bakingapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

    private Integer id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    @Override
    public void writeToParcel(Parcel out, int flags) {
        if (id == null) {
            out.writeByte((byte) (0x00));
        } else {
            out.writeByte((byte) (0x01));
            out.writeInt(id);
        }
        out.writeString(shortDescription);
        out.writeString(description);
        out.writeString(videoURL);
        out.writeString(thumbnailURL);
    }

    protected Step(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int i) {
            return new Step[i];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

}