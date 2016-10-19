package com.tywho.exceltoolsmobile.ui;

import android.os.Bundle;

import com.tywho.exceltoolsmobile.R;
import com.tywho.exceltoolsmobile.base.BaseActivity;
import com.tywho.exceltoolsmobile.ui.fragment.SearchFragment;


public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, SearchFragment.newInstance());
        }
    }
}
