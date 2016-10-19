package com.tywho.exceltoolsmobile.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shizhefei.mvc.IDataAdapter;
import com.shizhefei.recyclerview.HFAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 15:49
 */
public abstract class ListDataAdapter<ITEM> extends HFAdapter implements IDataAdapter<List<ITEM>> {

    protected OnItemClickLitener mOnItemClickLitener;
    private List<ITEM> datas = new ArrayList<ITEM>();

    public abstract AbsItemViewHolder onCreateViewHolderHF(ViewGroup viewGroup, int type);

    @Override
    public final void onBindViewHolderHF(RecyclerView.ViewHolder vh, int position) {
        @SuppressWarnings("unchecked")
        AbsItemViewHolder holder = (AbsItemViewHolder) vh;
        ITEM item = datas.get(position);
        holder.setData(item, position);
    }

    @Override
    public final void onViewAttachedToWindow(RecyclerView.ViewHolder vh) {
        super.onViewAttachedToWindow(vh);
        if (isHeader(vh) || isFooter(vh)) {
            return;
        }
        AbsItemViewHolder holder = (AbsItemViewHolder) vh;
        holder.onViewAttachedToWindow();
    }

    @Override
    public final void onViewDetachedFromWindow(RecyclerView.ViewHolder vh) {
        super.onViewDetachedFromWindow(vh);
        if (isHeader(vh) || isFooter(vh)) {
            return;
        }
        AbsItemViewHolder holder = (AbsItemViewHolder) vh;
        holder.onViewDetachedFromWindow();
    }


    @Override
    public int getItemCountHF() {
        return datas.size();
    }

    @Override
    public void notifyDataChanged(List<ITEM> items, boolean isRefresh) {
        if (isRefresh) {
            datas.clear();
            datas.addAll(items);
            notifyDataSetChangedHF();
        } else {
            datas.addAll(items);
            notifyItemRangeInsertedHF(datas.size() - items.size(), items.size());
        }
    }

    @Override
    public List<ITEM> getData() {
        return datas;
    }

    @Override
    public boolean isEmpty() {
        return datas.isEmpty();
    }

    private LayoutInflater inflater;

    protected View inflate(int layoutId, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        return inflater.inflate(layoutId, viewGroup, false);
    }

    protected abstract class AbsItemViewHolder extends RecyclerView.ViewHolder {
        protected Context context;

        public AbsItemViewHolder(View itemView) {
            super(itemView);
            this.context = itemView.getContext();
        }

        public View findViewById(int id) {
            return itemView.findViewById(id);
        }

        public abstract void setData(ITEM item, int position);

        protected void onViewDetachedFromWindow() {

        }

        protected void onViewAttachedToWindow() {

        }
    }
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}