package com.prody.core.data.models.config;

import android.graphics.Color;

import com.google.gson.annotations.SerializedName;

public class Style {
    @SerializedName("primary")
    private String mPrimary;
    @SerializedName("secondary")
    private String mSecondary;
    @SerializedName("background")
    private String mBackground;
    @SerializedName("text_color")
    private String mTextColor;
    @SerializedName("text_color_secondary")
    private String mTextColorSecondary;
    @SerializedName("accent")
    private String mAccent;
    @SerializedName("text_color_on_background")
    private String mTextColorOnBackground;
    @SerializedName("drawer_background")
    private String mDrawerBackground;
    @SerializedName("drawer_text_color")
    private String mDrawerTextColor;

    public int getDrawerTextColor() {
        return parseColor(mDrawerTextColor);
    }

    public int getDrawerBackground() {
        return parseColor(mDrawerBackground);
    }

    public int getAccent() {
        return parseColor(mAccent);
    }

    public int getPrimary() {
        return parseColor(mPrimary);
    }

    public int getSecondary() {
        return parseColor(mSecondary);
    }

    public int getBackground() {
        return parseColor(mBackground);
    }

    public int getTextColor() {
        return parseColor(mTextColor);
    }

    public int getTextColorSecondary() {
        return parseColor(mTextColorSecondary);
    }

    public int getTextColorOnBackground() {
        return parseColor(mTextColorOnBackground);
    }

    private static int parseColor(String color) {
        try {
            return Color.parseColor(color);
        } catch (Exception e) {
            return Color.BLACK;
        }
    }

    @Override
    public String toString() {
        return "Style{" +
                "mPrimary='" + mPrimary + '\'' +
                ", mSecondary='" + mSecondary + '\'' +
                ", mBackground='" + mBackground + '\'' +
                '}';
    }
}
