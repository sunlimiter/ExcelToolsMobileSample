package com.tywho.exceltoolsmobile.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tywho.exceltoolsmobile.R;
import com.tywho.exceltoolsmobile.bean.QuestionContent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * http://www.cgw360.com
 *
 * @author：litianyi
 * @create：2016-10-18 15:03
 */
public class QuestionAdapter extends ListDataAdapter<QuestionContent> {


    public QuestionAdapter() {
    }

    @Override
    public AbsItemViewHolder onCreateViewHolderHF(ViewGroup viewGroup, int type) {
        return new ItemViewHolder(inflate(R.layout.item_content, viewGroup));
    }

    public class ItemViewHolder extends AbsItemViewHolder {
        @Bind(R.id.item_tv)
        TextView itemTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(QuestionContent questionContent, final int position) {
            itemTv.setText(questionContent.getContent());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickLitener != null) {
                        mOnItemClickLitener.onItemClick(view, position);
                    }
                }
            });
        }
    }
}