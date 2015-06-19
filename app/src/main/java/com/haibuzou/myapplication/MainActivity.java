package com.haibuzou.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;


import com.haibuzou.myapplication.adapter.ExpandGridAdapter;
import com.haibuzou.myapplication.adapter.MyViewPagerAdapter;
import com.haibuzou.myapplication.entity.BaseData;
import com.haibuzou.myapplication.entity.ZhaoPin;
import com.haibuzou.myapplication.view.CurrenPositionView;
import com.haibuzou.myapplication.view.MyTextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements ExpandGridAdapter.OnClick {

    //招聘数据
    private List<ZhaoPin> data = null;
    private LinearLayout currenPositionLinear = null;
    private Integer duration = 200;
    private RelativeLayout rel;
    private String itemId = null;
    //记录点击位置
    private String clickPosition = "";
    private MyTextView clicktxt;
    //下拉的 圆点显示layout
    private LinearLayout circlelayout;
    private int lastlocation = -1;
    private ExpandGridAdapter.OnClick listener;
    //每行显示条目个数
    private final int NUM_LINE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        currenPositionLinear = (LinearLayout) findViewById(R.id.zp_curr_potion_linear);
        for (int num = 0; num < data.size(); num++) {
            ZhaoPin zhaoPin = data.get(num);
            CurrenPositionView currenPositionView = new CurrenPositionView(this);
            currenPositionView.setData(zhaoPin);
            currenPositionView.setViewId(num);
            currenPositionView.init();
            if (num > 0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                if (num == data.size() - 1) {
                    params.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
                }
                currenPositionLinear.addView(currenPositionView, params);
            } else {
                currenPositionLinear.addView(currenPositionView);
            }

        }
    }

    private void initData() {
        if (listener == null) {
            listener = this;
        }
        if (data == null) {
            data = Utils.getJobType(this);
        }
    }

    public class OnItemClick implements OnClickListener, OnPageChangeListener {
        private int position;
        private int tag;
        private MyTextView view;
        private TableLayout tableLayout;
        private ZhaoPin zhaoPin;

        public OnItemClick(ZhaoPin zhaoPin, int position, int tag, TableLayout table, MyTextView view) {
            this.position = position;
            this.tag = tag;
            this.view = view;
            this.tableLayout = table;
            this.zhaoPin = zhaoPin;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            if (lastlocation != arg0 && lastlocation != -1) {
                ImageView mimage = (ImageView) circlelayout
                        .findViewWithTag(lastlocation + 1 + 10);
                if (mimage != null) {
                    mimage.setImageResource(R.mipmap.black_circle);
                }
            }

            ImageView image = (ImageView) circlelayout
                    .findViewWithTag(arg0 + 1 + 10);
            if (image != null) {
                image.setImageResource(R.mipmap.orange_circle);
            }

            lastlocation = arg0;
        }

        @Override
        public void onPageSelected(int arg0) {

        }

        private void init(View v, int location) {
            List<BaseData> list = new ArrayList<>();
            ViewPager pager = (ViewPager) v.findViewById(R.id.expand_item);
            // 底部的圆点初始化
            circlelayout = (LinearLayout) v.findViewById(R.id.circle_layout);
            circlelayout.removeAllViews();
            int radio = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
            // 初始化一个params 用于圆点大小设置
            LinearLayout.LayoutParams circleparams = new LinearLayout.LayoutParams(radio, radio);
            circleparams.setMargins(0, 0, 10, 0);
            List<View> views = new ArrayList<>();
            // 下拉表格的行数
            int row = zhaoPin.getJobtype().get(location).getJobtype().size() / NUM_LINE;
            if (row > 4) {
                row = 4;
            } else if (row < 4) {
                if (zhaoPin.getJobtype().get(location).getJobtype().size() % NUM_LINE != 0) {
                    row += 1;
                }
            }
            // 计算生成 view的数量
            int i = zhaoPin.getJobtype().get(location).getJobtype().size() / 12;
            int len = zhaoPin.getJobtype().get(location).getJobtype().size() % 12;
            if (len > 0) {
                i += 1;
            }
            // View的 初始化
            for (int n = 1; n <= i; n++) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.viewpager, null);
                GridView grid = (GridView) view.findViewById(R.id.expand_grid);
                // 计算每个 gridview 存放多少数据
                if (i == 0) {
                    list = zhaoPin.getJobtype().get(location).getJobtype();
                } else if (n < i) {
                    // 每一个 表只有3列 最多 4行 每个表最多12个
                    list = zhaoPin.getJobtype().get(location).getJobtype().subList((n - 1) * 12, n * 12);
                } else {
                    int size = zhaoPin.getJobtype().get(location).getJobtype().size();
                    list = zhaoPin.getJobtype().get(location).getJobtype().subList((n - 1) * 12, size);
                }
                ExpandGridAdapter ea = new ExpandGridAdapter(MainActivity.this, list, R.layout.expand_grid_item);
                ea.setOnClick(listener);
                grid.setAdapter(ea);
                views.add(grid);
                if (i > 1) {
                    circlelayout.setVisibility(View.VISIBLE);
                    // viewpager 底部的 点标识
                    ImageView image = new ImageView(MainActivity.this);
                    image.setTag(n + 10);
                    image.setImageResource(R.mipmap.black_circle);
                    circlelayout.addView(image, circleparams);
                } else {
                    circlelayout.setVisibility(View.GONE);
                }
            }
            MyViewPagerAdapter mpa = new MyViewPagerAdapter(views);
            // 宽度每一个格高度 42dp 转成px
            int height = (int) (MainActivity.this.getResources().getDisplayMetrics().density * 42 + 0.5f);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, row * height);
            pager.setLayoutParams(params);
            pager.setAdapter(mpa);
            pager.setOnPageChangeListener(this);
        }

        // 收缩动画
        private void collapse(final View v) {
            final int initialHeight = v.getMeasuredHeight();
            Animation animation = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime,
                                                   Transformation t) {
                    // interpolatedTime 0-1 进行变化 为1的时候表示动画已经完成
                    if (interpolatedTime == 1) {
                        v.setVisibility(View.GONE);
                    } else {
                        // 同样的原理 让控件的高度不断变化
                        v.getLayoutParams().height = initialHeight
                                - (int) (initialHeight * interpolatedTime);
                        v.requestLayout();
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            animation.setDuration(duration);
            v.startAnimation(animation);
        }

        // 展开动画
        private void expand(final View v, int location) {
            init(v, location);
            v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            final int targetHeight = v.getMeasuredHeight();
            // 最开始显示下拉控件的时候 将高度设为0 达到不显示的效果
            v.getLayoutParams().height = 0;
            v.setVisibility(View.VISIBLE);

            Animation animation = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime,
                                                   Transformation t) {
                    // 不断变化高度来显示控件
                    v.getLayoutParams().height = (interpolatedTime == 1) ? RelativeLayout.LayoutParams.WRAP_CONTENT
                            : (int) (targetHeight * interpolatedTime);
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            animation.setDuration(duration);
            v.startAnimation(animation);
        }

        @Override
        public void onClick(View v) {
            String tempTag = (String) v.getTag();
            if (rel == null) {
                rel = (RelativeLayout) tableLayout.findViewWithTag(tag);
                expand(rel, position);
                view.isDraw(true);
            } else {
                if (rel.getVisibility() == View.VISIBLE) {
                    collapse(rel);
                    view.isDraw(false);
                } else {
                    if (tempTag.equals(clickPosition)) {
                        expand(rel, position);
                    }
                    view.isDraw(true);
                }

                if (!tempTag.equals(clickPosition)) {
                    rel = (RelativeLayout) tableLayout.findViewWithTag(tag);
                    expand(rel, position);
                    // 上一次的箭头去除
                    clicktxt.isDraw(false);
                    view.isDraw(true);
                }
            }
            clickPosition = tempTag;
            clicktxt = view;
        }
    }

    @Override
    public void onClick(int dataId, String str) {
        Intent intent = new Intent();
        intent.putExtra("id", dataId);
        intent.putExtra("value", str);
        setResult(RESULT_OK, intent);
        finish();
    }

}
