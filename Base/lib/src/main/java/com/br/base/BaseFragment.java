package com.br.base;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by peterson on 09/01/17.
 */

public abstract class BaseFragment<M, V extends BaseView, P extends Presenter<M, V>> extends Fragment {

    private Delegate<M, V, P> mvpDelegate =
            new Delegate<M, V, P>(createPresentationModelSerializer()) {
                @NonNull
                @Override
                protected P createPresenter() {
                    return BaseFragment.this.createPresenter();
                }

                @NonNull
                @Override
                protected M createPresentationModel() {
                    return BaseFragment.this.createPresentationModel();
                }
            };


    /**
     * instance of FragmentManager
     */
    protected FragmentManager mFragmentManager;


    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getActivity().getSupportFragmentManager();
        mvpDelegate.onCreate(getLayoutId(), savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mvpDelegate.onStart((V) this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mvpDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        mvpDelegate.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mvpDelegate.onDestroy();
    }

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

    @NonNull
    protected P getPresenter() {
        return mvpDelegate.getPresenter();
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
     * You can retrieve the arguments from #getArguments() method of your
     * fragment and pass it to your Presentation's model constructor.
     *
     * @return Presentation Model instance used by your Presenter.
     */
    @NonNull
    protected abstract M createPresentationModel();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public abstract
    @LayoutRes
    int getLayoutId();


    /**
     * Replace content
     *
     * @param fragment       Component to replace
     * @param content        parent that will receive the fragment
     * @param tag            identifier fragment
     * @param addToBackStack flag to notify if fragment will added on back stack
     * @return true if replace content success of false
     */
    protected boolean replaceContent(Fragment fragment, final int content, String tag, boolean addToBackStack) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).replaceContent(fragment, content, tag, addToBackStack);
            return true;
        } else {
            return false;
        }
    }


}
