package com.prody.modules.modularfragment.mvp;

import com.prody.core.data.models.Product;

import java.util.List;

/**
 * @author Paul
 * @since 2017.12.24
 */

public interface ModularFragmentView {

    void showError();

    void showProducts(List<Product> products);
}
