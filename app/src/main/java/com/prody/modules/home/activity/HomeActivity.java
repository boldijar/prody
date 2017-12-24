package com.prody.modules.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.gym.app.R;
import com.prody.core.data.models.config.Hierarchy;
import com.prody.core.data.models.config.Style;
import com.prody.core.di.InjectionHelper;
import com.prody.core.ui.activity.BaseConfigActivity;
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

    public static Intent createIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InjectionHelper.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        initUiFromConfig();
        setFragment(new HomeFragment());
    }

    private void initUiFromConfig() {
        mLinearLayout.setBackgroundColor(mStyle.getBackground());
        if (mHierarchy.mHasToolbar) {
            View toolbarView = getLayoutInflater().inflate(R.layout.toolbar, mLinearLayout, false);
            Toolbar toolbar = toolbarView.findViewById(R.id.toolbar);
            toolbar.setTitle(mHierarchy.mTitle);
            toolbar.setTitleTextColor(mStyle.getTextColor());
            toolbar.setBackgroundColor(mStyle.getPrimary());
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
        }
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_container, fragment)
                .commit();
    }

}
