package com.tywho.exceltoolsmobile.utils;

import android.support.v4.widget.SwipeRefreshLayout;

import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.IDataAdapter;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.MVCSwipeRefreshHelper;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 15:05
 */
public class MVCLoadMoreHelper<DATA> {
    private MVCHelper<DATA> mvcHelper;

    public MVCLoadMoreHelper(SwipeRefreshLayout swipeRefreshLayout, IDataAdapter<DATA> dataAdapter, IAsyncDataSource<DATA> dataSource) {
//        MVCHelper.setLoadViewFractory(new CustomLoadViewFactory());
        mvcHelper = new MVCSwipeRefreshHelper<>(swipeRefreshLayout);
        mvcHelper.setNeedCheckNetwork(false);
        mvcHelper.setAutoLoadMore(true);
        mvcHelper.setAdapter(dataAdapter);
        mvcHelper.setDataSource(dataSource);
    }


    public void refresh() {
        mvcHelper.refresh();
    }

    public void destory() {
        mvcHelper.destory();
    }
}
