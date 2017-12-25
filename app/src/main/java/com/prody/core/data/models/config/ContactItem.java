package com.prody.core.data.models.config;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Paul
 * @since 2017.12.25
 */

public class ContactItem implements Serializable {
    @SerializedName("type")
    private ContactItemType mType;
    @SerializedName("text")
    private String mText;
    @SerializedName("latitude")
    private double mLatitude;
    @SerializedName("longitude")
    private double mLongitude;

    public ContactItemType getType() {
        return mType;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public String getText() {
        return mText;
    }

    @Override
    public String toString() {
        return "ContactItem{" +
                "mType=" + mType +
                ", mText='" + mText + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                '}';
    }
}
