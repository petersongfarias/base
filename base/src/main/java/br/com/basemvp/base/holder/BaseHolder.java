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


package br.com.basemvp.base.holder;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.com.basemvp.base.anim.Animations;
import br.com.basemvp.base.anim.BaseViewAnimator;
import br.com.basemvp.base.view.anotations.Bind;

/**
 * Created by peterson on 01/08/2016.
 */
public class BaseHolder {

   private BaseViewAnimator mAnimator;

   /**
    * Helps to find recursivly all visible childs in a view group.
    *
    * @param viewGroup View group.
    */
   protected void findAllVisibleChilds(final ViewGroup viewGroup) {
      try {
         final Map<Integer, View> ordoredChilds = new HashMap<>();
         final Field[] fields = this.getClass().getDeclaredFields();
         for (int childViewIndex = 0; childViewIndex < viewGroup.getChildCount(); childViewIndex++) {
            final View childView = viewGroup.getChildAt(childViewIndex);
            if (childView instanceof ViewGroup) {
               findAllVisibleChilds((ViewGroup) childView);
               continue;
            }
            ordoredChilds.put(childView.getId(), childView);
         }
         for (int i = 0; i < fields.length; i++) {
            final Field field = fields[i];
            final Bind bind = field.getAnnotation(Bind.class);
            if (ordoredChilds.containsKey(bind.id())) {
               final View view = ordoredChilds.get(bind.id());
               field.setAccessible(true);
               field.set(field.getType(), ordoredChilds.get(bind.id()));
               if(bind.enableAnimation()){
                  animate(view, i, bind.animation());
               }
            }
         }
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
   }

   /**
    * start view animation
    *
    * @param child    view to animate
    * @param position view position on parent
    */
   protected void animate(final View child, final int position) {
      getAnimator()
            .setTarget(child)
            .setDuration(BaseViewAnimator.DURATION)
            .setInterpolator(new AccelerateDecelerateInterpolator())
            .setDelay(position * BaseViewAnimator.DELAY)
            .start();
   }

   protected void animate(final View child, final int position, final Animations typeAnim) {
      typeAnim.getAnimator()
            .setTarget(child)
            .setDuration(BaseViewAnimator.DURATION)
            .setInterpolator(new AccelerateDecelerateInterpolator())
            .setDelay(position * BaseViewAnimator.DELAY)
            .start();
   }

   /**
    * set Animations type
    *
    * @param animator Animations
    */
   public void setAnimator(Animations animator) {
      mAnimator = animator.getAnimator();
   }

   /**
    * Return BaseViewAnimator from Animations type
    *
    * @return BaseViewAnimator
    */
   public BaseViewAnimator getAnimator() {
      if (mAnimator == null) {
         mAnimator = Animations.FadeIn.getAnimator();
      }
      return mAnimator;
   }


}