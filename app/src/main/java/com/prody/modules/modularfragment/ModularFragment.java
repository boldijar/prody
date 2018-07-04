package com.prody.modules.modularfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.prody.R;
import com.prody.core.data.models.Product;
import com.prody.core.data.models.config.MenuItem;
import com.prody.core.ui.fragment.BaseFragment;
import com.prody.core.ui.view.GridSpacingItemDecoration;
import com.prody.modules.modularfragment.mvp.ModularFragmentPresenter;
import com.prody.modules.modularfragment.mvp.ModularFragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Paul
 * @since 2017.12.24
 */

public class ModularFragment extends BaseFragment implements ModularFragmentView {

    private List<Product> mItems;

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
    @BindView(R.id.module_recycler)
    RecyclerView mRecyclerView;

    private MenuItem mItem;
    private ModularFragmentPresenter mModularFragmentPresenter = new ModularFragmentPresenter(this);
    private RegularListAdapter mRegularListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_module;
    }


    @Override
    protected void onCreateView(View view) {
        ButterKnife.bind(this, view);
        if (mItems == null) {
            mModularFragmentPresenter.loadProducts(mItem);
        } else {
            showProducts(mItems);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItem = (MenuItem) getArguments().getSerializable(ARG_ITEM);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProducts(List<Product> products) {
        mItems = products;
        mRegularListAdapter = new RegularListAdapter(products, mItem);
        if (mItem.getSpan() > 1 && mItem.isStaggered()) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(mItem.getSpan(), StaggeredGridLayoutManager.VERTICAL));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), mItem.getSpan()));
        }
        mRecyclerView.setAdapter(mRegularListAdapter);
        if (mItem.isUsingPadding()) {
            mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(mItem.getSpan(), getResources().getDimensionPixelSize(R.dimen.card_margin), false));
        }
    }
}
