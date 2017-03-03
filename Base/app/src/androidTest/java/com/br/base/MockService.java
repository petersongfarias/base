package com.br.base;

import com.br.base.api.ComicBookService;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peterson on 09/01/17.
 */

public class MockService {

    private MockWebServer mMockWebServer;

    public final ComicBookService provideComicBookService(final OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(mMockWebServer.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ComicBookService.class);
    }

    public MockWebServer getMockWebServer() {
        return mMockWebServer;
    }


    public final void setUp() {
        mMockWebServer = new MockWebServer();
    }


}
