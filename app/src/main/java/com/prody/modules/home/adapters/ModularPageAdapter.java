package com.prody.modules.home.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.prody.core.data.models.config.MenuItem;
import com.prody.modules.modularfragment.ModularFragment;

import java.util.List;

/**
 * @author Paul
 * @since 2017.12.24
 */

public class ModularPageAdapter extends FragmentPagerAdapter {

    private final List<MenuItem> mMenuItems;

    public ModularPageAdapter(FragmentManager fm, @NonNull List<MenuItem> menuItems) {
        super(fm);
        mMenuItems = menuItems;
    }


    @Override
    public Fragment getItem(int position) {
        return ModularFragment.newInstance(mMenuItems.get(position));
    }

    @Override
    public int getCount() {
        return mMenuItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMenuItems.get(position).getTitle();
    }
}
