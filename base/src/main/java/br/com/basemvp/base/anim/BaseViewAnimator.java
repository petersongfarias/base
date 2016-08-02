/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Peterson Farias
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package br.com.basemvp.base.anim;

import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * Created by peterson on 01/08/2016.
 */
public abstract class BaseViewAnimator {

    public static final long DURATION = 120;
    public static final long DELAY = DURATION + 1;

    private AnimatorSet mAnimatorSet;
    private long mDuration = DURATION;
    private long mDelay = DELAY;

    {
        mAnimatorSet = new AnimatorSet();
    }


   protected abstract void prepare(View target);

    public BaseViewAnimator setTarget(View target) {
        prepare(target);
        return this;
    }

    /**
     * start to animate
     */
    public void start() {
        mAnimatorSet.setStartDelay(mDelay);
        mAnimatorSet.setDuration(mDuration);
        mAnimatorSet.start();
    }

    public BaseViewAnimator setDuration(long duration) {
        mDuration = duration;
        return this;
    }

    public BaseViewAnimator setDelay(long delay) {
        mDelay = delay;
        return this;
    }

    public void cancel(){
        mAnimatorSet.cancel();
    }

    public boolean isRunning(){
        return mAnimatorSet.isRunning();
    }

    public boolean isStarted(){
        return mAnimatorSet.isStarted();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public boolean isPaused(){
        return mAnimatorSet.isPaused();
    }

    public BaseViewAnimator setInterpolator(Interpolator interpolator) {
        mAnimatorSet.setInterpolator(interpolator);
        return this;
    }

    public long getDuration() {
        return mDuration;
    }

    public AnimatorSet getAnimatorAgent() {
        return mAnimatorSet;
    }

}
