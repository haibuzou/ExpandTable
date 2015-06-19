package com.haibuzou.myapplication.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.haibuzou.myapplication.R;
import com.haibuzou.myapplication.entity.BaseData;

/**
 * Created by AAA on 2015/6/16.
 */
public class ExpandGridAdapter extends CommonAdapter<BaseData> {
    private OnClick listener;

    public ExpandGridAdapter(Context context, List<BaseData> mDatas,
                             int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, final BaseData item) {
        helper.setText(R.id.expand_grid_txt, item.getName());
        View view = helper.getView(R.id.expand_grid_txt);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(item.getDataId(), item.getName());
                }

            }
        });
    }

    public interface OnClick {
        void onClick(int dataId, String str);
    }

    public void setOnClick(OnClick listener) {
        this.listener = listener;
    }
}
