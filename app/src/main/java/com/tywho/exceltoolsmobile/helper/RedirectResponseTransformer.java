package com.tywho.exceltoolsmobile.helper;

import java.net.SocketTimeoutException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 14:57
 */
public class RedirectResponseTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> CommonResponseObservable) {
        return CommonResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .lift(new Observable.Operator<T, T>() {
                    @Override
                    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
                        return new Subscriber<T>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(T tBaseBean) {
                                subscriber.onNext(tBaseBean);
                            }
                        };
                    }
                });
    }
}