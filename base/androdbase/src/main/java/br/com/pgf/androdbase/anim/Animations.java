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

package br.com.pgf.androdbase.anim;

/**
 * Created by peterson on 01/08/2016.
 */
public enum Animations {

   Pulse(PulseAnimator.class),
   Bounce(BounceAnimator.class),
   StandUp(StandUpAnimator.class),
   BounceIn(BounceInAnimator.class),
   BounceInDown(BounceInDownAnimator.class),
   BounceInLeft(BounceInLeftAnimator.class),
   BounceInRight(BounceInRightAnimator.class),
   BounceInUp(BounceInUpAnimator.class),
   FadeIn(FadeInAnimator.class),
   FadeInUp(FadeInUpAnimator.class),
   FadeInDown(FadeInDownAnimator.class),
   FadeInLeft(FadeInLeftAnimator.class),
   FadeInRight(FadeInRightAnimator.class),
   SlideInLeft(SlideInLeftAnimator.class),
   SlideInRight(SlideInRightAnimator.class),
   SlideInUp(SlideInUpAnimator.class),
   SlideInDown(SlideInDownAnimator.class),
   ZoomIn(ZoomInAnimator.class),
   ZoomInDown(ZoomInDownAnimator.class),
   ZoomInLeft(ZoomInLeftAnimator.class),
   ZoomInRight(ZoomInRightAnimator.class),
   ZoomInUp(ZoomInUpAnimator.class);

   private Class animatorClazz;

   Animations(Class clazz) {
      animatorClazz = clazz;
   }

   public BaseViewAnimator getAnimator() {
      try {
         return (BaseViewAnimator) animatorClazz.newInstance();
      } catch (Exception e) {
         throw new Error("Can not init animatorClazz instance");
      }
   }
}