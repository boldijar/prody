package com.prody.core.data.models.config;

import com.google.gson.annotations.SerializedName;

/**
 * TODO: Class description
 *
 * @author Paul
 * @since 2017.12.17
 */

public class MainMenuItem {

    @SerializedName("title")
    private String mTitle;

    public String getTitle() {
        return mTitle;
    }
}
