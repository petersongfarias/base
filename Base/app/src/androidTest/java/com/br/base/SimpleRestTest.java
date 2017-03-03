package com.br.base;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.br.base.api.ComicBookService;
import com.br.base.model.ComicBook;
import com.br.base.rest.Client;
import com.br.base.utils.FileUtils;

import org.frutilla.FrutillaTestRunner;
import org.frutilla.annotations.Frutilla;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by peterson on 09/01/17.
 */

@RunWith(FrutillaTestRunner.class)
public class SimpleRestTest {

    private MockWebServer mMockWebServer;
    private Context mContextTarget;
    private MockService mMockService;
    private Resources mResources;

    @Before
    public void setUp() throws Exception {
        mContextTarget = InstrumentationRegistry.getTargetContext();
        mMockService = new MockService();
        mMockService.setUp();
        mResources = mContextTarget.getResources();
        mMockWebServer = mMockService.getMockWebServer();
    }

    @Frutilla(
            Given = "Call Marvel service",
            When = "Execute call on service",
            Then = "It should give me a list of comic books"
    )
    @Test
    public void test_CallSyncComicBooks_OfTheHeroSelected_ReturnsComicBookList() throws IOException {
        ComicBookService comicBookService;
        Given:
        {
            final InputStream inputStream = mResources.openRawResource(R.raw.comic_books);
            final String response = FileUtils.inputStreamToString(inputStream);
            final MockResponse mockResponse = new MockResponse().setResponseCode(200);
            mockResponse.setBody(response);
            mMockWebServer.enqueue(mockResponse);
            try {
                mMockWebServer.start(4000);
            } catch (@NonNull final Exception loException) {
                loException.printStackTrace();
            }
            comicBookService = mMockService.provideComicBookService(Client.getClient());
        }

        Response<ComicBook> response;
        When:
        {
            response = comicBookService
                    .findComicBooks("1009610", 1, BuildConfig.API_KEY, BuildConfig.API_HASH)
                    .execute();
        }

        Then:
        {
            final ComicBook comicBook = response.body();
            assertThat(comicBook).isNotNull();
            assertThat(comicBook.mData.mCount).isGreaterThan(0L);
        }
    }


    @Frutilla(
            Given = "Call Marvel service",
            When = "Execute call on service and not found results",
            Then = "It should give me a empty list"
    )
    @Test
    public void test_CallSyncComicBooks_AndNotFoundResults_ReturnsEmptyList() throws IOException {
        ComicBookService comicBookService;
        Given:
        {
            final InputStream inputStream = mResources.openRawResource(R.raw.comic_books_empty);
            final String response = FileUtils.inputStreamToString(inputStream);
            final MockResponse mockResponse = new MockResponse().setResponseCode(200);
            mockResponse.setBody(response);
            mMockWebServer.enqueue(mockResponse);
            try {
                mMockWebServer.start(3000);
            } catch (@NonNull final Exception loException) {
                loException.printStackTrace();
            }
            comicBookService = mMockService.provideComicBookService(Client.getClient());
        }

        Response<ComicBook> response;
        When:
        {
            response = comicBookService
                    .findComicBooks("1009610", 1, BuildConfig.API_KEY, BuildConfig.API_HASH)
                    .execute();
        }

        Then:
        {
            final ComicBook comicBook = response.body();
            assertThat(comicBook).isNotNull();
            assertThat(comicBook.mData.mCount).isEqualTo(0);
        }
    }

}
