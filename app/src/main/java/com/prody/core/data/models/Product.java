package com.prody.core.data.models;

import com.google.gson.annotations.SerializedName;
import com.prody.core.data.models.config.Variant;

/**
 * @author Paul
 * @since 2017.12.24
 */

public class Product {
    @SerializedName("id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("subtitle")
    private String mSubtitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("image")
    private String mImage;
    @SerializedName("link")
    private String mLink;
    @SerializedName("tags")
    private String mTags;
    @SerializedName("price")
    private String mPrice;

    // other fields
    private Variant mVariant;

    public Variant getVariant() {
        return mVariant;
    }

    public void setVariant(Variant variant) {
        mVariant = variant;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImage() {
        return mImage;
    }

    public String getLink() {
        return mLink;
    }

    public String getTags() {
        return mTags;
    }

    public String getPrice() {
        return mPrice;
    }
}
