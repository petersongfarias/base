package com.br.base;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by peterson on 09/01/17.
 */

public interface BaseView {

    /**
     * initialize toolbar
     */
    void setupToolbar();

    /**
     * Return context - Return @{@link android.app.Activity} preferentially
     *
     * @return Context
     */
     Context getContext();

    /**
     * Return default @{@link Resources}
     *
     * @return @{@link Resources}
     */
    Resources getResources();

}
