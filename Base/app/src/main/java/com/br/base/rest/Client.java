package com.br.base.rest;

import com.br.base.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by peterson on 09/01/17.
 */

public final class Client {
    private static final int MINUTE = 60;
    private static final int MAX_AGE = MINUTE;
    private static final int MAX_STALE = 60 * 60 * 24;
    private static final int CACHE_SIZE = 2 * 1024 * 1024; // 2 MiB

    public static final OkHttpClient getClient() {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(HTTP_LOGGING_INTERCEPTOR())
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .connectTimeout(MINUTE, TimeUnit.SECONDS)
                .readTimeout(MINUTE, TimeUnit.SECONDS)
                .cache(setupCache())
                .build();
    }

    private static final HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR() {
        final HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return mLogging;
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", String.format("max-age=%d, only-if-cached, max-stale=%d", MAX_AGE, MAX_STALE))
                    .build();
        }
    };

    private static final Cache setupCache(){
        final File httpCacheDirectory = new File(BaseApplication.get().getCacheDir(), "responses");
        return new Cache(httpCacheDirectory, CACHE_SIZE);
    }

}
