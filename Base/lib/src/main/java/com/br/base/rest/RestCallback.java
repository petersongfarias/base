package com.br.base.rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by peterson on 09/01/17.
 */

public abstract class RestCallback<T extends Object> implements Callback<T> {

    public abstract void onSuccess(T response);

    public abstract void onError(ResponseError error);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            final ResponseError error = ResponseError.getResponError(response.errorBody());
            onError(error);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(ResponseError.getGenericException());
    }
}

