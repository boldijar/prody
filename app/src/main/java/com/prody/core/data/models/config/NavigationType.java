package com.prody.core.data.models.config;

import com.google.gson.annotations.SerializedName;

/**
 * @author Paul
 * @since 2017.12.24
 */

public enum NavigationType {
    @SerializedName("tabs")
    TABS,
    @SerializedName("drawer")
    DRAWER,
    NONE
}
