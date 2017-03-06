/*
 * Copyright (C) 2016 Appflate.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.br.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Class that makes it possible to write your own custom MVP Views that will fit in the DroidMVP
 * structure.
 * It is tightly coupled with the common android component's lifecycle, like onCreate, onStart,
 * onStop,
 * onSaveInstanceState and onDestroy to properly recover the model on configuration changes and
 * making sure
 * your presenters won't leak your views when those are supposed to be destroyed.
 */
public abstract class Delegate<M, V extends BaseView, P extends Presenter> {

    private P mPresenter;
    private M mPresentationModel;
    private final PresentationModelSerializer<M> mSerializer;

    private String mPresentationModelKey;

    public Delegate(@NonNull PresentationModelSerializer<M> serializer) {
        this.mSerializer = serializer;
    }

    /**
     * Commonly called from fragment's/activity's onCreate. Presentation Model is either created or
     * restored
     * here.
     *
     * @param savedInstanceState instanceState provided by android framework in which we store the
     *                           Presentation Model
     */
    public void onCreate(int viewId, @Nullable Bundle savedInstanceState) {
        mPresentationModelKey = viewId + "$PresentationModel";
        mPresentationModel =
                mSerializer.restorePresentationModel(savedInstanceState, mPresentationModelKey);
        if (mPresentationModel == null) {
            mPresentationModel = createPresentationModel();
        }
        this.mPresenter = createPresenter();
    }

    /**
     * Commonly called from fragment's/activity's onStart. Used for attaching the view to
     * mPresenter,
     * so the mPresenter can start to update the view's state
     */
    public void onStart(V mvpView) {
        checkPresenter();
        checkPresentationModel();
        mPresenter.attachView(mvpView, mPresentationModel);
        mPresenter.start();
    }

    /**
     * Commonly called from fragment's/activity's onStop. Used for detaching the view from
     * mPresenter, so no
     * memory leaks happen.
     */
    public void onStop() {
        checkPresenter();
        mPresenter.detachView();
        mPresenter.pause();
    }

    /**
     * Commonly called from fragment's/activity's onDestroy. Used to notify the mPresenter that the
     * view
     * will no longer be attached to the mPresenter, so all the long running tasks have to be
     * terminated
     * and the context should be cleared.
     */
    public void onDestroy() {
        checkPresenter();
        mPresenter.detachView();
        mPresenter.pause();
    }

    /**
     * Used by the delegate to persist current Presentation Model due to configuration change.
     */
    public void onSaveInstanceState(
            Bundle outState) {
        mSerializer.savePresentationModel(outState, mPresentationModelKey, mPresentationModel);
    }

    /**
     * This method return default presenter
     *
     * @return Presenter
     */
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * create default view presenter
     *
     * @return P Presenter
     */
    @NonNull
    protected abstract P createPresenter();

    /**
     * create presentation model for view
     *
     * @return M PresentationModel
     */
    @NonNull
    protected abstract M createPresentationModel();

    /**
     * Verify presenter instance
     */
    private void checkPresenter() {
        if (mPresenter == null) {
            throw new IllegalStateException(
                    "call onCreate in Delegate, because mPresenter is missing");
        }
    }

    /**
     * verify PresentationModel instance
     */
    private void checkPresentationModel() {
        if (mPresentationModel == null) {
            throw new IllegalStateException(
                    "seems like you forgot to create mPresentationModel in #createPresentationModel() method, or call the #onCreate() of this delegate");
        }
    }
}