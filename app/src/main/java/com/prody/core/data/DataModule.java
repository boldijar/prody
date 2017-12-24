package com.prody.core.data;

import com.prody.Shaorma;
import com.prody.core.data.models.config.Config;
import com.prody.core.data.models.config.Hierarchy;
import com.prody.core.data.models.config.Style;
import com.prody.core.server.ApiModule;

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
    Hierarchy provideHierarchy() {
        return CONFIG.mHierarchy;
    }

    @Provides
    Style provideStyle() {
        return CONFIG.mStyle;
    }

    public static void initConfig(Config config) {
        CONFIG = config;
        ApiModule.ENDPOINT = config.getCore().getEndpoint();
    }
}