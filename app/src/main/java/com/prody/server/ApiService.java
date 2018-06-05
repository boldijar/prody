package com.prody.server;

import com.prody.core.data.models.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    @GET("products")
    Observable<List<Product>> getProducts(@Query("category") String category);

    @GET
    Observable<List<Product>> getProductsByUrl(@Url String url);
}
