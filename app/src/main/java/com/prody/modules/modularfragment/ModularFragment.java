package com.prody.modules.modularfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.gym.app.R;
import com.prody.core.data.models.config.MenuItem;
import com.prody.core.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Paul
 * @since 2017.12.24
 */

public class ModularFragment extends BaseFragment {

    public static ModularFragment newInstance(MenuItem item) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        ModularFragment fragment = new ModularFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String ARG_ITEM = "item";

    @BindView(R.id.module_title)
    TextView mTitle;

    private MenuItem mItem;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_module;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mItem = (MenuItem) getArguments().getSerializable(ARG_ITEM);
        mTitle.setText(mItem.getTitle());
    }
}
