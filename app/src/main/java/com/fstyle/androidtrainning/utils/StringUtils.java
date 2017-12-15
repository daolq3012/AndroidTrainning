package com.fstyle.androidtrainning.utils;

/**
 * Created by Admin on 12/15/17.
 */

public final class StringUtils {

    private static final String URL_POSTER_API = "https://image.tmdb.org/t/p/w500/";
    private static final String MORE = " ...";
    private static final String OPEN_PARENTHESE = " (";
    private static final String CLOSE_PARENTHESE = ")";
    private static final int BEGIN_INDEX = 0;
    private static final int END_INDEX = 28;
    private static final int YEAR = 4;

    private StringUtils() {
        //No-op
    }

    public static String convertPosterPathToUrlPoster(String posterPath) {
        return URL_POSTER_API + posterPath;
    }

    public static String convertLongTitleToShortTitle(String titleMovie, String releaseDate) {
        String year = releaseDate.substring(BEGIN_INDEX, YEAR);
        if (titleMovie.length() > END_INDEX) {
            titleMovie = titleMovie.substring(BEGIN_INDEX, END_INDEX)
                    + MORE + OPEN_PARENTHESE
                    + year
                    + CLOSE_PARENTHESE;
            return titleMovie;
        } else {
            titleMovie = titleMovie + OPEN_PARENTHESE + year + CLOSE_PARENTHESE;
            return titleMovie;
        }
    }
}
