package com.prody.core.data.models.config;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

/**
 * @author Paul
 * @since 2017.12.25
 */

public enum ImageScaleType {

    @SerializedName("center_crop")
    CENTER_CROP(ImageView.ScaleType.CENTER_CROP),

    @SerializedName("fit_center")
    FIT_CENTER(ImageView.ScaleType.FIT_CENTER);

    private final ImageView.ScaleType mScaleType;

    ImageScaleType(ImageView.ScaleType centerCrop) {
        mScaleType = centerCrop;
    }

    public ImageView.ScaleType getScaleType() {
        return mScaleType;
    }
}
