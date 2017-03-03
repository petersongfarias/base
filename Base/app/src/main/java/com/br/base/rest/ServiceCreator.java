package com.br.base.rest;

import com.br.base.BuildConfig;
import com.br.base.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peterson on 09/01/17.
 */

public class ServiceCreator {

    private static OkHttpClient httpClient = Client.getClient();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        final Gson gsonDateFormat = new GsonBuilder().setDateFormat(Constants.DATE_FORMATTER).create();
        Retrofit retrofit = builder
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gsonDateFormat))
                .build();
        return retrofit.create(serviceClass);
    }
}
