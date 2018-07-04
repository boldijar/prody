package com.prody.modules.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Spinner;

import com.prody.R;
import com.prody.core.data.DataModule;
import com.prody.core.data.models.config.Config;
import com.prody.core.ui.activity.BaseActivity;
import com.prody.modules.home.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Paul
 * @since 2017.08.29
 */

public class SplashActivity extends BaseActivity implements SplashView {

    private SplashPresenter mSplashPresenter = new SplashPresenter(this);
    private static String[] CONFIGS = new String[]{
            "config-company.json",
            "config-portfolio.json",
            "config-club.json"
    };
    @BindView(R.id.splash_config)
    Spinner mSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
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
    }

    public void clickedNext(View view) {
        mSplashPresenter.loadConfig(getApplicationContext(), CONFIGS[mSpinner.getSelectedItemPosition()]);

    }
}
