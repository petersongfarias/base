package com.br.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by peterson on 09/01/17.
 */

public final class FileUtils {

    /**
     * Reads a file and formats it for text
     * @param inputStream Input file
     * @return Formatted file to String
     */
    public static final String inputStreamToString(final InputStream inputStream) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            return null;
        }
        return builder.toString();
    }

}
