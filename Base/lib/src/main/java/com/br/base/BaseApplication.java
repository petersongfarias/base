package com.br.base;

import android.app.Application;

/**
 * Created by peterson on 09/01/17.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Base.initialize(this);
    }

    public static final BaseApplication get(){
        return instance;
    }

}
