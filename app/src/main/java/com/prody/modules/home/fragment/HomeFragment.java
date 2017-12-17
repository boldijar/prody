package com.prody.modules.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gym.app.R;
import com.prody.core.data.models.config.Home;
import com.prody.core.data.models.config.MainMenuItem;
import com.prody.core.data.models.config.Style;
import com.prody.core.di.InjectionHelper;
import com.prody.core.ui.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Paul
 * @since 2017.12.17
 */

public class HomeFragment extends BaseFragment {

    @Inject
    Home mHome;
    @Inject
    Style mStyle;

    @BindView(R.id.home_parent)
    LinearLayout mLinearLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        InjectionHelper.getApplicationComponent().inject(this);
        initUiFromConfig();
    }

    private void initUiFromConfig() {
        mLinearLayout.setBackgroundColor(mStyle.getBackground());
        if (mHome.getNavigationType() == Home.NavigationType.TABS) {
            final TabLayout tabLayout = new TabLayout(getContext());
            TabLayout.LayoutParams layoutParams = new TabLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.setLayoutParams(layoutParams);
            tabLayout.setBackgroundColor(mStyle.getPrimary());
            tabLayout.setTabTextColors(mStyle.getTextColorSecondary(), mStyle.getTextColor());
            if (mHome.getMainMenuItems() != null) {
                for (MainMenuItem mainMenuItem : mHome.getMainMenuItems()) {
                    tabLayout.addTab(
                            tabLayout.newTab().setText(mainMenuItem.getTitle())
                    );
                }
            }
            tabLayout.setSelectedTabIndicatorColor(mStyle.getAccent());
            mLinearLayout.addView(tabLayout, 0);
        }
    }
}
