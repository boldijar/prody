package com.prody.core.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.prody.core.data.models.config.Config;

import javax.inject.Inject;

/**
 * @author Paul
 * @since 2017.08.29
 */

public abstract class BaseConfigActivity extends BaseActivity {

    @Inject
    Config mConfig;

    @Override
    protected void onResume() {
        super.onResume();
        setStatusBarColor();
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(mConfig.mStyle.getSecondary());
        }
    }
}
