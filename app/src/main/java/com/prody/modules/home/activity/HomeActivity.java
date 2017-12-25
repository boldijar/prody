package com.prody.modules.home.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.prody.R;
import com.prody.core.data.models.config.Hierarchy;
import com.prody.core.data.models.config.NavigationType;
import com.prody.core.data.models.config.Style;
import com.prody.core.di.InjectionHelper;
import com.prody.core.ui.activity.BaseConfigActivity;
import com.prody.modules.drawer.DrawerFragment;
import com.prody.modules.home.fragment.HomeFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Paul
 * @since 2017.08.29
 */

public class HomeActivity extends BaseConfigActivity {

    @Inject
    Hierarchy mHierarchy;
    @Inject
    Style mStyle;

    @BindView(R.id.home_parent)
    LinearLayout mLinearLayout;
    @BindView(R.id.home_drawer_layout)
    DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;
    private HomeFragment mHomeFragment;

    public static Intent createIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InjectionHelper.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        mHomeFragment = new HomeFragment();
        initUiFromConfig();
        setFragment(mHomeFragment);
    }

    private void initUiFromConfig() {
        mLinearLayout.setBackgroundColor(mStyle.getBackground());
        if (mHierarchy.mHasToolbar) {
            View toolbarView = getLayoutInflater().inflate(R.layout.toolbar, mLinearLayout, false);
            mToolbar = toolbarView.findViewById(R.id.toolbar);
            mToolbar.setTitle(mHierarchy.mTitle);
            mToolbar.setTitleTextColor(mStyle.getTextColor());
            mToolbar.setBackgroundColor(mStyle.getPrimary());
            mLinearLayout.addView(toolbarView, 0);

            if (!TextUtils.isEmpty(mHierarchy.mImage)) {
                ImageView imageView = toolbarView.findViewById(R.id.image);
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                switch (mHierarchy.getImageAlignment()) {
                    case CENTER:
                        params.gravity = Gravity.CENTER;
                        break;
                    case RIGHT:
                        params.gravity = Gravity.END;
                        break;
                    default:
                        params.gravity = Gravity.START;
                }
                imageView.setLayoutParams(params);
                Glide.with(imageView).load(mHierarchy.mImage).into(imageView);
            }
            setSupportActionBar(mToolbar);
        }
        if (mHierarchy.getNavigationType() == NavigationType.DRAWER) {
            loadNavigationDrawerItems();
        } else {
            // disable drawer
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    private void loadNavigationDrawerItems() {
        if (mToolbar == null) {
            return;
        }
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.appname, R.string.appname) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_menu, null);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, mStyle.getTextColor());
        mToolbar.setNavigationIcon(drawable);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.home_drawer_container, DrawerFragment.newInstance(mHierarchy.getMenuItems(), mHomeFragment))
                .commit();
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_container, fragment)
                .commit();
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(Gravity.START);
    }
}
