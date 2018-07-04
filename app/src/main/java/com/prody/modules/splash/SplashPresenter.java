package com.prody.modules.splash;

import android.content.Context;

import com.google.gson.Gson;
import com.prody.core.data.models.config.Config;
import com.prody.core.mvp.MvpObserver;
import com.prody.core.mvp.Presenter;

import java.io.InputStream;
import java.io.InputStreamReader;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Paul
 * @since 2017.12.17
 */

public class SplashPresenter extends Presenter<SplashView> {

    public SplashPresenter(SplashView view) {
        super(view);
    }

    public void loadConfig(final Context context, final String config) {
        Observable.create(new ObservableOnSubscribe<Config>() {
            @Override
            public void subscribe(ObservableEmitter<Config> e) throws Exception {
                Thread.sleep(2000);
                InputStream inputStream = context.getAssets().open(config);
                InputStreamReader reader = new InputStreamReader(inputStream);
                Config config = new Gson().fromJson(reader, Config.class);
                e.onNext(config);
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MvpObserver<Config>(this) {
                    @Override
                    public void onNext(Config value) {
                        getView().showConfig(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        e.printStackTrace();
                    }
                });
    }
}
