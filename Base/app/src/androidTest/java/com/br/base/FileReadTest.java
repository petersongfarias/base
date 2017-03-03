package com.br.base;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.br.base.model.ComicBook;
import com.br.base.utils.FileUtils;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FileReadTest {
    private Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void useAppContext() throws Exception {
        assertEquals("com.br.marvel", appContext.getPackageName());
    }

    @Test
    public void testReadFile(){
        final Resources resources = appContext.getResources();
        final InputStream inputStream = resources.openRawResource(R.raw.comic_books);
        final String comicBooks = FileUtils.inputStreamToString(inputStream);
        assertNotNull(comicBooks);
    }

    @Test
    public void testParseObject(){
        final Resources resources = appContext.getResources();
        final InputStream inputStream = resources.openRawResource(R.raw.comic_books);
        final String comicBooks = FileUtils.inputStreamToString(inputStream);
        final ComicBook data = new Gson().fromJson(comicBooks, ComicBook.class);
        assertNotNull(data);
    }

    @Test
    public void testResultListNotNull(){
        final Resources resources = appContext.getResources();
        final InputStream inputStream = resources.openRawResource(R.raw.comic_books);
        final String comicBooks = FileUtils.inputStreamToString(inputStream);
        final ComicBook comicBook = new Gson().fromJson(comicBooks, ComicBook.class);
        assertNotNull(comicBook.mData.mResults);
    }

    @Test
    public void testResultListNotEmpty(){
        final Resources resources = appContext.getResources();
        final InputStream inputStream = resources.openRawResource(R.raw.comic_books);
        final String comicBooks = FileUtils.inputStreamToString(inputStream);
        final ComicBook comicBook = new Gson().fromJson(comicBooks, ComicBook.class);
        assertTrue(comicBook.mData.mResults.size() == comicBook.mData.mCount);
    }

}
