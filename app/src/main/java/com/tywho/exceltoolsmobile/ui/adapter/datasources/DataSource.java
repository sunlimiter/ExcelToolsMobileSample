package com.tywho.exceltoolsmobile.ui.adapter.datasources;

import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;
import com.tywho.exceltoolsmobile.helper.RedirectResponseTransformer;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 14:55
 */
public class DataSource<T> implements IAsyncDataSource<List<T>> {
    private int mPage = 0;
    private int maxPageSize = 15;
    private int pageSize = 0;
    private Func1 func1;

    public DataSource(Func1 func1) {
        super();
        this.func1 = func1;
    }

    @Override
    public RequestHandle refresh(ResponseSender<List<T>> sender) throws Exception {
        return loadData(sender, 0);
    }

    @Override
    public RequestHandle loadMore(ResponseSender<List<T>> sender) throws Exception {
        return loadData(sender, mPage + 1);
    }

    @Override
    public boolean hasMore() {
        return pageSize >= maxPageSize;
    }

    private RequestHandle loadData(final ResponseSender<List<T>> sender, final int page) throws Exception {
        Subscription call = Observable.just(page)
                .concatMap(func1)
                .compose(new RedirectResponseTransformer<List<T>>())
                .subscribe(new Subscriber<List<T>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        sender.sendError(new Exception(e));
                    }

                    @Override
                    public void onNext(List<T> result) {
                        mPage = page;
                        pageSize = result.size();
                        sender.sendData(result);
                    }
                });
        return new RxRequestHandle(call);
    }

    class RxRequestHandle implements RequestHandle {

        private final Subscription call;

        public RxRequestHandle(Subscription call) {
            super();
            this.call = call;
        }

        @Override
        public void cancle() {
            if (!call.isUnsubscribed()) {
                call.unsubscribe();
            }
        }

        @Override
        public boolean isRunning() {
            return !call.isUnsubscribed();
        }
    }
}

