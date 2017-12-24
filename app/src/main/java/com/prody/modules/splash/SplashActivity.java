package com.prody.modules.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gym.app.R;
import com.prody.core.data.DataModule;
import com.prody.core.data.models.config.Config;
import com.prody.core.ui.activity.BaseActivity;
import com.prody.modules.home.activity.HomeActivity;

/**
 * @author Paul
 * @since 2017.08.29
 */

public class SplashActivity extends BaseActivity implements SplashView {

    private SplashPresenter mSplashPresenter = new SplashPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashPresenter.loadConfig(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSplashPresenter.destroySubscriptions();
    }

    @Override
    public void showConfig(Config config) {
        DataModule.initConfig(config);
        startActivity(HomeActivity.createIntent(this));
        finish();
    }
}
