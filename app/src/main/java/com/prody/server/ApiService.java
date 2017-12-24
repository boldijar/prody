package com.prody.server;

import com.prody.core.data.models.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("products")
    Observable<List<Product>> getProducts(@Query("category") String category);
}
