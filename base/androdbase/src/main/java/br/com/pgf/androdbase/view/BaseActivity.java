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


package br.com.pgf.androdbase.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

import br.com.pgf.androdbase.R;
import br.com.pgf.androdbase.holder.ActivityHolder;
import br.com.pgf.androdbase.view.widget.RevealBackgroundRelativeLayout;

/**
 * Created by peterson on 01/08/2016.
 */
public abstract class BaseActivity<H extends ActivityHolder> extends AppCompatActivity implements ActivityView {

   /**
    * generic holder
    */
   protected H holder;
   /**
    * default progress dialog
    */
   protected ProgressDialog progressDialog;

   @Override
   protected void onPostCreate(@Nullable Bundle savedInstanceState) {
      super.onPostCreate(savedInstanceState);
      if (holder == null) {
         mapUI();
      }
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
   public void replaceContent(Fragment fragment, final int content, String tag, boolean addToBackStack) {
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
               .commit();
      }
   }

   /**
    * show progress dialog on execute actions
    *
    * @param msg message to show on progress dialog
    */
   protected void showProgressDialog(final String msg) {
      progressDialog = new ProgressDialog(this);
      progressDialog.setIndeterminate(true);
      progressDialog.setCancelable(false);
      if (msg != null) {
         progressDialog.setMessage(msg);
      }
      progressDialog.show();
   }

   /**
    * dismiss progress dialog
    */
   protected void dismissProgressDialog() {
      if (progressDialog != null) {
         progressDialog.dismiss();
         progressDialog = null;
      }
   }

   protected void startRevelAnimation(View root, final RevealBackgroundRelativeLayout view, final int x, final int y, final float startRadius) {
      root.post(new Runnable() {
                   @Override
                   public void run() {
                      float radius = (float) Math.hypot(view.getRight(), view.getBottom());
                      Animator retAnimator;
                      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                         retAnimator = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, radius);
                      } else {
                         view.setClipOutLines(true);
                         view.setClipCenter(x, y);
                         view.setClipRadius(startRadius);
                         retAnimator = ObjectAnimator.ofFloat(view, "clipRadius", startRadius, radius);
                      }
                      retAnimator.setDuration(500);
                      retAnimator.addListener(new Animator.AnimatorListener() {
                         @Override
                         public void onAnimationStart(Animator animation) {

                         }

                         @Override
                         public void onAnimationEnd(Animator animation) {
                            view.setClipOutLines(false);

                         }

                         @Override
                         public void onAnimationCancel(Animator animation) {

                         }

                         @Override
                         public void onAnimationRepeat(Animator animation) {

                         }
                      });
                      retAnimator.setInterpolator(new AccelerateInterpolator(2.0f));
                      retAnimator.start();
                   }
                }
      );
   }


}
