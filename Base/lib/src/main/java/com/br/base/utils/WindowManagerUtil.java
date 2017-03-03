package com.br.base.utils;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * WindowManagerUtil helps to manage {@link WindowManager} conveniently.
 *
 * @author Leonardo Taehwan Kim
 */
public class WindowManagerUtil {

    public static Display getDefaultDisplay() {
        return ServiceUtil.getWindowManager().getDefaultDisplay();
    }

    public static DisplayMetrics getDisplayMetrics(){
        DisplayMetrics dm = new DisplayMetrics();
        ServiceUtil.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static void removeViewImmediate(View view) {
        ServiceUtil.getWindowManager().removeViewImmediate(view);
    }
}