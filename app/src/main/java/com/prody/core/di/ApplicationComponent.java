package com.prody.core.di;

import com.prody.Shaorma;
import com.prody.core.data.DataModule;
import com.prody.core.server.ApiModule;
import com.prody.modules.contact.ContactAdapter;
import com.prody.modules.drawer.DrawerFragment;
import com.prody.modules.drawer.DrawerItemAdapter;
import com.prody.modules.home.activity.HomeActivity;
import com.prody.modules.home.fragment.HomeFragment;
import com.prody.modules.modularfragment.RegularListAdapter;
import com.prody.modules.modularfragment.mvp.ModularFragmentPresenter;

import dagger.Component;

@Component(modules = {ApplicationModule.class, ApiModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(Shaorma shaorma);

    void inject(HomeActivity homeActivity);

    void inject(HomeFragment homeFragment);

    void inject(ModularFragmentPresenter modularFragmentPresenter);

    void inject(RegularListAdapter regularListAdapter);

    void inject(DrawerFragment drawerFragment);

    void inject(DrawerItemAdapter drawerItemAdapter);

    void inject(ContactAdapter contactAdapter);
}