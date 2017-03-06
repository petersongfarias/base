package com.br.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Base helps to get {@link Context}, {@link Resources}, {@link AssetManager}, {@link Configuration} and {@link DisplayMetrics} in any class.
 */
public final class Base {

    private static Context context;
    private static MicroBus microBus;
    private static Base base;

    private Base(final Context context) {
        Base.context = context;
        microBus = new MicroBus();
    }

    public static void initialize(@NonNull Context context) {
        if (base == null) {
            base = new Base(context);
        }
    }

    public static Context getContext() {
        synchronized (Base.class) {
            if (context == null)
                throw new NullPointerException("Call Base.initialize(context) within your Application onCreate() method.");

            return context.getApplicationContext();
        }
    }

    public static final MicroBus getBus() {
        return microBus;
    }

    public static Resources getResources() {
        return Base.getContext().getResources();
    }

    public static Resources.Theme getTheme() {
        return Base.getContext().getTheme();
    }

    public static AssetManager getAssets() {
        return Base.getContext().getAssets();
    }

    public static Configuration getConfiguration() {
        return Base.getResources().getConfiguration();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return Base.getResources().getDisplayMetrics();
    }

    public static boolean isMain() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    static class MicroBus {


        public interface BusEventReceiver {
            void onBusEvent(Object event);
        }

        private int priority = Thread.NORM_PRIORITY;
        private final HashMap<Class<?>, ArrayList<BusEventReceiver>> receivers = new HashMap<>();

        public final void register(BusEventReceiver receiver, Class<?> cls) {
            synchronized (receivers){
                ArrayList<BusEventReceiver> list = receivers.get(cls);
                if (list == null)
                    receivers.put(cls, list = new ArrayList<BusEventReceiver>());

                list.add(receiver);
            }
        }

        public final void unregister(BusEventReceiver receiver, Class<?> cls) {
            synchronized (receivers){
                ArrayList<BusEventReceiver> list = receivers.get(cls);

                if (list != null){
                    list.remove(receiver);
                }
            }
        }

        public void post(Object event) {
            ArrayList<BusEventReceiver> list = receivers.get(event.getClass());
            if (list != null) {
                for (BusEventReceiver receiver : (ArrayList<BusEventReceiver>) list.clone())
                    receiver.onBusEvent(event);
            }
        }

        public final void postAsync(final Object event) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            };

            final Thread thread = new Thread(runnable);
            thread.setPriority(priority);
            thread.start();
        }
    }
}