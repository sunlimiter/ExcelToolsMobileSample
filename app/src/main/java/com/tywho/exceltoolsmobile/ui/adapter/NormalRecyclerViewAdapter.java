package com.tywho.exceltoolsmobile.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tywho.exceltoolsmobile.R;
import com.tywho.exceltoolsmobile.bean.QuestionContent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-19 09:27
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<QuestionContent> contents = new ArrayList<>();
    private String[] zimus = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    public NormalRecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setContents(List<QuestionContent> contents) {
        this.contents = contents;
        notifyDataSetChanged();
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_content_info, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.itemTitleTv.setText(position > zimus.length ? "#" : zimus[position]);
        holder.itemInfoTv.setText(contents.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return contents == null ? 0 : contents.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_title_tv)
        TextView itemTitleTv;
        @Bind(R.id.item_info_tv)
        TextView itemInfoTv;

        NormalTextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}