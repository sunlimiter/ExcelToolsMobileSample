package com.tywho.exceltoolsmobile.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tywho.exceltoolsmobile.R;
import com.tywho.exceltoolsmobile.base.BaseFragment;
import com.tywho.exceltoolsmobile.bean.QuestionContent;
import com.tywho.exceltoolsmobile.helper.DialogHelper;
import com.tywho.exceltoolsmobile.ui.adapter.ListDataAdapter;
import com.tywho.exceltoolsmobile.ui.adapter.QuestionAdapter;
import com.tywho.exceltoolsmobile.ui.adapter.datasources.DataSource;
import com.tywho.exceltoolsmobile.utils.DBUtil;
import com.tywho.exceltoolsmobile.utils.MVCLoadMoreHelper;
import com.tywho.exceltoolsmobile.widget.SmartSwipeRefreshLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-19 10:21
 */
public class SearchFragment extends BaseFragment {
    @Bind(R.id.edit_query)
    EditText editQuery;
    @Bind(R.id.btn_query)
    Button btnQuery;
    @Bind(R.id.pull_refresh_rv)
    SmartSwipeRefreshLayout pullRefreshRv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    private MVCLoadMoreHelper<List<QuestionContent>> mvcHelper;
    private QuestionAdapter adapter;
    private DBUtil dbUtil;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.search_fragment;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        toolbar.setTitle("Excel搜索");
        initToolbarMenu(toolbar);
        dbUtil = DBUtil.getDbInstance(_mActivity);
        adapter = new QuestionAdapter();
        adapter.setOnItemClickLitener(new ListDataAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                List<QuestionContent> list = dbUtil.selectAllContent(adapter.getData().get(position).getLocationx(), adapter.getData().get(position).getParentid());
                DialogHelper.showBottomSheetContent(_mActivity, dbUtil.selectType(adapter.getData().get(position).getParentid()).getName(), list);
            }
        });

        pullRefreshRv.initWithLinearLayout();
        mvcHelper = new MVCLoadMoreHelper<>(pullRefreshRv.getSwipeRefreshLayout(), adapter,
                new DataSource<QuestionContent>(new Func1<Integer, Observable<List<QuestionContent>>>() {
                    @Override
                    public Observable<List<QuestionContent>> call(Integer integer) {
                        return getQuestionContent(integer);
                    }
                }));
        mvcHelper.refresh();
    }

    private Observable<List<QuestionContent>> getQuestionContent(final int page) {
        return Observable.create(new Observable.OnSubscribe<List<QuestionContent>>() {
            @Override
            public void call(Subscriber<? super List<QuestionContent>> subscriber) {
                List<QuestionContent> list = dbUtil.selectLike(editQuery.getText().toString(), page);
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    protected void initToolbarMenu(Toolbar toolbar) {
        super.initToolbarMenu(toolbar);
        toolbar.inflateMenu(R.menu.total);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_total:
                        break;
                }
                return true;
            }
        });
    }

    @OnClick(R.id.btn_query)
    public void queryClick(View view) {
        mvcHelper.refresh();
    }

    @OnClick(R.id.fab)
    public void fabClick(View view) {
    }

}
