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


package br.com.pgf.androdbase.holder;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.com.pgf.androdbase.anim.Animations;
import br.com.pgf.androdbase.anim.BaseViewAnimator;
import br.com.pgf.androdbase.view.anotations.Bind;


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
   protected void findAllMappedChilds(final ViewGroup viewGroup) {
      try {
         final Map<Integer, TempField> mappedChilds = new HashMap<>();
         final Field[] fields = this.getClass().getDeclaredFields();
         for (int i = 0; i < fields.length; i++) {
            final Field field = fields[i];
            final Bind bind = field.getAnnotation(Bind.class);
            final TempField tempField = new TempField(field, bind);
            mappedChilds.put(bind.id(), tempField);
         }
         for (int childViewIndex = 0; childViewIndex < viewGroup.getChildCount(); childViewIndex++) {
            final View childView = viewGroup.getChildAt(childViewIndex);
            if (childView instanceof ViewGroup) {
               findAllMappedChilds((ViewGroup) childView);
               continue;
            }
            int id = childView.getId();
            if(mappedChilds.containsKey(id)){
               final TempField ttempField = mappedChilds.get(id);
               ttempField.field.set(this, childView);
               final Bind tbind = ttempField.bind;
               if(tbind.enableAnimation()){
                  animate(childView, childViewIndex, tbind.animation());
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

   class TempField{
      public Field field;
      public Bind bind;

      public TempField(final Field field, final Bind bind){
         this.field = field;
         this.bind = bind;
      }
   }

}
