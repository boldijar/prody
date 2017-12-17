package com.prody.core.data.models.config;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Paul
 * @since 2017.12.17
 */

public class Home {
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
    @SerializedName("main_menu_items")
    private List<MainMenuItem> mMainMenuItems;

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

    public List<MainMenuItem> getMainMenuItems() {
        return mMainMenuItems;
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
