package com.prody.modules.modularfragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gym.app.R;
import com.prody.core.data.models.Product;
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

    public RegularListAdapter(List<Product> products) {
        mProducts = products;
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
            Glide.with(holder.mImageView.getContext()).load(product.getImage()).into(holder.mImageView);
        }
        if (holder.mTitle != null) {
            holder.mTitle.setText(product.getTitle());
            holder.mTitle.setTextColor(mStyle.getTextColorOnBackground());
        }
        if (holder.mSubtitle != null) {
            holder.mSubtitle.setText(product.getSubtitle());
            holder.mSubtitle.setTextColor(mStyle.getTextColorOnBackground());
        }
        if (holder.mPrice != null) {
            holder.mPrice.setText(product.getPrice());
            holder.mPrice.setTextColor(mStyle.getAccent());
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
