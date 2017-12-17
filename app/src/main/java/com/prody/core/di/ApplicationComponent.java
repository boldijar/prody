package com.prody.core.di;

import com.prody.Shaorma;
import com.prody.core.data.DataModule;
import com.prody.core.server.ApiModule;
import com.prody.modules.home.activity.HomeActivity;
import com.prody.modules.home.fragment.HomeFragment;

import dagger.Component;

@Component(modules = {ApplicationModule.class, ApiModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(Shaorma shaorma);

    void inject(HomeActivity homeActivity);

    void inject(HomeFragment homeFragment);
}