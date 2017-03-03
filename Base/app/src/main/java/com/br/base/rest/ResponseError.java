package com.br.base.rest;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by peterson on 09/01/17.
 */

public class ResponseError {

    public static final String GENERIC_EXCEPTION = "GENERIC_EXCEPTION";
    private static final String GENERIC_EXCEPTION_VALUE = "Não foi possivel conectar ao serviço.";
    @SerializedName("code")
    @Expose
    private String mCode;
    @SerializedName("message")
    @Expose
    private String mMessage;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public static final ResponseError getResponError(final ResponseBody responseBody) {
        ResponseError error;
        try {
            error = new Gson().fromJson(responseBody.string(), ResponseError.class);
        } catch (IOException e) {
            error = getGenericException();
        }
        return error;
    }

    public static final ResponseError getGenericException() {
        final ResponseError error = new ResponseError();
        error.setCode(GENERIC_EXCEPTION);
        error.setMessage(GENERIC_EXCEPTION_VALUE);
        return error;
    }
}
