package com.prody.modules.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.prody.R;
import com.prody.core.data.models.config.MenuItem;
import com.prody.core.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Paul
 * @since 2017.12.25
 */

public class ContactFragment extends BaseFragment {

    private static final String ARG_ITEM = "item";

    public static ContactFragment newInstance(MenuItem menuItem) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, menuItem);
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.contact_recycler)
    RecyclerView mRecyclerView;

    private MenuItem mItem;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItem = (MenuItem) getArguments().getSerializable(ARG_ITEM);
    }

    @Override
    protected void onCreateView(View view) {
        super.onCreateView(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new ContactAdapter(mItem.getContactItems()));
    }
}
