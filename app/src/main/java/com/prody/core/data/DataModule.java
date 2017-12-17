package com.prody.core.data;

import com.prody.Shaorma;
import com.prody.core.data.models.config.Config;
import com.prody.core.data.models.config.Home;
import com.prody.core.data.models.config.Style;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    public static Config CONFIG = null;
    private Shaorma mShaorma;

    public DataModule(Shaorma shaorma) {
        mShaorma = shaorma;
    }

    @Provides
    Config provideConfig() {
        if (CONFIG == null) {
            return Config.EMPTY;
        }
        return CONFIG;
    }

    @Provides
    Home provideHome() {
        return CONFIG.mHierarchy.mHome;
    }

    @Provides
    Style provideStyle() {
        return CONFIG.mStyle;
    }
}