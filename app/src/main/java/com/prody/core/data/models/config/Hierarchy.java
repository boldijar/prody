package com.prody.core.data.models.config;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Paul
 * @since 2017.12.17
 */

public class Hierarchy {

    @SerializedName("title")
    public String mTitle;
    @SerializedName("navigation_type")
    private NavigationType mNavigationType;
    @SerializedName("has_toolbar")
    public boolean mHasToolbar;
    @SerializedName("image")
    public String mImage;
    @SerializedName("image_alignment")
    private ImageAlignment mImageAlignment;
    @SerializedName("items")
    private List<MenuItem> mMenuItems;

    public NavigationType getNavigationType() {
        if (mNavigationType == null) {
            return NavigationType.NONE;
        }
        return mNavigationType;
    }

    public ImageAlignment getImageAlignment() {
        if (mImageAlignment == null) {
            return ImageAlignment.LEFT;
        }
        return mImageAlignment;
    }

    public List<MenuItem> getMenuItems() {
        return mMenuItems;
    }

    public enum ImageAlignment {
        @SerializedName("left")
        LEFT,
        @SerializedName("center")
        CENTER,
        @SerializedName("right")
        RIGHT;
    }

    public enum NavigationType {
        @SerializedName("tabs")
        TABS,
        @SerializedName("drawer")
        DRAWER,
        NONE
    }
}
