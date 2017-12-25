package com.prody.core.data.models.config;

import android.support.annotation.DrawableRes;

import com.google.gson.annotations.SerializedName;
import com.prody.R;

/**
 * @author Paul
 * @since 2017.12.25
 */

public enum ContactItemType {
    @SerializedName("email")
    EMAIL(R.drawable.ic_email, true),
    @SerializedName("phone")
    PHONE(R.drawable.ic_phone, true),
    @SerializedName("location")
    LOCATION(R.drawable.ic_location, true),
    @SerializedName("info")
    INFO(R.drawable.ic_info, false);

    private @DrawableRes
    final int mDrawable;
    private final boolean mClickable;

    ContactItemType(int drawable, boolean clickable) {
        mDrawable = drawable;
        mClickable = clickable;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public boolean isClickable() {
        return mClickable;
    }
}
