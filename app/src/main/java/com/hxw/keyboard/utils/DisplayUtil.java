package com.hxw.keyboard.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class DisplayUtil {
    public static int dp2px(float dp, Resources resources) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static int dp2px(float dp) {
        Resources resources = AppGlobals.INSTANCE.get().getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static int sp2px(float dp) {
        Resources resources = AppGlobals.INSTANCE.get().getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, resources.getDisplayMetrics());
    }


    public static int getDisplayWidthInPx() {
        WindowManager wm = (WindowManager) AppGlobals.INSTANCE.get().getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display defaultDisplay = wm.getDefaultDisplay();
            return defaultDisplay.getWidth();
        }
        return 0;

    }

    public static int getDisplayHeightInPx() {
        WindowManager wm = (WindowManager) AppGlobals.INSTANCE.get().getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display defaultDisplay = wm.getDefaultDisplay();
            return defaultDisplay.getHeight();
        }
        return 0;
    }

    public static int getStatusBarDimensionPx() {
        int statusBarHeight = 0;
        Resources res = AppGlobals.INSTANCE.get().getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
