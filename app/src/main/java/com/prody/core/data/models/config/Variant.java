package com.prody.core.data.models.config;

import com.google.gson.annotations.SerializedName;
import com.gym.app.R;

/**
 * @author Paul
 * @since 2017.12.24
 */

public enum Variant {
    @SerializedName("card_image")
    CARD_IMAGE(R.layout.item_card_image),
    @SerializedName("image")
    IMAGE(R.layout.item_image),
    @SerializedName("card_basic_details")
    CARD_BASIC_DETAILS(R.layout.item_card_basic_details),
    @SerializedName("basic_details")
    BASIC_DETAILS(R.layout.item_basic_details),
    @SerializedName("image_wrap")
    IMAGE_WRAP(R.layout.item_image_wrap),

    NONE(0);

    private final int mLayoutRes;

    Variant(int layoutRes) {
        mLayoutRes = layoutRes;
    }

    public int getLayoutRes() {
        return mLayoutRes;
    }
}
