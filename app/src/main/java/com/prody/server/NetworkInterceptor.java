package com.prody.server;


import android.app.Application;

import com.prody.data.Prefs;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Paul
 * @since 2017.08.30
 */

public class NetworkInterceptor implements Interceptor {

    private final Application mApplication;

    public NetworkInterceptor(Application application) {
        mApplication = application;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request original = chain.request();

        Request.Builder builder = original.newBuilder();
//        String token = Prefs.Token.get();
//        if (token != null) {
//            builder = builder.header("Authorization", "Bearer " + Prefs.Token.get());
//        }

        Response response = chain.proceed(builder.build());
        if (!response.isSuccessful()) {
            throw new ServerException(response.message(), response.code());
        }
        return response;
    }
}
