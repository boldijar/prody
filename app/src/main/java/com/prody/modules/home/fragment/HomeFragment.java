package com.prody.modules.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prody.R;
import com.prody.core.data.models.config.Hierarchy;
import com.prody.core.data.models.config.MenuItem;
import com.prody.core.data.models.config.NavigationType;
import com.prody.core.data.models.config.Style;
import com.prody.core.di.InjectionHelper;
import com.prody.core.ui.fragment.BaseFragment;
import com.prody.core.ui.view.EnablingViewPager;
import com.prody.modules.home.HomeFragmentNavigation;
import com.prody.modules.home.activity.HomeActivity;
import com.prody.modules.home.adapters.ModularPageAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * @author Paul
 * @since 2017.12.17
 */

public class HomeFragment extends BaseFragment implements HomeFragmentNavigation {

    @Inject
    Hierarchy mHome;
    @Inject
    Style mStyle;

    @BindView(R.id.home_parent)
    LinearLayout mLinearLayout;
    @BindView(R.id.home_pager)
    EnablingViewPager mViewPager;

    private ModularPageAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        InjectionHelper.getApplicationComponent().inject(this);
        initUi();
        initUiFromConfig();
    }

    private void initUi() {
        mAdapter = new ModularPageAdapter(getChildFragmentManager(), mHome.getMenuItems());
        mViewPager.setAdapter(mAdapter);
    }

    private void initUiFromConfig() {
        mLinearLayout.setBackgroundColor(mStyle.getBackground());
        if (mHome.getNavigationType() == NavigationType.TABS) {
            loadTabs();
        }
    }

    private void loadTabs() {
        if (mHome.getMenuItems() != null) {
            mViewPager.setPagingEnabled(true);
            final TabLayout tabLayout = new TabLayout(getContext());
            TabLayout.LayoutParams layoutParams = new TabLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.setLayoutParams(layoutParams);
            tabLayout.setBackgroundColor(mStyle.getPrimary());
            tabLayout.setTabTextColors(mStyle.getTextColorSecondary(), mStyle.getTextColor());

            for (MenuItem menuItem : mHome.getMenuItems()) {
                tabLayout.addTab(
                        tabLayout.newTab().setText(menuItem.getTitle())
                );
            }
            tabLayout.setSelectedTabIndicatorColor(mStyle.getAccent());
            mLinearLayout.addView(tabLayout, 0);
            tabLayout.setupWithViewPager(mViewPager);
        }

    }

    @Override
    public void goToPage(int index) {
        if (index >= mViewPager.getAdapter().getCount()) {
            Timber.e("Invalid index " + index + " when size is " + mViewPager.getAdapter().getCount());
            return;
        }
        mViewPager.setCurrentItem(index, false);
        ((HomeActivity) getActivity()).closeDrawer();
    }
}
