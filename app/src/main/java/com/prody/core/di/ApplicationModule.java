package com.prody.core.di;

import android.content.Context;
import android.net.ConnectivityManager;

import com.prody.data.SystemUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Context mAppContext;

    public ApplicationModule(Context appContext) {
        mAppContext = appContext.getApplicationContext();
    }

    @Provides
    ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) mAppContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    SystemUtils provideSystemUtils(ConnectivityManager connectivityManager) {
        return new SystemUtils(connectivityManager);
    }

}