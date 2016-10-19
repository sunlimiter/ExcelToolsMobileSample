package com.tywho.exceltoolsmobile.helper;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tywho.exceltoolsmobile.R;
import com.tywho.exceltoolsmobile.bean.QuestionContent;
import com.tywho.exceltoolsmobile.ui.adapter.NormalRecyclerViewAdapter;
import com.tywho.exceltoolsmobile.widget.DividerItemDecoration;

import java.util.List;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 17:25
 */
public class DialogHelper {
    public static void showBottomSheetContent(Context context, String title,List<QuestionContent> contents) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_content, null);
        ((TextView) view.findViewById(R.id.type_tv)).setText(title);
        RecyclerView mRecyclerView =  (RecyclerView) view.findViewById(R.id.recyclerView);

        NormalRecyclerViewAdapter adapter = new NormalRecyclerViewAdapter(context);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST);
        itemDecoration.setDivider(context.getResources().getDrawable(R.color.colorAccent));
        itemDecoration.setDividerHeight(10);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));//这里用线性显示 类似于listview


        mRecyclerView.setAdapter(adapter);
        adapter.setContents(contents);

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
