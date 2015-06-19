package com.haibuzou.myapplication.adapter;

import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private final SparseArray<SoftReference<View>> mViews;
    private final SparseArray<SoftReference<View>> childViews;
    private int mPosition;
    private View mConvertView;
    private ViewGroup parent;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mPosition = position;
        this.parent = parent;
        this.mViews = new SparseArray<SoftReference<View>>();
        this.childViews = new SparseArray<SoftReference<View>>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    private ViewHolder(Context context, ViewGroup parent, View view,
                       int position) {
        this.mPosition = position;
        this.parent = parent;
        this.mViews = new SparseArray<SoftReference<View>>();
        this.childViews = new SparseArray<SoftReference<View>>();
        mConvertView = view;
        // setTag
        mConvertView.setTag(this);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView item view
     * @param parent      父控件
     * @param layoutId    布局id
     * @param position    位置
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder(context, parent, layoutId, position);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
        }
        return holder;
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent      母控件
     * @param position    点击位置
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, Class<? extends View> view, int position) throws Exception {
        ViewHolder holder = null;
        if (convertView == null) {
            Constructor<?> constructor = view.getConstructor(Context.class);
            View contentView = (View) constructor.newInstance(context);
            holder = new ViewHolder(context, parent, contentView, position);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
        }
        return holder;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId) != null ? mViews.get(viewId).get() : null;
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, new SoftReference<View>(view));
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 临时创建view，但不显示
     *
     * @param viewId    view的 id
     * @param childView 子view
     */
    public void setChildView(int viewId, View childView) {
        if (getView(viewId) == null) {
            return;
        }
        childViews.put(viewId, new SoftReference<View>(childView));
    }

    /**
     * 获取子view
     *
     * @param viewId
     */
    public <T extends View> T getChildView(int viewId) {
        View view = childViews.get(viewId) != null ? childViews.get(viewId).get() : null;
        return (T) view;
    }

    public ViewGroup getParent() {
        return parent;
    }

    public int getPosition() {
        return mPosition;
    }

}
