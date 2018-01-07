package rc.loveq.baselib.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by rc on 2017/12/26.
 * Description:
 */

public class CustomPopupWindow {
    private PopupWindow mPopupWindow;
    private View mContentview;
    private SparseArray<View> mViews;

    private CustomPopupWindow(Builder builder) {
        mContentview = LayoutInflater.from(builder.mContext).inflate(builder.mContentViewId, null);
        mPopupWindow =
                new PopupWindow(mContentview, builder.mWidth, builder.mHeight, builder.mFocusable);
        //需要跟 setBackGroundDrawable 结合
        mPopupWindow.setOutsideTouchable(builder.mOutsideCancel);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(builder.mAnimStyle);
        mViews = new SparseArray<View>();
    }

    /**
     * popup 消失
     */
    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mContentview.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public CustomPopupWindow setText(@IdRes int viewId, Spanned content) {
        TextView textView = getView(viewId);
        textView.setText(content);
        return this;
    }

    public CustomPopupWindow setText(@IdRes int viewId, String content) {
        TextView textView = getView(viewId);
        textView.setText(content);
        return this;
    }

    public CustomPopupWindow setOnClickListener(@IdRes int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public void showAsDropDown(View targetView) {
        showAsDropDown(targetView, 0, 0);
    }

    public void showAsDropDown(View targetView, int xoff, int yoff) {
        if (mPopupWindow == null) {
            throw new NullPointerException("没有初始化PopupWindow");
        }
        if (!mPopupWindow.isShowing()) {
            mPopupWindow.showAsDropDown(targetView, xoff, yoff);
        }
    }

    public int getMeasuredWidth() {
        mPopupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return mContentview.getMeasuredWidth();
    }

    public int getMeasuredHeight() {
        mPopupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return mContentview.getMeasuredHeight();
    }

    /**
     * builder 类
     */
    public static class Builder {
        private int mContentViewId = -1;
        private int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        private int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        private boolean mFocusable = true;
        private boolean mOutsideCancel = true;
        private int mAnimStyle = -1;
        private Context mContext;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setContentView(@LayoutRes int contentViewId) {
            this.mContentViewId = contentViewId;
            return this;
        }

        public Builder setWidth(int width) {
            this.mWidth = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.mHeight = height;
            return this;
        }

        public Builder setFocusable(boolean focusable) {
            this.mFocusable = focusable;
            return this;
        }

        public Builder setOutSideCancel(boolean outSideCancel) {
            this.mOutsideCancel = outSideCancel;
            return this;
        }

        public Builder setAnimationStyle(int animationStyle) {
            this.mAnimStyle = animationStyle;
            return this;
        }

        public CustomPopupWindow builder() {
            return new CustomPopupWindow(this);
        }
    }
}
