package com.prody.core.data.models.config;

import com.google.gson.annotations.SerializedName;

/**
 * @author Paul
 * @since 2017.12.17
 */

public class Config {
    @SerializedName("core")
    public Core mCore;
    @SerializedName("style")
    public Style mStyle;
    @SerializedName("hierarchy")
    public Hierarchy mHierarchy;

    public static Config EMPTY = new Config();

    @Override
    public String toString() {
        return "Config{" +
                "mCore=" + mCore +
                ", mStyle=" + mStyle +
                '}';
    }
}
