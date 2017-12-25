package com.prody.modules.drawer;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gym.app.R;
import com.prody.core.data.models.config.MenuItem;
import com.prody.core.data.models.config.Style;
import com.prody.core.di.InjectionHelper;
import com.prody.modules.home.HomeFragmentNavigation;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Paul
 * @since 2017.12.25
 */

public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemAdapter.ViewHolder> {

    @Inject
    Style mStyle;
    private final List<MenuItem> mMenuItems;
    private final HomeFragmentNavigation mHomeFragmentNavigation;

    public DrawerItemAdapter(List<MenuItem> menuItems, HomeFragmentNavigation homeFragmentNavigation) {
        mMenuItems = menuItems;
        mHomeFragmentNavigation = homeFragmentNavigation;
        InjectionHelper.getApplicationComponent().inject(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, mStyle);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MenuItem item = mMenuItems.get(position);
        holder.mText.setText(item.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHomeFragmentNavigation.goToPage(holder.getAdapterPosition());
            }
        });
        if (TextUtils.isEmpty(item.getIcon())) {
            holder.mIcon.setImageResource(android.R.color.transparent);
        } else {
            Glide.with(holder.mIcon).load(item.getIcon()).into(holder.mIcon);
        }
    }

    @Override
    public int getItemCount() {
        return mMenuItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_drawer_icon)
        ImageView mIcon;
        @BindView(R.id.item_drawer_text)
        TextView mText;

        public ViewHolder(ViewGroup viewGroup, Style style) {
            super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_drawer, viewGroup, false));
            ButterKnife.bind(this, itemView);
            mText.setTextColor(style.getDrawerTextColor());
        }
    }
}
