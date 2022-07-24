package com.example.dialogfragment.top;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ApplicationUtil {

    // 用于获取屏幕宽高
    private static DisplayMetrics displayMetrics = new DisplayMetrics();

    /**
     * 返回屏幕宽(px)
     */
    public static int getScreenWidth(Activity activity) {
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 返回屏幕高(px)
     */
    public static int getScreenHeight(Activity activity) {
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


}
