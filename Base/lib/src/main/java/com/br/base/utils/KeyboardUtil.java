package com.br.base.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.br.base.Base;

/**
 * KeyboardUtil helps to show and hide keyboard conveniently.
 */
public class KeyboardUtil {

    /**
     * Helps to show keyboard in {@link Activity#onCreate(Bundle)}, {@link Activity#onStart()},
     * {@link Activity#onResume()},
     * {@link MenuItem.OnActionExpandListener#onMenuItemActionExpand(MenuItem)},
     * {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)} and etc
     * This method guarantee to show keyboard every time.
     */
    public static void show(final View view) {
        if (view == null)
            return;

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                showInMainThread(view);
            }
        }, 200);
    }

    /**
     * Please note that this method does not guarantee to show keyboard every time. To guarantee
     * to show keyboard, please use {@link #show(View)} instead. It doesn't have any delay, use
     * this method when it is able to show keyboard immediately. EX) when user click a button to
     * show keyboard
     */
    public static void showImmediately(final View view) {
        if (view == null)
            return;

        if (Base.isMain()) {
            showInMainThread(view);
        } else {
            view.post(new Runnable() {
                @Override
                public void run() {
                    showInMainThread(view);
                }
            });
        }
    }

    private static void showInMainThread(final View view) {
        if (view == null)
            return;

        view.requestFocus();
        ServiceUtil.getInputMethodManager().showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hide(Fragment fragment) {
        if (fragment == null || fragment.getActivity() == null)
            return;

        hide(fragment.getActivity());
    }

    public static void hide(Fragment fragment, boolean clearFocus) {
        if (fragment == null || fragment.getActivity() == null)
            return;

        hide(fragment.getActivity());
    }

    public static void hide(Activity activity) {
        hide(activity, true);
    }

    public static void hide(Activity activity, boolean clearFocus) {
        if (activity == null)
            return;

        hide(activity.getCurrentFocus(), clearFocus);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void hide(android.app.Fragment fragment) {
        hide(fragment, true);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void hide(android.app.Fragment fragment, boolean clearFocus) {
        if (fragment == null || fragment.getActivity() == null)
            return;

        hide(fragment.getActivity(), clearFocus);
    }

    public static void hide(Dialog dialog) {
        hide(dialog, true);
    }

    public static void hide(Dialog dialog, boolean clearFocus) {
        if (dialog == null)
            return;

        hide(dialog.getCurrentFocus(), clearFocus);
    }

    public static void hide(View view) {
        hide(view, true);
    }

    public static void hide(View view, boolean clearFocus) {
        if (view == null)
            return;

        if (clearFocus) {
            view.clearFocus();
        }

        ServiceUtil.getInputMethodManager().hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
