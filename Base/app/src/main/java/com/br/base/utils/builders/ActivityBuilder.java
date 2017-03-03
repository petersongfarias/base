package com.br.base.utils.builders;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.br.base.Base;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ActivityBuilder helps to build {@link Activity} {@link Intent} and start {@link Activity}.
 */
public class ActivityBuilder {

    final Intent intent;

    public <C extends Activity> ActivityBuilder(Class<C> clazz) {
        intent = new Intent(Base.getContext(), clazz);
    }

    public <T extends Serializable> ActivityBuilder set(String key, T value) {
        intent.putExtra(key, value);
        return this;
    }

    public ActivityBuilder set(String key, Parcelable value) {
        intent.putExtra(key, value);
        return this;
    }

    public ActivityBuilder set(String key, Parcelable[] value) {
        intent.putExtra(key, value);
        return this;
    }

    public <T extends Parcelable> ActivityBuilder set(String key, ArrayList<T> value) {
        intent.putExtra(key, value);
        return this;
    }

    public ActivityBuilder remove(String key) {
        intent.removeExtra(key);
        return this;
    }

    public ActivityBuilder setFlags(int flags) {
        intent.setFlags(flags);
        return this;
    }

    public ActivityBuilder addFlags(int flags) {
        intent.addFlags(flags);
        return this;
    }

    public Intent buildIntent() {
        return intent;
    }

    public void start() {
        Base.getContext().startActivity(intent);
    }

    public void startForResult(Activity activity, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
    }

    @TargetApi(16)
    public void startForResult(Activity activity, int requestCode, Bundle options) {
        activity.startActivityForResult(intent, requestCode, options);
    }
}
