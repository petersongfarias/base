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

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.List;

import br.com.basemvp.base.view.anotations.Bind;
import br.com.basemvp.base.view.interact.ActivityInteract;

public abstract class ActivityHolder extends BaseHolder {

   protected final ActivityInteract interact;

   /**
    * Default constructor to bind views
    * @param interact ActivityInteract
    */
   public ActivityHolder(final ActivityInteract interact) {
      this.interact = interact;
      findViews();
   }

   /**
    * find all layouts views
    */
   protected abstract void findViews();

   /**
    * Look for a child view with the given id.  If this view has the given
    * id, return this view.
    *
    * @param id The id to search for.
    * @return The view that has the given id in the hierarchy or null
    */
   protected View findViewById(final @NonNull @IdRes int id) {
      return interact.findViewById(id);

   }

   /**
    * Return main layout
    * @return ViewGroup
    */
   protected ViewGroup getViewGroup(){
      final ViewGroup viewGroup = (ViewGroup) ((ViewGroup)
            findViewById(android.R.id.content)).getChildAt(0);
      return viewGroup;
   }

}
