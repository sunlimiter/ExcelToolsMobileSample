package com.tywho.exceltoolsmobile.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.tywho.exceltoolsmobile.R;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 15:08
 */
public class SmartSwipeRefreshLayout  extends FrameLayout {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Drawable divider;
    private int dividerHeight;

    public SmartSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SmartSwipeRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartSwipeRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, new int[]{
                android.R.attr.divider,
                android.R.attr.dividerHeight
        }, defStyleAttr, defStyleRes);

        divider = a.getDrawable(0);
        dividerHeight = a.getDimensionPixelOffset(1, 0);
        a.recycle();

        inflate(context, R.layout.ssrl_swipe_refresh_layout, this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        if (divider != null && dividerHeight > 0) {
            DividerItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST);
            itemDecoration.setDivider(divider);
            itemDecoration.setDividerHeight(dividerHeight);
            recyclerView.addItemDecoration(itemDecoration);
        }

    }

    public void initWithLinearLayout() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(layoutManager);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

}
