package com.prody.core.data.models.config;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Paul
 * @since 2017.12.17
 */

public class MenuItem implements Serializable {

    @SerializedName("title")
    private String mTitle;
    @SerializedName("type")
    private ModuleType mModuleType;
    @SerializedName("variant")
    private Variant mVariant;
    @SerializedName("category")
    private String mCategory;
    @SerializedName("span")
    private int mSpan;
    @SerializedName("shuffle")
    private boolean mShuffle;
    @SerializedName("using_padding")
    private Boolean mUsingPadding;
    @SerializedName("staggered")
    private Boolean mStaggered;
    @SerializedName("scale_type")
    private ImageScaleType mImageScaleType;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("contact_items")
    private ArrayList<ContactItem> mContactItems;

    public String getIcon() {
        return mIcon;
    }

    public ImageScaleType getImageScaleType() {
        if (mImageScaleType == null) {
            return ImageScaleType.CENTER_CROP;
        }
        return mImageScaleType;
    }

    public boolean isStaggered() {
        if (mStaggered == null) {
            return false;
        }
        return mStaggered;
    }

    public boolean isUsingPadding() {
        if (mUsingPadding == null) {
            return true;
        }
        return mUsingPadding;
    }

    public String getTitle() {
        return mTitle;
    }

    public ModuleType getModuleType() {
        if (mModuleType == null) {
            return ModuleType.UNKNOWN;
        }
        return mModuleType;
    }

    public Variant getVariant() {
        if (mVariant == null) {
            return Variant.NONE;
        }
        return mVariant;
    }

    public String getCategory() {
        return mCategory;
    }

    public int getSpan() {
        if (mSpan == 0) {
            return 1;
        }
        return mSpan;
    }

    public boolean isShuffle() {
        return mShuffle;
    }

    public ArrayList<ContactItem> getContactItems() {
        if (mContactItems == null) {
            mContactItems = new ArrayList<>();
        }
        return mContactItems;
    }
}
