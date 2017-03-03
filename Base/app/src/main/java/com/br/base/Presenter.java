package com.br.base;


import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Created by peterson on 09/01/17.
 */

public abstract class Presenter<M, V extends BaseView> {

    private WeakReference<V> mView;

    private WeakReference<M> mPresentationModel;

    protected AtomicBoolean isViewAlive = new AtomicBoolean();

    public V getView() {
        return mView.get();
    }

    public M getPresentationModel() {
        return this.mPresentationModel.get();
    }

    public void attachView(V view, M presentationModel) {
        this.mView = new WeakReference<>(view);
        this.mPresentationModel = new WeakReference<M>(presentationModel);
    }

    public void detachView() {
        if (mView != null) {
            mView.clear();
        }
    }

    public void start() {
        isViewAlive.set(true);
    }

    public void pause() {
        isViewAlive.set(false);
    }


}
