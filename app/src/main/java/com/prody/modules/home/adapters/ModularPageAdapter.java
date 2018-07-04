package com.prody.modules.home.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.prody.core.data.models.config.MenuItem;
import com.prody.core.ui.fragment.UnimplementedFragment;
import com.prody.modules.contact.ContactFragment;
import com.prody.modules.modularfragment.ModularFragment;

import java.util.HashMap;
import java.util.List;

/**
 * @author Paul
 * @since 2017.12.24
 */

public class ModularPageAdapter extends FragmentPagerAdapter {

    private final List<MenuItem> mMenuItems;
    private final HashMap<Integer, Fragment> mFragmentHashMap;

    public ModularPageAdapter(FragmentManager fm, @NonNull List<MenuItem> menuItems) {
        super(fm);
        mMenuItems = menuItems;
        mFragmentHashMap = new HashMap<>();
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (mMenuItems.get(position).getModuleType()) {
            case VERTICAL_LIST:
                fragment = mFragmentHashMap.get(position);
                if (fragment == null) {
                    fragment = ModularFragment.newInstance(mMenuItems.get(position));
                    mFragmentHashMap.put(position, fragment);
                }
                return fragment;
            case CONTACT:
                fragment = mFragmentHashMap.get(position);
                if (fragment == null) {
                    fragment = ContactFragment.newInstance(mMenuItems.get(position));
                    mFragmentHashMap.put(position, fragment);
                }
                return fragment;
        }
        return new UnimplementedFragment();
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
