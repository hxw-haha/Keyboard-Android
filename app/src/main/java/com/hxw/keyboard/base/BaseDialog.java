package com.hxw.keyboard.base;

import android.app.Dialog;
import android.content.Context;
import android.util.SparseArray;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.hxw.keyboard.R;


/**
 * <p>文件描述：</p>
 * <p>作者：hanxw</p>
 * <p>创建时间：2021/3/24</p>
 * <p>更改时间：2021/3/24</p>
 * <p>版本号：1</p>
 */
public abstract class BaseDialog {

    protected final SparseArray<View> mViews;
    protected final Dialog mDialog;
    protected final View mRootView;
    protected final Display mDisplay;

    public BaseDialog(@NonNull Context context) {
        this(context, false);
    }

    /**
     * @param context
     * @param isClearFlags 是否清楚背景颜色
     */
    public BaseDialog(@NonNull Context context, boolean isClearFlags) {
        mRootView = LayoutInflater.from(context).inflate(layoutId(), null);
        mViews = new SparseArray<>();
        WindowManager windowManager = (WindowManager) mRootView.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();

        mDialog = new Dialog(mRootView.getContext(), R.style.DialogAnimationStyle);
        mDialog.setContentView(mRootView);
        final Window window = mDialog.getWindow();
        if (window != null) {
            if (isClearFlags) {
                //清除灰色背景
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
            final int gravity = getGravity();
            window.setGravity(gravity);
            final WindowManager.LayoutParams lp = window.getAttributes();
            if (gravity == Gravity.CENTER) {
                lp.width = (int) (mDisplay.getWidth() * 0.7f);
            } else {
                mRootView.setMinimumWidth(mDisplay.getWidth());
                lp.width = mDisplay.getWidth();
            }
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
    }

    @LayoutRes
    protected abstract int layoutId();

    /**
     * @return {@link Gravity}
     */
    public int getGravity() {
        return Gravity.CENTER;
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public void show() {
        mDialog.show();
    }

    /**
     * findViewById
     *
     * @param viewId 控件ID
     * @param <V>    extends View
     * @return View
     */
    public <V extends View> V findViewId(@IdRes int viewId) {
        V view = (V) mViews.get(viewId);
        if (view == null) {
            view = (V) mRootView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    /**
     * 显示 Toast
     *
     * @param msg 显示信息
     */
    public void showToast(@NonNull String msg) {
        Toast.makeText(mRootView.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
