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


package br.com.basemvp.base;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by peterson on 01/08/2016.
 */
public abstract class Presenter<M , V> {

   /**
    * view reference
    */
   private SoftReference viewReference;
   /**
    * models reference
    */
   private SoftReference modelReference;

   /**
    * @param model baseModel instance
    */
   public final void setModel(final M model) {
      if (model == null) {
         throw new NullPointerException("model is null, check it");
      }
      modelReference = new SoftReference(model);
   }

   /**
    * @return BaseModel
    */
   public final M getModel() {
      final M model = (M) modelReference.get();
      if (model == null) {
         throw new NullPointerException("model not found, invalid call");
      }
      return model;
   }

   /**
    * clear model list
    */
   public final void clearModels() {
      if (modelReference != null) {
         modelReference.clear();
      }
   }

   /**
    * this method attach baseView on presenter
    *
    * @param v ActivityView instance
    */
   public final <V> void attachView(V v) {
      if (v == null) {
         throw new NullPointerException("view is null, check it");
      }
      this.viewReference = new SoftReference<>(v);
   }

   /**
    * this method detach baseView from presenter
    */
   public final void detachView() {
      this.viewReference.clear();
   }

   /**
    * Return a baseView attached on presenter
    *
    * @return ActivityView
    */
   public final V getView() {
      return (V) viewReference.get();
   }

   /**
    * destroy objects instances of the presenter
    */
   public final void destroyPresenter() {
      clearModels();
      detachView();
   }

}
