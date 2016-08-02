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



package br.com.pgf.androdbase.view.interact;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by peterson on 01/08/2016.
 */
public interface ActivityInteract {

    /**
     * @see Activity#getApplication()
     */
    Application getApplication();

    /**
     * @see Activity#getApplicationContext()
     */
    Context getApplicationContext();

    /**
     * @see Activity#getApplicationInfo()
     */
    ApplicationInfo getApplicationInfo();

    /**
     * @see Activity#getAssets()
     */
    AssetManager getAssets();

    /**
     * @see Activity#getBaseContext()
     */
    Context getBaseContext();

    /**
     * @see Activity#getContentResolver()
     */
    ContentResolver getContentResolver();

    /**
     * @see Activity#getLayoutInflater()
     */
    LayoutInflater getLayoutInflater();

    /**
     * @see Activity#getResources()
     */
    Resources getResources();

    /**
     * @see Activity#findViewById(int)
    */
    View findViewById(@IdRes int id);

}
