package com.prody.core.data.models.config;

import com.google.gson.annotations.SerializedName;

public class Core {
    @SerializedName("endpoint")
    public String mEndpoint;
    @SerializedName("version")
    public int mVersion;

    public String getEndpoint() {
        return mEndpoint;
    }

    @Override
    public String toString() {
        return "Core{" +
                "mEndpoint='" + mEndpoint + '\'' +
                ", mVersion=" + mVersion +
                '}';
    }
}
