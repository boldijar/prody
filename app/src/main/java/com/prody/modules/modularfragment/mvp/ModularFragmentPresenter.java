package com.prody.modules.modularfragment.mvp;

import com.prody.core.data.models.Product;
import com.prody.core.data.models.config.MenuItem;
import com.prody.core.di.InjectionHelper;
import com.prody.core.mvp.MvpObserver;
import com.prody.core.mvp.Presenter;
import com.prody.server.ApiService;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Paul
 * @since 2017.12.24
 */

public class ModularFragmentPresenter extends Presenter<ModularFragmentView> {

    @Inject
    ApiService mApiService;

    public ModularFragmentPresenter(ModularFragmentView view) {
        super(view);
        InjectionHelper.getApplicationComponent().inject(this);
    }

    public void loadProducts(final MenuItem menuItem) {
        mApiService.getProducts(menuItem.getCategory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MvpObserver<List<Product>>(this) {
                    @Override
                    public void onNext(List<Product> value) {
                        for (Product product : value) {
                            product.setVariant(menuItem.getVariant());
                        }
                        if (menuItem.isShuffle()) {
                            Collections.shuffle(value);
                        }
                        getView().showProducts(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getView().showError();
                    }
                });
    }
}
