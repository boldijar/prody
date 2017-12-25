package com.prody.modules.modularfragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prody.R;
import com.prody.core.data.models.Product;
import com.prody.core.data.models.config.MenuItem;
import com.prody.core.data.models.config.Style;
import com.prody.core.data.models.config.Variant;
import com.prody.core.di.InjectionHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Paul
 * @since 2017.12.24
 */

public class RegularListAdapter extends RecyclerView.Adapter<RegularListAdapter.ViewHolder> {

    @Inject
    Style mStyle;

    private final List<Product> mProducts;
    private final MenuItem mMenuItem;

    public RegularListAdapter(List<Product> products, MenuItem menuItem) {
        mProducts = products;
        mMenuItem = menuItem;
        InjectionHelper.getApplicationComponent().inject(this);
    }

    @Override
    public int getItemViewType(int position) {
        return mProducts.get(position).getVariant().ordinal();
    }

    @Override
    public RegularListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Variant variant = Variant.values()[viewType];
        if (variant.getLayoutRes() == 0) {
            throw new RuntimeException("Variant layout is missing! " + variant);
        }
        return new RegularListAdapter.ViewHolder(parent, variant.getLayoutRes());
    }

    @Override
    public void onBindViewHolder(RegularListAdapter.ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        if (holder.mImageView != null) {
            holder.mImageView.setScaleType(mMenuItem.getImageScaleType().getScaleType());
            Glide.with(holder.mImageView.getContext()).load(product.getImage()).into(holder.mImageView);
        }
        if (holder.mTitle != null) {
            if (TextUtils.isEmpty(product.getTitle())) {
                holder.mTitle.setVisibility(View.GONE);
            } else {
                holder.mTitle.setText(product.getTitle());
                holder.mTitle.setTextColor(mStyle.getTextColorOnBackground());
                holder.mTitle.setVisibility(View.VISIBLE);
            }
        }
        if (holder.mSubtitle != null) {
            if (TextUtils.isEmpty(product.getSubtitle())) {
                holder.mSubtitle.setVisibility(View.GONE);
            } else {
                holder.mSubtitle.setText(product.getSubtitle());
                holder.mSubtitle.setTextColor(mStyle.getTextColorOnBackground());
                holder.mSubtitle.setVisibility(View.VISIBLE);
            }
        }
        if (holder.mPrice != null) {
            if (TextUtils.isEmpty(product.getPrice())) {
                holder.mPrice.setVisibility(View.GONE);
            } else {
                holder.mPrice.setText(product.getPrice());
                holder.mPrice.setTextColor(mStyle.getAccent());
                holder.mPrice.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image)
        @Nullable
        ImageView mImageView;
        @Nullable
        @BindView(R.id.item_title)
        TextView mTitle;
        @Nullable
        @BindView(R.id.item_subtitle)
        TextView mSubtitle;
        @Nullable
        @BindView(R.id.item_price)
        TextView mPrice;

        public ViewHolder(ViewGroup parent, int layout) {
            super(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
