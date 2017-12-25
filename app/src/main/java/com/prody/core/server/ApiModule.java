package com.prody.core.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prody.BuildConfig;
import com.prody.Shaorma;
import com.prody.server.ApiService;
import com.prody.server.NetworkInterceptor;
import com.prody.utils.Constants;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public static String ENDPOINT = "www.google.ro";
    private Shaorma mShaorma;

    public ApiModule(Shaorma shaorma) {
        mShaorma = shaorma;
    }

    @Provides
    static Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    Cache provideCache() {
        File httpCacheDirectory = new File(mShaorma.getCacheDir().getAbsolutePath(), "HttpCache");
        return new Cache(httpCacheDirectory, Constants.DISK_CACHE_SIZE);
    }

    @Provides
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return interceptor;
    }

    @Provides
    OkHttpClient provideOkHttpClient(Cache cache,
                                     HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new NetworkInterceptor(mShaorma))
                .build();
    }

    @Provides
    CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides
    Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    Retrofit provideRetrofitHolder(final OkHttpClient okHttpClient,
                                   final Converter.Factory converter,
                                   final CallAdapter.Factory adapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(converter)
                .addCallAdapterFactory(adapterFactory)
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .build();
    }

    @Provides
    ApiService provideApiService(final Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}