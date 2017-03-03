package com.br.base;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * Created by peterson on 23/02/17.
 */

public abstract class BaseBindActivity<M, V extends BaseView, P extends Presenter, B extends ViewDataBinding> extends AppCompatActivity {

    private Delegate<M, V, P> mvpDelegate =
            new Delegate<M, V, P>(createPresentationModelSerializer()) {
                @NonNull
                @Override
                protected P createPresenter() {
                    return BaseBindActivity.this.createPresenter();
                }

                @NonNull
                @Override
                protected M createPresentationModel() {
                    return BaseBindActivity.this.createPresentationModel();
                }
            };

    /***
     * Binding activity class generated by Android
     */
    public WeakReference<? extends ViewDataBinding> mBind;

    protected void putBind(B binding) {
        mBind = new WeakReference<>(binding);
    }

    protected B getBind() {
        return mBind == null ? null : (B) mBind.get();
    }

    /**
     * default progress dialog
     */
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        putBind((B) DataBindingUtil.setContentView(this, getLayoutId()));
        mvpDelegate.onCreate(this.getLayoutId(), savedInstanceState);
        getBind().setVariable(data(), mvpDelegate.getPresenter().getPresentationModel());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mvpDelegate.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpDelegate.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mvpDelegate.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mvpDelegate.onStart((V) this);
    }

    /**
     * Replace content
     *
     * @param fragment       Component to replace
     * @param content        parent that will receive the fragment
     * @param tag            identifier fragment
     * @param addToBackStack flag to notify if fragment will added on back stack
     * @return true if replace content success of false
     */
    public final void replaceContent(Fragment fragment, final int content, String tag, boolean addToBackStack) {
        if (addToBackStack) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right)
                    .replace(content, fragment, tag)
                    .addToBackStack(tag)
                    .commitAllowingStateLoss();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right)
                    .replace(content, fragment, tag)
                    .commitAllowingStateLoss();
        }
    }

    @NonNull
    protected P getPresenter() {
        return mvpDelegate.getPresenter();
    }

    /**
     * remove all fragments from backstack
     */
    public final void clearBackstack() {
        final int count = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    /**
     * show progress dialog on execute actions
     *
     * @param msg message to show on progress dialog
     */
    public final void showProgressDialog(final String msg) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        if (msg != null) {
            mProgressDialog.setMessage(msg);
        }
        mProgressDialog.show();
    }

    /**
     * show progress dialog on execute actions
     *
     * @param msg message to show on progress dialog
     */
    public final void showProgressDialog(final @StringRes int msg) {
        showProgressDialog(getString(msg));
    }

    /**
     * dismiss progress dialog
     */
    public final void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * change statusBar color if build version is greater Lollipop
     *
     * @param color statusBar color
     */
    public final void setStatusBarColor(@ColorRes final int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, color));
        }
    }


    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Feel free to override this method that returns your own implementation of
     * PresentationModelSerializer.
     * Useful if you use a Parceler library for example
     *
     * @return an instance of PresentationModelSerializer that will serialize and deserialize your
     * PresentationModel from Bundle.
     */
    protected PresentationModelSerializer<M> createPresentationModelSerializer() {
        return new ParcelableAndSerializablePresentationModelSerializer<>();
    }


    /**
     * Used for creating the presenter instance, called in #onCreate(Bundle) method.
     *
     * @return an instance of your Presenter.
     */
    @NonNull
    protected abstract P createPresenter();

    /**
     * Used to create the Presentation Model that will be attached to your presenter in #onAttach()
     * method of your presenter.
     * <p>
     * NOTE: this will be called only if there is no Presentation Model persisted in your
     * savedInstanceState!
     * <p>
     * You can retrieve the arguments from your Intent's extra and pass it
     * to your Presentation's model constructor.
     *
     * @return Presentation Model instance used by your Presenter.
     */
    @NonNull
    protected abstract M createPresentationModel();

    /**
     * The int value found in the BR class generated by DataBinding
     *
     * @return
     */
    public abstract int data();


}