package com.prody.modules.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.prody.R;
import com.prody.core.data.models.config.Hierarchy;
import com.prody.core.data.models.config.MenuItem;
import com.prody.core.data.models.config.Style;
import com.prody.core.di.InjectionHelper;
import com.prody.core.ui.fragment.BaseFragment;
import com.prody.modules.home.HomeFragmentNavigation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TODO: Class description
 *
 * @author Paul
 * @since 2017.12.25
 */

public class DrawerFragment extends BaseFragment {

    private static final String KEY_ITEMS = "items";

    public static DrawerFragment newInstance(List<MenuItem> items, HomeFragmentNavigation homeFragmentNavigation) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_ITEMS, new ArrayList<>(items));
        DrawerFragment fragment = new DrawerFragment();
        fragment.setArguments(args);
        fragment.mHomeFragmentNavigation = homeFragmentNavigation;
        return fragment;
    }

    @Inject
    Style mStyle;
    @Inject
    Hierarchy mHierarchy;

    @BindView(R.id.drawer_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.drawer_background)
    View mBackground;
    @BindView(R.id.drawer_image)
    ImageView mImage;

    private ArrayList<MenuItem> mMenuItems;
    private HomeFragmentNavigation mHomeFragmentNavigation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectionHelper.getApplicationComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_drawer;
    }

    @Override
    protected void onCreateView(View view) {
        super.onCreateView(view);
        mUnbinder = ButterKnife.bind(this, view);
        mBackground.setBackgroundColor(mStyle.getDrawerBackground());

        if (TextUtils.isEmpty(mHierarchy.getDrawerImage())) {
            mImage.setVisibility(View.GONE);
        } else {
            Glide.with(this).load(mHierarchy.getDrawerImage()).into(mImage);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //noinspection unchecked
        mMenuItems = (ArrayList<MenuItem>) getArguments().getSerializable(KEY_ITEMS);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new DrawerItemAdapter(mMenuItems, mHomeFragmentNavigation));
    }
}
