package com.haibuzou.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.haibuzou.myapplication.MainActivity;
import com.haibuzou.myapplication.R;
import com.haibuzou.myapplication.entity.ZhaoPin;
import com.haibuzou.myapplication.view.MyTextView;


public class CurrenPositionView extends LinearLayout {
    //每行item个数
    private final int ROW_NUM = 3;
    private TextView titleView;
    private ZhaoPin data;
    private TableLayout tableLayout;
    private LayoutInflater layoutInflater;
    private int viewId;

    public CurrenPositionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CurrenPositionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurrenPositionView(Context context) {
        super(context);
    }

    public ZhaoPin getData() {
        return data;
    }

    public void setData(ZhaoPin data) {
        this.data = data;
    }

    private void initView() {
        layoutInflater = LayoutInflater.from(getContext());
        View rootView = layoutInflater.inflate(R.layout.pulldown_view, this);
        titleView = (TextView) rootView.findViewById(R.id.job_name_text);
        tableLayout = (TableLayout) rootView.findViewById(R.id.job_name_table);
        tableLayout.setStretchAllColumns(true);
        setTitleStr(data.getName());
        //初始化表格数据
        int h = data.getJobtype().size() / ROW_NUM;
        int len = data.getJobtype().size() % ROW_NUM;
        int location = 0;
        if (len != 0)
            h += 1;
        for (int n = 1; n <= h; n++) {
            TableRow row = new TableRow(getContext());
            int length = n == h ? len : ROW_NUM;
            for (int m = 0; m < length; m++) {
                MyTextView mview = (MyTextView) layoutInflater.inflate(R.layout.table_row_item, null);
                mview.setText(data.getJobtype().get(location).getName());
                mview.setTag("" + viewId + location);
                mview.setOnClickListener(((MainActivity) getContext()).new OnItemClick(data, location, n, tableLayout, mview));
                row.addView(mview);
                location++;
            }
            // 每一列 tablerow 增加一个 下拉的layout
            View view = layoutInflater.inflate(R.layout.expand_item, null);
            view.setTag(n);
            view.setVisibility(View.GONE);
            tableLayout.addView(row);
            tableLayout.addView(view);
        }
    }

    public void init() {
        initView();
    }

    public void setTitleStr(CharSequence text) {
        titleView.setText(text);
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }
}
